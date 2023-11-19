package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.dto.TodoDTO;

public class UpdateDAO {
	public TodoDTO getTodo(int userId) 
			throws SQLException, ClassNotFoundException {
		
		TodoDTO todo = new TodoDTO();
		String sql = "SELECT id, todo, timeLimit, priority, user_id FROM todos WHERE user_id = ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, userId);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				todo.setId(res.getInt("id"));
				todo.setTodo(res.getString("todo"));
				todo.setTimeLimit(res.getDate("timeLimit"));
				todo.setPriority(res.getInt("priority"));
				todo.setUser_id(res.getInt("user_id"));
			}
			return todo;
		}
	}
	
	public int updateTodo(int id, String todo, Date timeLimit, int priority, int user_id) 
			throws SQLException, ClassNotFoundException {
		
		int processingNumber = 0;
		String sql = "UPDATE todos SET todo = ?, timeLimit = ?, priority = ?, user_id = ? WHERE id = ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			
			
			pstmt.setString(1, todo);
			pstmt.setDate(2, timeLimit);
			pstmt.setInt(3, priority);
			pstmt.setInt(4, user_id);
			pstmt.setInt(5, id);
			
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}
 
}
