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

import model.dao.LowPriorityDAO;
import model.dto.TodoDTO;

/**
 * Servlet implementation class LowPriorityServlet
 */
@WebServlet("/lowpriority-servlet")
public class LowPriorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<TodoDTO> todoList = new ArrayList<>();
		LowPriorityDAO dao = new LowPriorityDAO();
		
		try {
			todoList = dao.descTodo();
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("todoList", todoList);
		request.getRequestDispatcher("todo/todo-list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
