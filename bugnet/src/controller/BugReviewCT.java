package controller;

import javax.swing.JLabel;

import entity.BugET;

public class BugReviewCT {
	private BugET bug = new BugET();
	
	private int bugID;
	
	public BugReviewCT(int bID) {
		bugID = bID;
	}
	
	public void bugReviewed() {
		bug.updateDB(bugID, "Reviewed", 0);
	}
	
	public void rdyToReview() {
		bug.updateDB(bugID, "Ready for Review", 0);
	}
	
	public void setLabels(JLabel bID, JLabel desc, JLabel stat, JLabel type, JLabel rpt, JLabel dev) {
		bID.setText(Integer.toString(bugID));
		desc.setText(bug.getDescription().get(bugID));
		stat.setText(bug.getStatus().get(bugID));
		type.setText(bug.getBugType().get(bugID));
		rpt.setText(Integer.toString(bug.getReporterID().get(bugID)));
		dev.setText(Integer.toString(bug.getDeveloperID().get(bugID)));
	}
}
