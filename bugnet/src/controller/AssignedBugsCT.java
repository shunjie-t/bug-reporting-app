package controller;

import java.util.HashMap;
import entity.BugET;

public class AssignedBugsCT {
	private String[] colName;
	private Object[][] asnBugs;
	private BugET bug = new BugET();
	
	private HashMap<Integer, String> btHM = new HashMap<Integer, String>();
	private HashMap<Integer, String> sHM = new HashMap<Integer, String>();
	private HashMap<Integer, String> dHM = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> dIDHM = new HashMap<Integer, Integer>();
	
	public AssignedBugsCT() {
		setColName();
	}
	
	public String[] getColName() {
		return colName;
	}
	
	private void setColName() {
		colName = bug.getColName();
	}
	
	public Object[][] getAsnBugs(int dID) {
		setHashMaps();
		
		HashMap<Integer, String> tp = new HashMap<Integer, String>();
		HashMap<Integer, String> st = new HashMap<Integer, String>();
		HashMap<Integer, String> ds = new HashMap<Integer, String>();
		
		for(int a : dIDHM.keySet()) {
			if(dID == dIDHM.get(a)) {
				tp.put(a, btHM.get(a));
				st.put(a, sHM.get(a));
				ds.put(a, dHM.get(a));
			}
		}
		
		btHM.clear();
		sHM.clear();
		dHM.clear();
		
		btHM.putAll(tp);
		sHM.putAll(st);
		dHM.putAll(ds);
		
		set2DObj();
		
		return asnBugs;		
	}
	
	private void setHashMaps() {
		btHM.clear();
		sHM.clear();
		dHM.clear();
		dIDHM.clear();
		
		btHM.putAll(bug.getBugType());
		sHM.putAll(bug.getStatus());
		dHM.putAll(bug.getDescription());
		dIDHM.putAll(bug.getDeveloperID());
	}
	
	private void set2DObj() {
		int a = 0;
		asnBugs = new Object[btHM.size()][4];
		
		for(int b : btHM.keySet()) {
			if (sHM.get(b).equals("Pending")) {
				asnBugs[a][0] = b;
				asnBugs[a][1] = btHM.get(b);
				asnBugs[a][2] = sHM.get(b);
				asnBugs[a][3] = dHM.get(b);
				a++;
			}
		}
		
		for(int b : btHM.keySet()) {
			if (sHM.get(b).equals("Active")) {
				asnBugs[a][0] = b;
				asnBugs[a][1] = btHM.get(b);
				asnBugs[a][2] = sHM.get(b);
				asnBugs[a][3] = dHM.get(b);
				a++;
			}
		}
		
		for(int b : btHM.keySet()) {
			if (sHM.get(b).equals("Ready for Review")) {
				asnBugs[a][0] = b;
				asnBugs[a][1] = btHM.get(b);
				asnBugs[a][2] = sHM.get(b);
				asnBugs[a][3] = dHM.get(b);
				a++;
			}
		}
		
		for(int b : btHM.keySet()) {
			if (sHM.get(b).equals("Reviewed")) {
				asnBugs[a][0] = b;
				asnBugs[a][1] = btHM.get(b);
				asnBugs[a][2] = sHM.get(b);
				asnBugs[a][3] = dHM.get(b);
				a++;
			}
		}
		
		for(int b : btHM.keySet()) {
			if (sHM.get(b).equals("Closed")) {
				asnBugs[a][0] = b;
				asnBugs[a][1] = btHM.get(b);
				asnBugs[a][2] = sHM.get(b);
				asnBugs[a][3] = dHM.get(b);
				a++;
			}
		}
		
		for(int b : btHM.keySet()) {
			if (sHM.get(b).equals("Duplicate")) {
				asnBugs[a][0] = b;
				asnBugs[a][1] = btHM.get(b);
				asnBugs[a][2] = sHM.get(b);
				asnBugs[a][3] = dHM.get(b);
				a++;
			}
		}
		
		for(int b : btHM.keySet()) {
			if (sHM.get(b).equals("Invalid")) {
				asnBugs[a][0] = b;
				asnBugs[a][1] = btHM.get(b);
				asnBugs[a][2] = sHM.get(b);
				asnBugs[a][3] = dHM.get(b);
				a++;
			}
		}
	}
}
