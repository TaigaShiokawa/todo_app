package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBConnection;
import model.dto.TodoDTO;

public class LowPriorityDAO {
	public List<TodoDTO> descTodo() 
			throws SQLException, ClassNotFoundException {
		
		List<TodoDTO> todo = new ArrayList<>();
		String sql = "SELECT * FROM todos ORDER BY priority DESC";
		
		try(Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				int id = res.getInt("id");
				String todos = res.getString("todo");
				Date timeLimit = res.getDate("timeLimit");
				int priority = res.getInt("priority");
				int user_id = res.getInt("user_id");
				
				todo.add(new TodoDTO(id, todos, timeLimit, priority, user_id));
			}
		}
		return todo;
	}
}
