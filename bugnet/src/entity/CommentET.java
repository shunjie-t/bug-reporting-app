package entity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

public class CommentET {
	private HashMap<Integer, Integer> userID = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> bugID = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> text = new HashMap<Integer, String>();
	private HashMap<Integer, String> date = new HashMap<Integer, String>();
	
	private String[] colName;
	
	public CommentET() {
		downloadDB();
	}
	
	public HashMap<Integer, Integer> getUserID() {
		return userID;
	}
	
	public HashMap<Integer, Integer> getBugID() {
		return bugID;
	}
	
	public HashMap<Integer, String> getText() {
		return text;
	}
	
	public HashMap<Integer, String> getDate() {
		return date;
	}
	
	private void downloadDB() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet res = null;
		ResultSetMetaData meta;
		int columnCount = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Project_V6.db");
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT * FROM comments");
			meta = res.getMetaData();
			columnCount = meta.getColumnCount();
			colName = new String[columnCount];
			
			for(int i = 0; i < columnCount; i++) {
				colName[i] = meta.getColumnName(i + 1);
			}
			
			while(res.next()) {
				userID.put(res.getInt(colName[0]), res.getInt(colName[1]));
				bugID.put(res.getInt(colName[0]), res.getInt(colName[2]));
				text.put(res.getInt(colName[0]), res.getString(colName[3]));
				date.put(res.getInt(colName[0]), res.getString(colName[4]));
			}
		}
		catch (SQLException e) {
			System.out.println("From downloadDB SQLException, " + e.getMessage());
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
	
	public void uploadDB(int rptID, int bID, String cmnt) {
		Connection conn = null;
		PreparedStatement stmt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
		Date dt = new Date(System.currentTimeMillis());
		
		String today = sdf.format(dt).toString();
		String query = "INSERT INTO comments(" + colName[0] + ", " + colName[1] + ", " + colName[2] + ", " + colName[3] + "," + colName[4] + ") VALUES(?, ?, ?, ?, ?)";
		int cID = text.size() + 1;
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Project_V6.db");
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, cID);
			stmt.setInt(2, rptID);
			stmt.setInt(3, bID);
			stmt.setString(4, cmnt);
			stmt.setString(5, today);
			stmt.execute();
			
			userID.put(cID, rptID);
			bugID.put(cID, bID);
			text.put(cID, cmnt);
			date.put(cID, today);
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
