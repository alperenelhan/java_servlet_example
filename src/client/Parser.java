package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

;

public class Parser {

	private DataInputStream in;
	private BufferedReader br;
	private ArrayList<Command> commands;

	public Parser(String input) throws IOException {
		commands = new ArrayList<Command>();
		FileInputStream fstream = new FileInputStream(input);
		in = new DataInputStream(fstream);
		br = new BufferedReader(new InputStreamReader(in));
	}

	public Parser parse() throws IOException {
		String strLine;
		while ((strLine = br.readLine()) != null) {
			if (strLine == null || strLine.isEmpty()) {
				continue;
			}
			String[] str = strLine.trim().split(" ");
			if (str.length == 0) {
				continue;
			}
			Command command = new Command();
			command.name(str[0]);
			if ("listele".equals(command.name())) {
				if (str.length == 2) {
					command.personId(str[1]);
				}
			} else if ("ekle".equals(command.name())
					|| "gunle".equals(command.name())) {
				if (str.length < 4) {
					continue;
				}
				command.personId(str[1]);
				command.date(str[str.length - 1]);
				command.person(StringUtils.join(str, ' ', 2, str.length - 1));
			} else if ("sil".equals(command.name())) {
				if (str.length != 2) {
					continue;
				}
				command.personId(str[1]);
			} else {
				System.out.println("command named " + str[0] + " not found");
				continue;
			}
			commands.add(command);
		}
		return this;
	}

	public ArrayList<Command> done() {
		try {
			in.close();
		} catch (IOException e) {
			return null;
		}
		return commands;
	}
}
