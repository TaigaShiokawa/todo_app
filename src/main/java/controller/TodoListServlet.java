package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TodoListDAO;
import model.dto.TodoDTO;

/**
 * Servlet implementation class TodoListServlet
 */
@WebServlet("/todolist-servlet")
public class TodoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("loginUser");
		
		List<TodoDTO> todoList = new ArrayList<>();
		TodoListDAO dao = new TodoListDAO();
		
		try {
			todoList = dao.getTodoList(userId);
			request.setAttribute("todoList", todoList);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("todo/todo-list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

}
