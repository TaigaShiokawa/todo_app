package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.InsertDAO;
import model.dto.TodoDTO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert-servlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String todo = request.getParameter("todo");
		Date timeLimit = Date.valueOf(request.getParameter("timeLimit"));
		int priority = Integer.parseInt(request.getParameter("priority"));
		
		InsertDAO dao = new InsertDAO();
		TodoDTO todos = new TodoDTO();
		
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("loginUser");
		
		todos.setTodo(todo);
		todos.setTimeLimit(timeLimit);
		todos.setPriority(priority);
		todos.setUser_id(userId);
		
		try {
			dao.isertTodo(todos);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.sendRedirect("todolist-servlet");
		
	}

}
