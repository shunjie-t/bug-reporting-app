package main;

import boundary.LoginUI;

public class Main {

	public static void main(String[] args) {
		try {
			// Loads the JDBC driver into memory at the beginning of the application.
			Class.forName("org.sqlite.JDBC");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		LoginUI lgUI = new LoginUI();
		lgUI.setVisible(true);
		lgUI.requestFocus();
	}

}
