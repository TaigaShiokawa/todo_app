package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DBConnection;
import model.dto.TodoDTO;

public class InsertDAO {
	public int isertTodo(TodoDTO todo) 
			throws SQLException, ClassNotFoundException {
		
		int processingNumber = 0;
		String sql = "INSERT INTO todos (todo, timeLimit, priority, user_id) VALUES (?, ?, ?, ?)";
		
		try(Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, todo.getTodo());
			pstmt.setDate(2, todo.getTimeLimit());
			pstmt.setInt(3, todo.getPriority());
			pstmt.setInt(4, todo.getUser_id());
			
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}
}
