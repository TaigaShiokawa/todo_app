package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.dao.RegisterDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register-servlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.sendRedirect("register/register.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPass = null;
		try {
			hashedPass = hashPass(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		User user = new User();
		RegisterDAO dao = new RegisterDAO();
		
		user.setUsername(username);
		user.setPassword(hashedPass);
		
		try {
			int result = dao.registerUser(user);
			if(result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("NOTIFICATION", "Registration Completed!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("register/register.jsp").forward(request, response);
	}
	
	private String hashPass(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		String hex = String.format("%064x", new BigInteger(1, digest));
		return hex;
	}
}
