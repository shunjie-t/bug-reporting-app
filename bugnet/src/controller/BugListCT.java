package controller;

import entity.BugET;
import entity.UserET;

public class BugListCT {
	private BugET bug = new BugET();
	private UserET user;
	
	private String[] columns;
	private Object[][] rows;
	
	public BugListCT(UserET obj) {
		user = obj;
		setAttributes();
	}
	
	public String[] getColumns() {
		return columns;
	}
	
	public Object[][] getRows() {
		return rows;
	}
	
	private void setAttributes() {
		int b = bug.getBugType().size() - 1;
		columns = bug.getColName();
		
		rows = new Object[bug.getBugType().size()][columns.length];
		// b starts from the last index so that the list displays the latest bugs.
		
		for(int a : bug.getBugType().keySet()) {
			rows[b][0] = a;
			rows[b][1] = bug.getBugType().get(a);
			rows[b][2] = bug.getStatus().get(a);
			rows[b][3] = bug.getDescription().get(a);
			rows[b][4] = user.getUsernameHM().get(bug.getReporterID().get(a));
			rows[b][5] = user.getUsernameHM().get(bug.getDeveloperID().get(a));
			rows[b][6] = bug.getDate().get(a);
			b--;
		}
	}
}
