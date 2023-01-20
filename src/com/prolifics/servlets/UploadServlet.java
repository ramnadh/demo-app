package com.prolifics.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.prolifics.dto.UserDTO;
import com.prolifics.util.ApplicationProperties;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO dto = (UserDTO) request.getSession().getAttribute("user");
		if(dto!=null) {
			response.sendRedirect(request.getContextPath() + "/home.jsp");
		}else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
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
		String path = uploadLocation + "/" + dto.getId();
		File directoryPath = new File(path);
		if(!directoryPath.exists()) {
			directoryPath.mkdir();
		}
		processRequest(request, response, path);
		
	}
	

	protected void processRequest(HttpServletRequest request, HttpServletResponse response,String path)throws ServletException, IOException {  
        response.setContentType("text/html;charset=UTF-8");  
        String param = "";
        // creating path components for saving the file  
        final Part filePart = request.getPart("fl");  
        final String fileName = getFileName(filePart);  
          
        // declare instances of OutputStream and InputStream  
        OutputStream otpStream = null;  
        InputStream iptStream = null; 
        
        // try section handles the code for storing file into the specified location  
        try {  
            // initialize instances of OutputStream and InputStream classes  
            otpStream = new FileOutputStream(new File(path + File.separator + fileName));  
            iptStream = filePart.getInputStream();  
  
            int read = 0;  
              
            // initialize bytes array for storing file data  
            final byte[] bytes = new byte[1024];  
              
            // use while loop to read data from the file using iptStream and write into  the destination folder using writer and otpStream  
            while ((read = iptStream.read(bytes)) != -1) {  
                otpStream.write(bytes, 0, read);  
            }  
            System.out.println("New file " + fileName + " created at " + path);
            param = "?success=File Uploaded Successfully!";
        }  
        // catch section handles the errors   
        catch (FileNotFoundException fne){
        	param = "?error=File Upload Failed!";
            fne.printStackTrace();
        }  
        // finally section will close all the open classes  
        finally {  
            if (otpStream != null) {  
                otpStream.close();  
            }  
            if (iptStream != null) {  
                iptStream.close();  
            }
        }  
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp"+param);
        dispatcher.forward(request, response);
    }  

	// getFileName() method to get the file name from the part  
    private String getFileName(final Part part) {  
        // get header(content-disposition) from the part  
        final String partHeader = part.getHeader("content-disposition"); 
        System.out.println("partHeader - " + partHeader);  
        // code to get file name from the header  
        for (String content : part.getHeader("content-disposition").split(";")) {  
            if (content.trim().startsWith("filename")) {  
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");  
            }  
        }  
       
        return part.getSubmittedFileName();  
    }  
}  
	
	
	


