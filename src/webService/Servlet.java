package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected static Operations ops = new Operations();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		BufferedReader br = req.getReader();
		String line;
		StringBuffer answer = new StringBuffer();
		while ((line = br.readLine()) != null) {
			answer.append(line);
		}
		try {
			out.write(ops.exec(answer.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
