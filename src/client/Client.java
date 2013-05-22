package client;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Client {

	public static void main(String[] args) throws Exception {

		// Check arguments
		if (args.length != 2) {
			System.err.println("Gimme the input/output");
			return;
		}

		// Parse input into command object list
		Parser parser = new Parser(args[0]);
		ArrayList<Command> commands = parser.parse().done();
		if (commands == null) {
			System.err.println("Wrong input has given!");
			return;
		}

		// Initialize variables
		XmlHandler<Command> handler = new XmlHandler<Command>();
		PrintWriter pw = new PrintWriter(args[1], "UTF-8");
		String url = "http://localhost:8080/bbm488_exp3/";

		for (Command command : commands) {

			// Marshal object into XML string
			String data = handler.marshal(command, Command.class);

			// Create new connection with given URL
			Connection conn = new Connection(url);

			// Send the XML data and then receive response
			String answer = conn.open("POST").sendData(data).receiveData();

			// Print response data to output file
			if (answer != null) {
				pw.println(handler.unmarshal(answer, Command.class));
			}
		}

		// Close output file
		pw.close();
	}
}
