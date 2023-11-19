package model.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.Login;

public class LoginDAO {
	public boolean validate(Login login) 
			throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		
		boolean status = false;
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		String hashedPass = hashPass(login.getPassword());
		
		try(Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, login.getUsername());
			pstmt.setString(2, hashedPass);
			
			ResultSet res = pstmt.executeQuery();
			status = res.next();
		}
		return status;
	}
	
	public int getUserId(String username) 
			throws SQLException, ClassNotFoundException {
		
		int userId = -1;
		String sql = "SELECT id FROM users WHERE username = ?";
		try(Connection con = DBConnection.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, username);
			ResultSet res = pstmt.executeQuery();
			if(res.next()) {
				userId = res.getInt("id");
			}
		}
		return userId;
	}
	
	private String hashPass(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hex = String.format("%064x", new BigInteger(1, digest));
        return hex;
    }
}