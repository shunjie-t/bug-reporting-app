package controller;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import entity.BugET;
import entity.UserET;

public class BugSearchCT {
	private BugET bug = new BugET();
	private UserET user;
	private String[] searchBy, category;
	
	private String[] columns;
	private Object[][] rows;
	
	private HashMap<Integer, String> bugType = new HashMap<Integer, String>();
	private HashMap<Integer, String> status = new HashMap<Integer, String>();
	private HashMap<Integer, String> description = new HashMap<Integer, String>();
	private HashMap<Integer, Integer> reporterID = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> developerID = new HashMap<Integer, Integer>();
	private HashMap<Integer, String> date = new HashMap<Integer, String>();
	
	private HashMap<Integer, String> username = new HashMap<Integer, String>();
	
	public BugSearchCT(UserET obj, String[] sb, String[] ct) {
		user = obj;
		searchBy = sb;
		category = ct;
		
		setHashMaps();
		
		columns = bug.getColName();
	}
	
	public String[] getColumns() {
		return columns;
	}
	
	public Object[][] getRows(String sb, String ct, String term) {
		setHashMaps();
		
		if(sb.equals(searchBy[0]))
			byTitle(term);
			
		else if(sb.equals(searchBy[1]))
			byUsername(term);
			
		else if(sb.equals(searchBy[2]))
			byKeyword(term);
		
		category(ct);
		
		set2DObj();
		
		return rows;
	}
	
	private void setHashMaps() {
		bugType.clear();
		status.clear();
		description.clear();
		reporterID.clear();
		developerID.clear();
		username.clear();
		date.clear();
		
		bugType.putAll(bug.getBugType());
		status.putAll(bug.getStatus());
		description.putAll(bug.getDescription());
		reporterID.putAll(bug.getReporterID());
		developerID.putAll(bug.getDeveloperID());
		username.putAll(user.getUsernameHM());
		date.putAll(bug.getDate());
	}
	
	private void byTitle(String term) {
		HashMap<Integer, String> tp = new HashMap<Integer, String>();
		HashMap<Integer, String> st = new HashMap<Integer, String>();
		HashMap<Integer, String> ds = new HashMap<Integer, String>();
		HashMap<Integer, Integer> rptID = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> devID = new HashMap<Integer, Integer>();
		HashMap<Integer, String> dt = new HashMap<Integer, String>();
		
		Pattern pt;
		Matcher mc;
		
		String regex = "(^" + term + "\\.*$)|(^" + term + " +)|( +" + term + " +)|( +" + term + "\\.*$)";
		pt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		
		for(int a : description.keySet()) {
			mc = pt.matcher(description.get(a));
			
			if(mc.find()) {
				tp.put(a, bugType.get(a));
				st.put(a, status.get(a));
				ds.put(a, description.get(a));
				rptID.put(a, reporterID.get(a));
				devID.put(a, developerID.get(a));
				dt.put(a, date.get(a));
			}
		}
		
		bugType.clear();
		status.clear();
		description.clear();
		reporterID.clear();
		developerID.clear();
		date.clear();
		
		bugType.putAll(tp);
		status.putAll(st);
		description.putAll(ds);
		reporterID.putAll(rptID);
		developerID.putAll(devID);
		date.putAll(dt);
	}
	
	private void byUsername(String term) {
		HashMap<Integer, String> tp = new HashMap<Integer, String>();
		HashMap<Integer, String> st = new HashMap<Integer, String>();
		HashMap<Integer, String> ds = new HashMap<Integer, String>();
		HashMap<Integer, Integer> rptID = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> devID = new HashMap<Integer, Integer>();
		HashMap<Integer, String> dt = new HashMap<Integer, String>();
		
		for(int a : username.keySet()) {
			if(term.equals(username.get(a))) {
				for(int b : reporterID.keySet()) {
					if(a == reporterID.get(b) || a == developerID.get(b)) {
						tp.put(b, bugType.get(b));
						st.put(b, status.get(b));
						ds.put(b, description.get(b));
						rptID.put(b, reporterID.get(b));
						devID.put(b, developerID.get(b));
						dt.put(b, date.get(b));
					}
				}
				break;
			}
		}
		
		bugType.clear();
		status.clear();
		description.clear();
		reporterID.clear();
		developerID.clear();
		date.clear();
		
		bugType.putAll(tp);
		status.putAll(st);
		description.putAll(ds);
		reporterID.putAll(rptID);
		developerID.putAll(devID);
		date.putAll(dt);
	}
	
	private void byKeyword(String term) {
		HashMap<Integer, String> tp = new HashMap<Integer, String>();
		HashMap<Integer, String> st = new HashMap<Integer, String>();
		HashMap<Integer, String> ds = new HashMap<Integer, String>();
		HashMap<Integer, Integer> rptID = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> devID = new HashMap<Integer, Integer>();
		HashMap<Integer, String> dt = new HashMap<Integer, String>();
		
		Pattern pt;
		Matcher mc;
		
		String[] words = term.split(" +");
		String regex;
		
		for(String w : words) {
			regex = "(^" + w + "\\.*$)|(^" + w + " +)|( +" + w + " +)|( +" + w + "\\.*$)";
			pt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			
			for(int a : description.keySet()) {
				mc = pt.matcher(description.get(a));
				
				if(mc.find()) {
					tp.put(a, bugType.get(a));
					st.put(a, status.get(a));
					ds.put(a, description.get(a));
					rptID.put(a, reporterID.get(a));
					devID.put(a, developerID.get(a));
					dt.put(a, date.get(a));
				}
			}
		}
		
		bugType.clear();
		status.clear();
		description.clear();
		reporterID.clear();
		developerID.clear();
		date.clear();
		
		bugType.putAll(tp);
		status.putAll(st);
		description.putAll(ds);
		reporterID.putAll(rptID);
		developerID.putAll(devID);
		date.putAll(dt);
	}
	
	private void category(String ct) {
		HashMap<Integer, String> tp = new HashMap<Integer, String>();
		HashMap<Integer, String> st = new HashMap<Integer, String>();
		HashMap<Integer, String> ds = new HashMap<Integer, String>();
		HashMap<Integer, Integer> rptID = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> devID = new HashMap<Integer, Integer>();
		HashMap<Integer, String> dt = new HashMap<Integer, String>();
		
		for(int a : bugType.keySet()) {
			if(ct.equals(category[1]) && bugType.get(a).equals(category[1])) {
				tp.put(a, bugType.get(a));
				st.put(a, status.get(a));
				ds.put(a, description.get(a));
				rptID.put(a, reporterID.get(a));
				devID.put(a, developerID.get(a));
				dt.put(a, date.get(a));
			}
			else if(ct.equals(category[2]) && bugType.get(a).equals(category[2])) {
				tp.put(a, bugType.get(a));
				st.put(a, status.get(a));
				ds.put(a, description.get(a));
				rptID.put(a, reporterID.get(a));
				devID.put(a, developerID.get(a));
				dt.put(a, date.get(a));
			}
			else if(ct.equals(category[3]) && bugType.get(a).equals(category[3])) {
				tp.put(a, bugType.get(a));
				st.put(a, status.get(a));
				ds.put(a, description.get(a));
				rptID.put(a, reporterID.get(a));
				devID.put(a, developerID.get(a));
				dt.put(a, date.get(a));
			}
		}
		
		if(!ct.equals(category[0])) {
			bugType.clear();
			status.clear();
			description.clear();
			reporterID.clear();
			developerID.clear();
			date.clear();
		
			bugType.putAll(tp);
			status.putAll(st);
			description.putAll(ds);
			reporterID.putAll(rptID);
			developerID.putAll(devID);
			date.putAll(dt);
		}
	}
	
	private void set2DObj() {
		int a = 0;
		rows = new Object[bugType.size()][columns.length];
		
		for(int b : bugType.keySet()) {
			rows[a][0] = b;
			rows[a][1] = bugType.get(b);
			rows[a][2] = status.get(b);
			rows[a][3] = description.get(b);
			rows[a][4] = username.get(reporterID.get(b));
			rows[a][5] = username.get(developerID.get(b));
			rows[a][6] = date.get(b);
			a++;
		}
	}
}
