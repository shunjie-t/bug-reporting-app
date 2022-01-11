package controller;

import java.util.HashMap;
import entity.BugET;

public class ReviewListCT {
	private String[] colName;
	private Object[][] revList;
	private BugET bug = new BugET();
	
	private HashMap<Integer, String> btHM = new HashMap<Integer, String>();
	private HashMap<Integer, String> sHM = new HashMap<Integer, String>();
	private HashMap<Integer, String> dHM = new HashMap<Integer, String>();
	
	public ReviewListCT() {
		setColName();
	}
	
	public String[] getColName() {
		return colName;
	}
	
	private void setColName() {
		colName = bug.getColName();
	}
	
	public Object[][] getRevList() {
		setHashMaps();
		
		HashMap<Integer, String> tp = new HashMap<Integer, String>();
		HashMap<Integer, String> st = new HashMap<Integer, String>();
		HashMap<Integer, String> ds = new HashMap<Integer, String>();
		
		for(int a : sHM.keySet()) {
			if((sHM.get(a)).equals("Ready for Review")) {
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
		
		return revList;		
	}
	
	private void setHashMaps() {
		btHM.clear();
		sHM.clear();
		dHM.clear();
		
		btHM.putAll(bug.getBugType());
		sHM.putAll(bug.getStatus());
		dHM.putAll(bug.getDescription());
	}
	
	private void set2DObj() {
		int a = 0;
		revList = new Object[btHM.size()][4];
		
		for(int b : btHM.keySet()) {
			revList[a][0] = b;
			revList[a][1] = btHM.get(b);
			revList[a][2] = sHM.get(b);
			revList[a][3] = dHM.get(b);
			a++;
		}
	}
}
