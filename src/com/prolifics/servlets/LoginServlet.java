package com.prolifics.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prolifics.dao.UserDAO;
import com.prolifics.dto.UserDTO;
import com.prolifics.util.ApplicationProperties;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet",urlPatterns = "/LoginServlet",loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init(ServletConfig config) throws ServletException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		Properties props = new Properties();
		try {
			props.load(is);
			Map<String, String> map = (Map)props;
			System.out.println(map);
			ApplicationProperties.setSysProperties(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null && action.equals("logout")) {
			request.getSession().removeAttribute("user");
		}		

		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO user = dao.getUserDetails(request.getParameter("username"));
		if(user !=null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			request.getSession().setAttribute("user", user);
			dispatcher.forward(request, response);			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("error", "EmailId not found in database.");
			dispatcher.forward(request, response);
		}
	}

}
