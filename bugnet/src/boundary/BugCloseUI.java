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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.BugCloseCT;
import entity.UserET;

public class BugCloseUI extends JFrame {
	private UserET user;
	private BugCloseCT controller;
	
	public BugCloseUI(UserET obj, int bID) {
		user = obj;
		controller = new BugCloseCT(bID, user);
		
		initialize();
	}
	
	private void initialize() {
		JPanel MainPanel,HeaderPanel,BodyPanel,NavigationPanel,LeftNavPanel,MiddleNavPanel,RightNavPanel,ContentPanel,UsernamePanel,FillerPanel1;
		JLabel lblLogo,lblUsername,lblRole,lblTitle,lblBugID,lblID,lblBugDescription,lblDescription,lblBugStatus,lblStatus,lblBugType,lblType,lblBugReporter,lblReporter,lblBugDeveloper,lblDeveloper,lblError;
		JButton btnCancel,btnConfirm;
		
		setTitle("Bugnet");
		setMinimumSize(new Dimension(1000, 600));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
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
		ContentPanel.setLayout(null);
		
		lblTitle = new JLabel("Close a bug");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(45, 20, 160, 30);
		ContentPanel.add(lblTitle);
		
		lblBugID = new JLabel("Bug ID:");
		lblBugID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBugID.setBounds(95, 70, 50, 25);
		ContentPanel.add(lblBugID);
		
		lblID = new JLabel("<ID>");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblID.setBounds(220, 70, 700, 25);
		ContentPanel.add(lblID);
		
		lblBugDescription = new JLabel("Bug description:");
		lblBugDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBugDescription.setBounds(95, 110, 110, 25);
		ContentPanel.add(lblBugDescription);
		
		lblDescription = new JLabel("<description>");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescription.setBounds(220, 110, 700, 25);
		ContentPanel.add(lblDescription);
		
		lblBugStatus = new JLabel("Bug status:");
		lblBugStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBugStatus.setBounds(95, 150, 110, 25);
		ContentPanel.add(lblBugStatus);
		
		lblStatus = new JLabel("<status>");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatus.setBounds(220, 150, 700, 25);
		ContentPanel.add(lblStatus);
		
		lblBugType = new JLabel("Bug type:");
		lblBugType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBugType.setBounds(95, 190, 110, 25);
		ContentPanel.add(lblBugType);
		
		lblType = new JLabel("<type>");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblType.setBounds(220, 190, 700, 25);
		ContentPanel.add(lblType);
		
		lblBugReporter = new JLabel("Reporter:");
		lblBugReporter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBugReporter.setBounds(95, 230, 110, 25);
		ContentPanel.add(lblBugReporter);
		
		lblReporter = new JLabel("<reporter>");
		lblReporter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReporter.setBounds(220, 230, 700, 25);
		ContentPanel.add(lblReporter);
		
		lblBugDeveloper = new JLabel("Assigned developer:");
		lblBugDeveloper.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBugDeveloper.setBounds(95, 270, 110, 25);
		ContentPanel.add(lblBugDeveloper);
		
		lblDeveloper = new JLabel("<developer>");
		lblDeveloper.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDeveloper.setBounds(220, 270, 700, 25);
		ContentPanel.add(lblDeveloper);
		
		controller.setLabels(lblID, lblDescription, lblStatus, lblType, lblReporter, lblDeveloper);
		
		lblError = new JLabel("Cannot close bug that is not 'Reviewed' status");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblError.setBounds(270, 360, 280, 25);
		lblError.setVisible(false);
		ContentPanel.add(lblError);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(570, 400, 90, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		ContentPanel.add(btnCancel);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(670, 400, 90, 25);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lblStatus.getText().equals("Reviewed")) {
					controller.closeBug();
					JOptionPane.showMessageDialog(null, "Bug successfully updated to Closed.", "Updated bug status to Closed", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					lblError.setVisible(true);
				}
			}
		});
		btnConfirm.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				lblError.setVisible(false);
			}
		});
		ContentPanel.add(btnConfirm);
	}
}
