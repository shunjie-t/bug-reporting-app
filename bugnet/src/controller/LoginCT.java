package controller;

import java.util.HashMap;

import boundary.BugListUI;
import entity.DeveloperET;
import entity.ReporterET;
import entity.ReviewerET;
import entity.TriagerET;
import entity.UserET;

public class LoginCT {	
	private UserET acct;
	
	public boolean login(String un, char[] pw) {
		authenticate(un, pw);
		
		if(acct != null) {
			BugListUI blUI = new BugListUI(acct);
			blUI.setVisible(true);
			
			return true;
		}
		
		return false;
	}
	
	private void authenticate(String un, char[] pw) {
		String strPW = String.valueOf(pw);
		String rl = null;
		int id = 0;
		HashMap<Integer, String> unHM = new HashMap<Integer, String>();
		
		acct = new UserET(un);
		if(strPW.equals(acct.getPassword())) {
			id = acct.getUserID();
			rl = acct.getRole();
			unHM = acct.getUsernameHM();
		}
		
		if(rl == null) {
			acct = null;
		}
		else if(rl.equals("Reporter")) {
			acct = new ReporterET(id, un, strPW, rl, unHM);
		}
		else if(rl.equals("Triager")) {
			acct = new TriagerET(id, un, strPW, rl, unHM);
		}
		else if(rl.equals("Developer")) {
			acct = new DeveloperET(id, un, strPW, rl, unHM);
		}
		else if(rl.equals("Reviewer")) {
			acct = new ReviewerET(id, un, strPW, rl, unHM);
		}
	}
}
