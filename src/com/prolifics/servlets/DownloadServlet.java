package com.prolifics.servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prolifics.dto.UserDTO;
import com.prolifics.util.ApplicationProperties;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("fileName") == null) {
			String uploadLocation = ApplicationProperties.getProperty("upload.dir");
			UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
			if(dto!=null) {
				File directoryPath = new File(uploadLocation + "/" + dto.getId());
				if(directoryPath.exists()) {
					request.setAttribute("myFiles", directoryPath.list());
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("my-files.jsp");
				dispatcher.forward(request, response);
			}else {
				//Session timed out
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}
		}else {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadLocation = ApplicationProperties.getProperty("upload.dir");
		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		
		if(dto == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		String fileName = request.getParameter("fileName");
		String path = uploadLocation+"/"+ dto.getId()+"/"+ fileName;
		File file = new File(path);
		int length = 0;
		ServletOutputStream os = response.getOutputStream();
		response.setContentType(getContentType(path));
		response.setContentLength((int)file.length());
		
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
        response.setHeader(headerKey, headerValue);
	        
		 byte[] by = new byte[(int) file.length()];
		 DataInputStream in = new DataInputStream(new FileInputStream(file));
		 
		 while((in != null) && ((length = in.read(by)) != -1))
		 {
			 os.write(by,0,length);
		 }
		 in.close();
		 os.close();
	}
	
	/**
	 * getContentType returns the MIME Type
	 * of a specified File.
	 * 
	 * @param file
	 * 	<p>The input File Name as String.
	 * @return
	 * 	<p>The desired MIME Type.</p>
	 */
	private String getContentType(String file) {
		String type = null;
		Path path = new File(file).toPath();
		try {
			type = Files.probeContentType(path);
		} catch (IOException e) {
			type = "application/octet-stream";
		}
		
		System.out.println("Content Type - " + type);
		
		return type;
	}

}
