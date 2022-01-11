package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.BugAssignCT;
import entity.UserET;

public class BugAssignUI extends JFrame {
	private UserET user;
	private BugAssignCT controller;
	
	private Object[][] bugRow;
	private String[] bugCol;
	
	private Object[][] devRow;
	private String[] devCol;
	
	private Object[][] asgnBugRow;
	
	private int developerID = 0; 
	
	public BugAssignUI(UserET obj) {
		user = obj;
		controller = new BugAssignCT(user);
		bugRow = controller.getBugRows();
		bugCol = controller.getBugColumns();
		
		devRow = controller.getUserRows();
		devCol = controller.getUserColumns();
		
		asgnBugRow = new Object[0][bugCol.length];
		
		initialize();
	}
	
	private void initialize() {
		JPanel MainPanel,HeaderPanel,BodyPanel,NavigationPanel,LeftNavPanel,MiddleNavPanel,RightNavPanel,ContentPanel,UsernamePanel,FillerPanel1,DeveloperPanel,BugPanel,AssignPanel;
		JLabel lblLogo,lblUsername,lblRole,lblTitle,lblSearchDev,lblSearchBug,lblSelectDev,lblSelectBug,lblBugsToBe;
		JLabel lblDeveloper = new JLabel();
		JLabel lblError = new JLabel();
		JMenuBar mbNavigation;
		JMenu mnGoto;
		JScrollPane scrollPane,DeveloperScrollPane,BugScrollPane,AssignScrollPane;
		JTextField tfDeveloper,tfBug;
		JTable tbDeveloper = new JTable(devRow, devCol);
		JTable tbBug = new JTable(bugRow, bugCol);
		JTable tbAssign = new JTable(new DefaultTableModel(asgnBugRow, bugCol));
		JButton btnAdd,btnClear,btnConfirm;
		
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
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		BodyPanel.add(scrollPane, BorderLayout.CENTER);
		
		ContentPanel = new JPanel();
		ContentPanel.setPreferredSize(new Dimension(1000, 950));
		scrollPane.setViewportView(ContentPanel);
		ContentPanel.setLayout(null);
		
		lblTitle = new JLabel("Assign bugs to a developer");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(20, 40, 340, 30);
		ContentPanel.add(lblTitle);
		
		lblSelectDev = new JLabel("Select a developer to assign");
		lblSelectDev.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectDev.setBounds(40, 100, 190, 25);
		ContentPanel.add(lblSelectDev);
		
		lblSelectBug = new JLabel("Select bugs to assign");
		lblSelectBug.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectBug.setBounds(540, 100, 170, 25);
		ContentPanel.add(lblSelectBug);
		
		DeveloperPanel = new JPanel();
		DeveloperPanel.setBounds(40, 130, 400, 250);
		ContentPanel.add(DeveloperPanel);
		DeveloperPanel.setLayout(new BorderLayout(0, 0));
		
		DeveloperScrollPane = new JScrollPane();
		DeveloperPanel.add(DeveloperScrollPane, BorderLayout.CENTER);
		
		tbDeveloper.setModel(new DefaultTableModel(devRow, devCol) {
			// make the table uneditable
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tbDeveloper.setCellSelectionEnabled(true);
		tbDeveloper.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDeveloper.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tbDeveloper.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String devName = tbDeveloper.getValueAt(tbDeveloper.rowAtPoint(e.getPoint()), 1).toString();
				lblDeveloper.setText(devName);
				
				developerID = (int) tbDeveloper.getValueAt(tbDeveloper.rowAtPoint(e.getPoint()), 0);
			}
		});
		DeveloperScrollPane.setViewportView(tbDeveloper);
		
		BugPanel = new JPanel();
		BugPanel.setBounds(540, 130, 400, 250);
		ContentPanel.add(BugPanel);
		BugPanel.setLayout(new BorderLayout(0, 0));
		
		BugScrollPane = new JScrollPane();
		BugPanel.add(BugScrollPane, BorderLayout.CENTER);
		
		tbBug.setModel(new DefaultTableModel(bugRow, bugCol) {
			// make the table uneditable
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tbBug.setCellSelectionEnabled(true);
		tbBug.setFont(new Font("Tahoma", Font.PLAIN, 13));
		BugScrollPane.setViewportView(tbBug);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(830, 411, 90, 25);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> assigned = new ArrayList<Integer>();
				ArrayList<Integer> selected = new ArrayList<Integer>();
				ArrayList<Integer> filtered = new ArrayList<Integer>();
				Object[][] tempRow = controller.getBugRows();
				int i = 0;
				
				int[] rowIdx = tbBug.getSelectedRows();
				
				for(int a = 0; a < asgnBugRow.length; a++) {
					assigned.add((int) asgnBugRow[a][0]);
				}
				
				for(int a : rowIdx) {
					selected.add((int) bugRow[a][0]);
				}
				
				for(int id : selected) {
					if(!assigned.contains(id)) {
						filtered.add(id);
					}
				}
				
				assigned.addAll(filtered);
				
				asgnBugRow = new Object[assigned.size()][bugCol.length];
				
				for(int a = 0; a < tempRow.length; a++) {
					for(int b : assigned) {
						if(b == (int) tempRow[a][0]) {
							asgnBugRow[i][0] = tempRow[a][0];
							asgnBugRow[i][1] = tempRow[a][1];
							asgnBugRow[i][2] = tempRow[a][2];
							asgnBugRow[i][3] = tempRow[a][3];
							i++;
						}
					}
				}
				
				updateTable(tbAssign, asgnBugRow);
			}
		});
		ContentPanel.add(btnAdd);
		
		lblBugsToBe = new JLabel("Bugs to be assigned to:");
		lblBugsToBe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBugsToBe.setBounds(60, 525, 143, 25);
		ContentPanel.add(lblBugsToBe);
		
		lblDeveloper.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDeveloper.setBounds(200, 525, 80, 25);
		ContentPanel.add(lblDeveloper);
		
		AssignPanel = new JPanel();
		AssignPanel.setBounds(60, 560, 860, 220);
		ContentPanel.add(AssignPanel);
		AssignPanel.setLayout(new BorderLayout(0, 0));
		
		AssignScrollPane = new JScrollPane();
		AssignPanel.add(AssignScrollPane, BorderLayout.CENTER);
		
		AssignScrollPane.setViewportView(tbAssign);
		
		lblError.setBounds(300, 880, 800, 25);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ContentPanel.add(lblError);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(asgnBugRow.length == 0 && lblDeveloper.getText().equals("")) {
					lblError.setText("Unable to assign, developer and bugs not selected.");
				}
				else if(asgnBugRow.length == 0) {
					lblError.setText("Unable to assign, bugs not selected.");
				}
				else if(lblDeveloper.getText().equals("")) {
					lblError.setText("Unable to assign, developer not selected.");
				}
				else if(!lblDeveloper.getText().equals("") && asgnBugRow.length != 0) {
					controller.assignBugs(developerID, asgnBugRow);
					JOptionPane.showMessageDialog(null, "Bug successfully assigned to " + lblDeveloper.getText() + ".", "Bug assigned", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnConfirm.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				lblError.setText("");
			}
		});
		btnConfirm.setBounds(780, 880, 90, 25);
		ContentPanel.add(btnConfirm);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asgnBugRow = new Object[0][bugCol.length];
				
				DefaultTableModel model = (DefaultTableModel) tbAssign.getModel();
				model.setRowCount(0);
				for(Object[] a : asgnBugRow) {
					model.addRow(a);
				}
			}
		});
		btnClear.setBounds(800, 791, 90, 25);
		ContentPanel.add(btnClear);
	}
	
	private void addMenuItems(String role, JMenu menu) {
		JMenuItem mntmBugList,mntmBugSearch,mntmBugAssign,mntmLogout;
		
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
		
		mntmBugAssign = new JMenuItem("Bug assign");
		mntmBugAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BugAssignUI baUI = new BugAssignUI(user);
				baUI.setVisible(true);
				dispose();
			}
		});
		menu.add(mntmBugAssign);
		
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
	
	private void updateTable(JTable table, Object[][] row) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for(Object[] a : row) {
			model.addRow(a);
		}
	}
}
