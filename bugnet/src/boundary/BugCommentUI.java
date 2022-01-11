package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.ScrollPaneConstants;

import entity.UserET;
import controller.BugCommentCT;

public class BugCommentUI extends JFrame {
	private UserET user;
	private BugCommentCT controller;
	
	public BugCommentUI(UserET obj, int bugID) {
		user = obj;
		controller = new BugCommentCT(user, bugID);
		
		initialize();
	}

	private void initialize() {
		JPanel MainPanel,HeaderPanel,BodyPanel,NavigationPanel,LeftNavPanel,MiddleNavPanel,RightNavPanel,ContentPanel,UsernamePanel,FillerPanel1,DetailPanel,PartitionPanel,TextAreaPanel;
		JLabel lblLogo,lblUsername,lblRole,lblBugIdInd,lblBugId,lblTitleInd,lblTitle,lblCategoryInd,lblCategory,lblStatusInd,lblStatus,lblReporterInd,lblReporter,lblAssigneeInd,lblAssignee,lblDateInd,lblDate;
		JScrollPane scrollPane, spTextArea;
		JTextArea taComment;
		JButton btnAddComment;
		GridBagConstraints gbc = new GridBagConstraints();
		
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
		ContentPanel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		ContentPanel.add(scrollPane, BorderLayout.CENTER);
		
		DetailPanel = new JPanel();
		scrollPane.setColumnHeaderView(DetailPanel);
		DetailPanel.setLayout(new GridLayout(0, 2, 0, 0));
		DetailPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
		
		lblBugIdInd = new JLabel("Bug:");
		lblBugIdInd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblBugIdInd);
		
		lblBugId = new JLabel("<bugID>");
		lblBugId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblBugId);
		
		lblTitleInd = new JLabel("Title:");
		lblTitleInd.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitleInd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblTitleInd);
		
		lblTitle = new JLabel("<title>");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblTitle);
		
		lblCategoryInd = new JLabel("Category:");
		lblCategoryInd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblCategoryInd);
		
		lblCategory = new JLabel("<category>");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblCategory);
		
		lblStatusInd = new JLabel("Status:");
		lblStatusInd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblStatusInd);
		
		lblStatus = new JLabel("<status>");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblStatus);
		
		lblReporterInd = new JLabel("Reporter:");
		lblReporterInd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblReporterInd);
		
		lblReporter = new JLabel("<username>");
		lblReporter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblReporter);
		
		lblAssigneeInd = new JLabel("Assignee:");
		lblAssigneeInd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblAssigneeInd);
		
		lblAssignee = new JLabel("<username>");
		lblAssignee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblAssignee);
		
		lblDateInd = new JLabel("Date:");
		lblDateInd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblDateInd);
		
		lblDate = new JLabel("<date>");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DetailPanel.add(lblDate);
		
		// display the information for the labels in DetailPanel base on the bug ID.
		controller.setLabels(lblBugId, lblTitle, lblCategory, lblStatus, lblReporter, lblAssignee, lblDate);
		
		PartitionPanel = new JPanel();
        scrollPane.setViewportView(PartitionPanel);
        PartitionPanel.setLayout(new GridBagLayout());
        
        // populate comments from database.
        controller.displayComment(PartitionPanel);
        
        spTextArea = new JScrollPane();
        spTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        PartitionPanel.add(spTextArea, gbc);
        
        TextAreaPanel = new JPanel();
        TextAreaPanel.setBorder(new TitledBorder("Enter a comment"));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        TextAreaPanel.setLayout(new GridBagLayout());
        spTextArea.setViewportView(TextAreaPanel);
        
        taComment = new JTextArea();
        taComment.setFont(new Font("Dialog", Font.PLAIN, 14));
        taComment.setLineWrap(true);
        taComment.setPreferredSize(new Dimension(700, 180));
        TextAreaPanel.add(taComment, gbc);
        
        btnAddComment = new JButton("Add comment");
        btnAddComment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {               
        		String comment = taComment.getText();
        		String temp;
        		int begin = 0, end = 120;
        		char[] commentChars = comment.toCharArray();
        		
        		if(!comment.isBlank() || !comment.isEmpty()) {
        			while(comment.length() > end) {
            			temp = comment.substring(begin, end);
            			commentChars[temp.lastIndexOf(" ")] = '\n';
            			
            			begin = end;
            			end += 120;
            		}
            		comment = String.valueOf(commentChars).trim();
            		
            		controller.addComment(PartitionPanel, comment);
            		
            		taComment.setText("");
                    validate();
        		}
        	}
        });
        gbc.fill = 60;
        TextAreaPanel.add(btnAddComment, gbc);
	}
}
