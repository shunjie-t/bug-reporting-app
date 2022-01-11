package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controller.BugReportCT;
import entity.UserET;

public class BugReportUI extends JFrame {
	private UserET user;
	private BugReportCT controller = new BugReportCT();
	
	public BugReportUI(UserET obj) {
		user = obj;
		
		initialize();
	}
	
	private void initialize() {
		JPanel MainPanel,HeaderPanel,BodyPanel,NavigationPanel,LeftNavPanel,MiddleNavPanel,RightNavPanel,ContentPanel,UsernamePanel,FillerPanel1;
		JLabel lblLogo,lblTitle,lblUsername,lblRole,lblBugType,lblDescription,lblInvalid;
		JMenu mnGoto;
		JMenuBar mbNavigation;
		JComboBox cbBugType;
		JTextArea taDescription;
		JButton btnSubmit;
		
		setTitle("Bugnet");
		setMinimumSize(new Dimension(1000, 600));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		MainPanel = new JPanel();
		MainPanel.setBackground(new Color(47, 49, 54));
		getContentPane().add(MainPanel, BorderLayout.NORTH);
		MainPanel.setLayout(new BorderLayout(0, 0));
	
		HeaderPanel = new JPanel();
		HeaderPanel.setBackground(new Color(47, 49, 54));
		MainPanel.add(HeaderPanel, BorderLayout.NORTH);
	
		lblLogo = new JLabel("Bugnet");
		HeaderPanel.add(lblLogo);
		lblLogo.setForeground(new Color(200, 201, 202));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
	
		BodyPanel = new JPanel();
		getContentPane().add(BodyPanel, BorderLayout.CENTER);
		BodyPanel.setLayout(new BorderLayout(0, 0));
	
		NavigationPanel = new JPanel();
		BodyPanel.add(NavigationPanel, BorderLayout.NORTH);
		NavigationPanel.setLayout(new BorderLayout(0, 0));
	
		LeftNavPanel = new JPanel();
		NavigationPanel.add(LeftNavPanel, BorderLayout.WEST);
	
		mbNavigation = new JMenuBar();
		LeftNavPanel.add(mbNavigation);
	
		mnGoto = new JMenu("Go to");
		mbNavigation.add(mnGoto);
	
		addMenuItems(user.getRole(), mnGoto);
	
		MiddleNavPanel = new JPanel();
		NavigationPanel.add(MiddleNavPanel, BorderLayout.CENTER);
	
		RightNavPanel = new JPanel();
		NavigationPanel.add(RightNavPanel, BorderLayout.EAST);
		
		UsernamePanel = new JPanel();
		RightNavPanel.add(UsernamePanel, BorderLayout.CENTER);
		UsernamePanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblUsername = new JLabel(user.getUsername());
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		UsernamePanel.add(lblUsername);
		
		lblRole = new JLabel(user.getRole());
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		UsernamePanel.add(lblRole);
		
		FillerPanel1 = new JPanel();
		RightNavPanel.add(FillerPanel1, BorderLayout.EAST);
		FillerPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ContentPanel = new JPanel();
		ContentPanel.setLayout(null);
		BodyPanel.add(ContentPanel, BorderLayout.CENTER);
		
		lblTitle = new JLabel("Report a Bug");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(95, 49, 175, 36);
		ContentPanel.add(lblTitle);
				
		lblBugType = new JLabel("Bug Type:");
		lblBugType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBugType.setBounds(46, 123, 62, 21);
		ContentPanel.add(lblBugType);
		
		cbBugType = new JComboBox();
		cbBugType.setModel(new DefaultComboBoxModel(new String[] {"Backend", "Frontend", "Network"}));
		cbBugType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbBugType.setBounds(231, 123, 156, 22);
		ContentPanel.add(cbBugType);
				
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(46, 167, 175, 21);
		ContentPanel.add(lblDescription);
		
		lblInvalid = new JLabel("Description cannot be blank.");
		lblInvalid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInvalid.setBounds(231, 347, 200, 21);
		ContentPanel.add(lblInvalid);
		lblInvalid.setVisible(false);
		
		taDescription = new JTextArea();
		taDescription.setBounds(231, 167, 312, 151);
		taDescription.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblInvalid.setVisible(false);
			}
		});
		ContentPanel.add(taDescription);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bugType = String.valueOf(cbBugType.getSelectedItem());
				String des = taDescription.getText();
				
				if(des.isBlank() || des.isEmpty()) {
					lblInvalid.setVisible(true);
				}
				else {
					controller.report(user.getUserID(), des, bugType);
					
					BugListUI blUI = new BugListUI(user);
					blUI.setVisible(true);
					dispose();
				}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.setBounds(430, 423, 89, 23);
		ContentPanel.add(btnSubmit);
	}
	
	private void addMenuItems(String role, JMenu menu) {
		JMenuItem mntmBugList,mntmBugReport,mntmBugSearch,mntmLogout;
		
		mntmBugList = new JMenuItem("Bug list");
		mntmBugList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BugListUI blUI = new BugListUI(user);
				blUI.setVisible(true);
				dispose();
			}
		});
		menu.add(mntmBugList);
		
		mntmBugSearch = new JMenuItem("Bug search");
		mntmBugSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BugSearchUI bsUI = new BugSearchUI(user);
				bsUI.setVisible(true);
				dispose();
			}
		});
		menu.add(mntmBugSearch);
		
		mntmBugReport = new JMenuItem("Report a bug");
		mntmBugReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BugReportUI brUI = new BugReportUI(user);
				brUI.setVisible(true);
				dispose();
			}
		});
		menu.add(mntmBugReport);
		
		mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI lgUI = new LoginUI();
				lgUI.setVisible(true);
				dispose();
			}
		});
		menu.add(mntmLogout);
	}
}
