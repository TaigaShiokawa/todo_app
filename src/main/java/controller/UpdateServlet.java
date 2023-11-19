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

import model.dao.UpdateDAO;
import model.dto.TodoDTO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update-servlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		TodoDTO todo = new TodoDTO();
		UpdateDAO dao = new UpdateDAO();
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("loginUser");
		
		try {
			todo = dao.getTodo(userId);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("update/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String todo = request.getParameter("todo");
		Date timeLimit = Date.valueOf(request.getParameter("timeLimit"));
		int priority = Integer.parseInt(request.getParameter("priority"));
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("loginUser");
		
		UpdateDAO dao = new UpdateDAO();
		TodoDTO todos = new TodoDTO();
		
		todos.setId(id);
		todos.setTodo(todo);
		todos.setTimeLimit(timeLimit);
		todos.setPriority(priority);
		todos.setUser_id(userId);
		
		try {
			dao.updateTodo(id, todo, timeLimit, priority, userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("todolist-servlet");
	}

}
