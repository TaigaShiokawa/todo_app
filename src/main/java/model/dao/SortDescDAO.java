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

public class SortDescDAO {
	public List<TodoDTO> descDAO() 
			throws SQLException, ClassNotFoundException {
		
		List<TodoDTO> todoList = new ArrayList<>();
		String sql = "SELECT * FROM todos ORDER BY timeLimit DESC";
		
		try(Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int id = res.getInt("id");
				String todo = res.getString("todo");
				Date timeLimit = res.getDate("timeLimit");
				int priority = res.getInt("priority");
				int user_id = res.getInt("user_id");
				
				todoList.add(new TodoDTO(id, todo, timeLimit, priority, user_id));
			}
			return todoList;
		}
	}

}
