package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {

	private URL _url;
	private HttpURLConnection _conn;

	/**
	 * 
	 * @param url
	 *            URL to be opened
	 */
	public Connection(String url) {
		try {
			_url = new URL(url);
		} catch (MalformedURLException e) {
			_url = null;
		}
	}

	/**
	 * Opens connection to given URL with specific HTTP method.
	 * 
	 * @param method
	 *            can be "POST", "GET", "PUT", "DELETE" etc.
	 * @return if _url is malformed return null object else return connection
	 *         object.
	 * @throws IOException
	 */
	public Connection open(String method) throws IOException {

		// Check if URLis malformed
		if (_url == null) {
			System.err.println("Cannot open connection");
			return null;
		}
		// Open connection to given URL with given method
		_conn = (HttpURLConnection) _url.openConnection();
		_conn.setDoOutput(true);
		_conn.setRequestMethod(method);

		// return connection object
		return this;
	}

	/**
	 * Receive response data from opened connection.
	 * 
	 * @return response data from server. If there is no answer return null
	 * @throws IOException
	 */
	public String receiveData() throws IOException {

		// Check if connection is opened
		if (_conn == null) {
			return null;
		}

		// Initialize variables
		StringBuffer answer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				_conn.getInputStream()));
		String line;

		// Read response data
		while ((line = reader.readLine()) != null) {
			answer.append(line);
		}

		// Check if response is null or empty
		if (answer.toString() == null || answer.toString().isEmpty()) {
			return null;
		}

		// return response data
		return answer.toString().trim();
	}

	/**
	 * Send request data from opened connection.
	 * 
	 * @param data
	 *            data to be send
	 * @return Connection object
	 * @throws IOException
	 */
	public Connection sendData(String data) throws IOException {

		// Send request data to writer
		OutputStreamWriter writer = new OutputStreamWriter(
				_conn.getOutputStream());
		writer.write(data);
		writer.flush();

		// return Connection object
		return this;
	}

}
