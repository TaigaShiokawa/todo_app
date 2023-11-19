package model.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.DBConnection;
import model.User;

public class RegisterDAO {
	public int registerUser(User user) 
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		
		int processingNumber = 0;
		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
		String hashedPass = hashPass(user.getPassword());
		
		try(Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, hashedPass);
			
			processingNumber = pstmt.executeUpdate();
		}
		return processingNumber;
	}
	
	private String hashPass(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		String hex = String.format("%064x", new BigInteger(1, digest));
		return hex;
	}
}
