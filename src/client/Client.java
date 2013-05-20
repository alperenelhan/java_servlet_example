package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Client {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Gimme the input/output");
			return;
		}
		Parser parser = new Parser(args[0]);
		ArrayList<Command> commands = parser.parse().done();
		if (commands == null) {
			System.err.println("Wrong input has given!");
			return;
		}
		XmlHandler<Command> handler = new XmlHandler<Command>();
		PrintWriter pw = new PrintWriter(args[1], "UTF-8");
		for (Command command : commands) {
			String data = handler.marshal(command, Command.class);
			
			// Open connection
			URL url = new URL("http://localhost:8080/bbm488_exp3/");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			// Write data
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(data);
			writer.flush();
			
			// Read data
			StringBuffer answer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				answer.append(line);
			}
			//pw.println(handler.unmarshal(answer.toString(), Command.class));
			pw.println(answer.toString());
		}
		pw.close();
	}
}
