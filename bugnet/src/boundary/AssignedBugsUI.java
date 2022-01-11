package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.AssignedBugsCT;
import entity.UserET;

public class AssignedBugsUI extends JFrame{
	private AssignedBugsCT controller;
	private UserET user;
	private int selectedBugID;
	private Object[][] allBugs;
	private String[] colNames = {"BugID", "BugType", "Status", "Description"};
	
	public AssignedBugsUI(UserET obj) {
		user = obj;
		controller = new AssignedBugsCT();
		allBugs = controller.getAsnBugs(user.getUserID());
		
		initialize();
	}
	
	private void initialize() {
		JPanel MainPanel,HeaderPanel,BodyPanel,NavigationPanel,LeftNavPanel,MiddleNavPanel,RightNavPanel,ContentPanel,UsernamePanel,FillerPanel1,DetailPanel,ListPanel;
		JLabel lblLogo,lblUsername,lblRole,lblTitle,lblBugsFound;
		JMenuBar mbNavigation;
		JMenu mnGoto;
		JMenuItem mntmBugList,mntmBugSearch,mntmAssignedBugs,mntmLogout;
		GridBagLayout gbl_ContentPanel;
		JScrollPane scrollPane;
		GridBagConstraints gbc_DetailPanel,gbc_ListPanel;
		JPopupMenu popupMenu = new JPopupMenu();
		JTable table;
		
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
	
		mntmBugList = new JMenuItem("Bug list");
		mntmBugList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BugListUI blUI = new BugListUI(user);
				blUI.setVisible(true);
				dispose();
			}
		});
		mnGoto.add(mntmBugList);
	
		mntmBugSearch = new JMenuItem("Bug search");
		mntmBugSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BugSearchUI bsUI = new BugSearchUI(user);
				bsUI.setVisible(true);
				dispose();
			}
		});
		mnGoto.add(mntmBugSearch);
		
		mntmAssignedBugs = new JMenuItem("Assigned Bugs");
		mntmAssignedBugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AssignedBugsUI abUI = new AssignedBugsUI(user);
				abUI.setVisible(true);
				dispose();
			}
		});
		mnGoto.add(mntmAssignedBugs);		
		
		mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI lgUI = new LoginUI();
				lgUI.setVisible(true);
				dispose();
			}
		});
		mnGoto.add(mntmLogout);
		
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
		BodyPanel.add(ContentPanel, BorderLayout.CENTER);
		
		gbl_ContentPanel = new GridBagLayout();
		gbl_ContentPanel.columnWidths = new int[]{0, 0};
		gbl_ContentPanel.rowHeights = new int[]{0, 0};
		gbl_ContentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_ContentPanel.rowWeights = new double[]{1.0, 6.0};
		ContentPanel.setLayout(gbl_ContentPanel);
		
		DetailPanel = new JPanel();
		gbc_DetailPanel = new GridBagConstraints();
		gbc_DetailPanel.insets = new Insets(0, 0, 5, 0);
		gbc_DetailPanel.fill = GridBagConstraints.BOTH;
		gbc_DetailPanel.gridx = 0;
		gbc_DetailPanel.gridy = 0;
		DetailPanel.setLayout(new GridLayout(2, 1, 0, 0));
		ContentPanel.add(DetailPanel, gbc_DetailPanel);
		
		lblTitle = new JLabel("Assigned bugs");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		DetailPanel.add(lblTitle);
		
		lblBugsFound = new JLabel(Integer.toString(allBugs.length) + " assigned bugs.");
		lblBugsFound.setFont(new Font("Tahoma", Font.PLAIN, 13));
		DetailPanel.add(lblBugsFound);
		
		ListPanel = new JPanel();
		gbc_ListPanel = new GridBagConstraints();
		gbc_ListPanel.fill = GridBagConstraints.BOTH;
		gbc_ListPanel.gridx = 0;
		gbc_ListPanel.gridy = 1;
		ContentPanel.add(ListPanel, gbc_ListPanel);
		ListPanel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		ListPanel.add(scrollPane, BorderLayout.CENTER);
		
		rightClickMenu(popupMenu);
		
		table = new JTable(allBugs, colNames);
		table.setModel(new DefaultTableModel(allBugs, colNames) {
			// make the table uneditable
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setComponentPopupMenu(popupMenu);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectedBugID = (int) table.getValueAt(table.rowAtPoint(e.getPoint()), 0);
			}
		});
		scrollPane.setViewportView(table);
	}
	
	private void rightClickMenu(JPopupMenu pm) {
		JMenuItem rdyToReview;
		
		rdyToReview = new JMenuItem("Ready for Review");
		rdyToReview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BugReviewUI brUI = new BugReviewUI(user, selectedBugID);
				brUI.setVisible(true);
				dispose();
			}
		});
		
		if(pm != null)
			pm.add(rdyToReview);
	}
}
