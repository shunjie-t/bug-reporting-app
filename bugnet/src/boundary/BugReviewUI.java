package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.BugReviewCT;
import entity.UserET;

public class BugReviewUI extends JFrame{
	private UserET user;
	private BugReviewCT controller;
	
	public BugReviewUI(UserET obj, int bID) {
		user = obj;
		controller = new BugReviewCT(bID);
		
		initialize();
	}
	
	private void initialize() {
		JPanel MainPanel,HeaderPanel,BodyPanel,NavigationPanel,LeftNavPanel,MiddleNavPanel,RightNavPanel,ContentPanel,UsernamePanel,FillerPanel1;
		JLabel lblLogo,lblUsername,lblRole,lblTitle,lblBugID,lblID,lblBugDescription,lblDescription,lblBugStatus,lblStatus,lblBugType,lblType,lblBugReporter,lblReporter,lblBugDeveloper,lblDeveloper;
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
		
		lblTitle = new JLabel("Bug review");
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
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(570, 400, 90, 25);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(user.getRole().equals("Developer")) {
					AssignedBugsUI abUI = new AssignedBugsUI(user);
					abUI.setVisible(true);
					dispose();
				}
				else if(user.getRole().equals("Reviewer")) {
					ReviewListUI rlUI = new ReviewListUI(user);
					rlUI.setVisible(true);
					dispose();
				}
			}
		});
		ContentPanel.add(btnCancel);
		
		btnConfirm = new JButton("Reviewed");
		
		if(user.getRole().equals("Developer")) {
			btnConfirm = new JButton("Ready for review");
			
			btnConfirm.setBounds(670, 400, 130, 25);
			
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.rdyToReview();
					JOptionPane.showMessageDialog(null, "Bug successfully updated to Ready for Review.", "Updated bug status to Ready for Review", JOptionPane.INFORMATION_MESSAGE);
					AssignedBugsUI abUI = new AssignedBugsUI(user);
					abUI.setVisible(true);
					dispose();
				}
			});
			
			if(lblStatus.getText().equals("Active")) {
				ContentPanel.add(btnConfirm);
			}
		}
		
		if(user.getRole().equals("Reviewer")) {
			btnConfirm.setBounds(670, 400, 130, 25);
			
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.bugReviewed();
					JOptionPane.showMessageDialog(null, "Bug successfully updated to Reviewed.", "Updated bug status to Reviewed", JOptionPane.INFORMATION_MESSAGE);
					ReviewListUI rlUI = new ReviewListUI(user);
					rlUI.setVisible(true);
					dispose();
				}
			});
			
			if(lblStatus.getText().equals("Ready for Review")) {
				ContentPanel.add(btnConfirm);
			}
		}
		
	}
}
