package controller;

import java.util.ArrayList;
import java.util.HashMap;

import entity.BugET;
import entity.UserET;
import entity.TriagerET;

public class BugAssignCT {
	private BugET bug = new BugET();
	private UserET user;
	
	private HashMap<Integer, String> bugType = new HashMap<Integer, String>();
	private HashMap<Integer, String> status = new HashMap<Integer, String>();
	private HashMap<Integer, String> description = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> reporterID = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> developerID = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> date = new HashMap<Integer, String>();
	
	private HashMap<Integer, String> username = new HashMap<Integer, String>();
	private HashMap<Integer, String> role = new HashMap<Integer, String>();
	
	private String[] bugColumns;
	private Object[][] bugRows;
	
	private String[] userColumns;
	private Object[][] userRows;
	
	public BugAssignCT(UserET obj) {
		user = obj;
		setHashMaps();
		setColumns();
		setRows();
	}
	
	public String[] getBugColumns() {
		return bugColumns;
	}
	
	public Object[][] getBugRows() {
		return bugRows;
	}
	
	public String[] getUserColumns() {
		return userColumns;
	}
	
	public Object[][] getUserRows() {
		return userRows;
	}
	
	public void assignBugs(int devID, Object[][] rows) {
		for(int a = 0; a < rows.length; a++) {
			bug.updateDB((int) rows[a][0], "Active", devID);
		}
	}
	
	private void setHashMaps() {
		bugType.putAll(bug.getBugType());
		status.putAll(bug.getStatus());
		description.putAll(bug.getDescription());
		reporterID.putAll(bug.getReporterID());
		developerID.putAll(bug.getDeveloperID());
		date.putAll(bug.getDate());
		username.putAll(user.getUsernameHM());
		role.putAll(((TriagerET) user).getRoleHM());
	}
	
	private void setColumns() {
		userColumns = new String[2];
		userColumns[0] = "User ID";
		userColumns[1] = "Name";
		
		bugColumns = new String[4];
		bugColumns[0] = "Bug ID";
		bugColumns[1] = "Bug type";
		bugColumns[2] = "Description";
		bugColumns[3] = "Date";
	}
	
	private void setRows() {
		ArrayList<Integer> devKey = new ArrayList<Integer>();
		ArrayList<Integer> pendingKey = new ArrayList<Integer>();
		
		for(int k : role.keySet()) {
			if(role.get(k).equals("Developer"))
				devKey.add(k);
		}
		
		userRows = new Object[devKey.size()][2];
		
		for(int a = 0; a < devKey.size(); a++) {
			userRows[a][0] = devKey.get(a);
			userRows[a][1] = username.get(devKey.get(a));
		}
		
		for(int k : status.keySet()) {
			if(status.get(k).equals("Pending"))
				pendingKey.add(k);
		}
		
		bugRows = new Object[pendingKey.size()][4];
		
		for(int a = 0; a < pendingKey.size(); a++) {
			bugRows[a][0] = pendingKey.get(a);
			bugRows[a][1] = bugType.get(pendingKey.get(a));
			bugRows[a][2] = description.get(pendingKey.get(a));
			bugRows[a][3] = date.get(pendingKey.get(a));
		}
	}
}
