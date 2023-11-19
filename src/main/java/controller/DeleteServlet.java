package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import content.Parameters;
import model.dao.DeleteDAO;
import model.dto.TodoDTO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete-servlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DeleteDAO dao = new DeleteDAO();
		TodoDTO todo = new TodoDTO();
		
		int id = Integer.parseInt(request.getParameter(Parameters.TODO_ID));
		
		todo.setId(id);
		
		try {
			dao.deleteTodo(id);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		response.sendRedirect("todolist-servlet");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

}
