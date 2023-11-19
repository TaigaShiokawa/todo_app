package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.dao.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.sendRedirect("login/login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPass = null;
		try {
			hashedPass = hashPass(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		Login login = new Login();
		LoginDAO dao = new LoginDAO();
		
		login.setUsername(username);
		login.setPassword(hashedPass);
		
		try {
			if(dao.validate(login)) {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", dao.getUserId(username));
				response.sendRedirect("todolist-servlet");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginError", "error");
				response.sendRedirect("login/login.jsp");
			}
		} catch(SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private String hashPass(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		String hex = String.format("%064x", new BigInteger(1, digest));
		return hex;
	}

}
