package controller;

import entity.BugET;

public class BugReportCT {
	private BugET bug = new BugET();
	
	public void report(int rptID, String desc, String type) {
		bug.uploadDB(rptID, desc, type);
	}
}
