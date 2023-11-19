package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DBConnection;

public class DeleteDAO {
	public int deleteTodo(int id) 
			throws SQLException, ClassNotFoundException {
		
		int processingNum = 0;
		String sql = "DELETE FROM todos WHERE id = ?";
		
		try (Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, id);
			processingNum = pstmt.executeUpdate();
		}
		return processingNum;
	}

}
