package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

public class ServletUpload extends HttpServlet {

	private ServletConfig config;

	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	final public ServletConfig getServletConfig() {
		return config;
	}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<BODY BGCOLOR='white'>");
		out.println("<H1>jspSmartUpload : Servlet Sample</H1>");
		out.println("<HR>");

		// Variables
		int count = 0;
		SmartUpload su = new SmartUpload();
		try {
			// Initialization
			su.initialize(config, request, response);
			// Upload
			su.upload();
            String testparam=su.getRequest().getParameter("TEST");
            System.out.println(testparam);
			// 逐一提取上传文件信息，同时可保存文件。
			for (int i = 0; i < su.getFiles().getCount(); i++) {
				com.jspsmart.upload.File file = su.getFiles().getFile(i);
				// 若文件不存在则继续
				if (file.isMissing())
					continue;
				// file.saveAs("/upload/" + myFile.getFileName());
				file.saveAs("/upload/" + file.getFileName(), su.SAVE_VIRTUAL);

			}

		} catch (Exception e) {
			out.println("Unable to upload the file.<br>");
			out.println("Error : " + e.toString());
		}

		out.println("</BODY>");
		out.println("</HTML>");
	}

	/**
	 * Destroy the servlet
	 */
	public void destroy() {
	}
}
