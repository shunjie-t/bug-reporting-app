package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class UserET {
	private int userID;
	private String username, password;
	private String[] colName;
	private HashMap<Integer, String> usernameHM = new HashMap<Integer, String>();
	
	public UserET(String un) {
		downloadDB(un);
	}
	
	public UserET(int id, String un, String pw, HashMap<Integer, String> unHM) {
		userID = id;
		username = un;
		password = pw;
		usernameHM = unHM;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public HashMap<Integer, String> getUsernameHM() {
		return usernameHM;
	}
	
	public String getRole() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		String result;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Project_V6.db");
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT " + colName[3] + " FROM users WHERE " + colName[0] + " = '" + Integer.toString(getUserID()) + "'");
			result = res.getString(colName[3]);
			
			return result;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void downloadDB(String un) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		ResultSetMetaData meta;
		int columnCount;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Project_V6.db");
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT * FROM users");
			meta = res.getMetaData();
			columnCount = meta.getColumnCount();
			colName = new String[columnCount];
			
			for(int i = 0; i < columnCount; i++) {
				colName[i] = meta.getColumnName(i + 1);
			}
			
			res.close();
			res = stmt.executeQuery("SELECT * FROM users WHERE " + colName[1] + " = '" + un + "'");
			
			userID = res.getInt(colName[0]);
			username = res.getString(colName[1]);
			password = res.getString(colName[2]);
			
			res.close();
			res = stmt.executeQuery("SELECT UID, UName FROM users");
			
			while(res.next()) {
				usernameHM.put(res.getInt("UID"), res.getString("UName"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
