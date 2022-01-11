package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DeveloperET extends UserET {
	private String role;
	private HashMap<Integer, String> roleHM = new HashMap<Integer, String>();
	
	public DeveloperET(int id, String un, String pw, String rl, HashMap<Integer, String> unHM) {
		super(id, un, pw, unHM);
		role = rl;
		downloadDB();
	}
	
	@Override
	public String getRole() {
		return role;
	}
	
	public HashMap<Integer, String> getRoleHM() {
		return roleHM;
	}
	
	private void downloadDB() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Project_V6.db");
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT UID, URole FROM users");
			
			while(res.next()) {
				roleHM.put(res.getInt("UID"), res.getString("URole"));
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
