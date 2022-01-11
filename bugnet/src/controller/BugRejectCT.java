package controller;

import javax.swing.JLabel;

import entity.BugET;
import entity.UserET;

public class BugRejectCT {
	private BugET bug = new BugET();
	private UserET user;
	
	private int bugID;
	
	public BugRejectCT(int bID, UserET obj) {
		bugID = bID;
		user = obj;
	}
	
	public void rejectBug(String stat) {
		bug.updateDB(bugID, stat, 0);
	}
	
	public void setLabels(JLabel bID, JLabel desc, JLabel stat, JLabel type, JLabel rpt) {
		bID.setText(Integer.toString(bugID));
		desc.setText(bug.getDescription().get(bugID));
		stat.setText(bug.getStatus().get(bugID));
		type.setText(bug.getBugType().get(bugID));
		rpt.setText(user.getUsernameHM().get(bug.getReporterID().get(bugID)));
	}
}
