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

public class BugET {	
	/*
	 * This hashmaps contain all the data from bugs table.
	 * The data from sqlite database will be stored into these hashmaps.
	 * There are 6 hashmaps each for the column of data to be stored as the value of the hashmap.
	 * BugID column is stored as the key for all of the hashmaps.
	 * */
	private HashMap<Integer, String> bugType = new HashMap<Integer, String>();
	private HashMap<Integer, String> status = new HashMap<Integer, String>();
	private HashMap<Integer, String> description = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> reporterID = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> developerID = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> date = new HashMap<Integer, String>();
	
	// Column name of bugs table.
	private String[] colName;
	
	public BugET() {
		downloadDB();
	}
	
	public HashMap<Integer, String> getBugType() {
		return bugType;
	}
	
	public HashMap<Integer, String> getStatus() {
		return status;
	}
	
	public HashMap<Integer, String> getDescription() {
		return description;
	}
	
	public HashMap<Integer, Integer> getReporterID() {
		return reporterID;
	}
	
	public HashMap<Integer, Integer> getDeveloperID() {
		return developerID;
	}
	
	public HashMap<Integer, String> getDate() {
		return date;
	}
	
	public String[] getColName() {
		return colName;
	}
	
	private void downloadDB() {
		Connection conn = null;
		Statement bugStmt = null;
		ResultSet bugRes = null;
		ResultSetMetaData bugMeta;
		int columnCount = 0;

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Project_V6.db");
			
			bugStmt = conn.createStatement();
			bugRes = bugStmt.executeQuery("SELECT * FROM bugs");
			bugMeta = bugRes.getMetaData();
			columnCount = bugMeta.getColumnCount();
			colName = new String[columnCount];

			/*
			 * Get the all column names from the query.
			 * Assign it to the colName array.
			 * */
			for(int i = 0; i < columnCount; i++) {
				colName[i] = bugMeta.getColumnName(i + 1);
			}

			/*
			 * Retrieve data from database.
			 * Assign the retrieved data into the 6 Hashmaps.
			 * */
			while(bugRes.next()) {
				bugType.put(bugRes.getInt(colName[0]), bugRes.getString(colName[1]));
				status.put(bugRes.getInt(colName[0]), bugRes.getString(colName[2]));
				description.put(bugRes.getInt(colName[0]), bugRes.getString(colName[3]));
				reporterID.put(bugRes.getInt(colName[0]), bugRes.getInt(colName[4]));
				developerID.put(bugRes.getInt(colName[0]), bugRes.getInt(colName[5]));
				date.put(bugRes.getInt(colName[0]), bugRes.getString(colName[6]));
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
			catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void uploadDB(int rptID, String desc, String type) {
		Connection conn = null;
		PreparedStatement prep = null;
		SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
		Date dt = new Date(System.currentTimeMillis());
		
		String today = sdf.format(dt).toString();
		String query = "";
		int id = bugType.size() + 1;
		
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Project_V6.db");
			
			query = "INSERT INTO bugs('" + colName[0] + "', '" + colName[1] + "', '" + colName[2] + "', '" + colName[3] + "', '" + colName[4] + "', '" + colName[5] + "', '" + colName[6] + "') VALUES(?, ?, ?, ?, ?, ?, ?)";
			prep = conn.prepareStatement(query);
			prep.setInt(1, id);
			prep.setString(2, type);
			prep.setString(3, "Pending");
			prep.setString(4, desc);
			prep.setInt(5, rptID);
			prep.setInt(6, 0);
			prep.setString(7, today);
			
			prep.execute();
			
			bugType.put(id, type);
			status.put(id, "Pending");
			description.put(id, desc);
			reporterID.put(id, rptID);
			developerID.put(id, 0);
			date.put(id, today);
		}
		catch (SQLException e) {
			System.out.println("From uploadDB SQLException, " + e.getMessage());
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			}
			catch (Exception e) {
				System.out.println("From uploadDB finally Exception, " + e.getMessage());
			}
		}
	}
	
	public void updateDB(int bID, String stat, int devID) {
		Connection conn = null;
		PreparedStatement prep = null;
		String query = "";
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:Project_V6.db");
			
			if(devID == 0) {
				// When devID is 0, bug status will be updated to either 'Invalid' or 'Duplicate'. 
				query = "UPDATE bugs SET " + colName[2] + " = ? WHERE " + colName[0] + " = " + bID;
				prep = conn.prepareStatement(query);
				prep.setString(1, stat);
				
				status.put(bID, stat);
			}
			else if(devID != 0) {
				// When devID is not 0, bug status will be updated to 'Active'.
				// And devID will be updated.
				query = "UPDATE bugs SET " + colName[2] + " = ?, " + colName[5] + " = ? WHERE " + colName[0] + " = " + bID;
				prep = conn.prepareStatement(query);
				prep.setString(1, stat);
				prep.setInt(2, devID);
				
				status.put(bID, stat);
				developerID.put(bID, devID);
			}
			
			prep.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("From updateDB SQLException, " + e.getMessage());
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			}
			catch (Exception e) {
				System.out.println("From updateDB finally Exception, " + e.getMessage());
			}
		}
	}
}
