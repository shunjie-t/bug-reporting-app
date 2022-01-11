package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import entity.BugET;
import entity.CommentET;
import entity.UserET;

public class BugCommentCT {
	private CommentET comment = new CommentET();
	private BugET bug = new BugET();
	private UserET user;
	private int bugID;
	private int reporterID;
	
	public BugCommentCT(UserET obj, int bID) {
		user = obj;
		bugID = bID;
		reporterID = obj.getUserID();
	}
	
	public void setLabels(JLabel bID, JLabel titl, JLabel cate, JLabel stat, JLabel rpt, JLabel dev, JLabel date) {
		bID.setText(Integer.toString(bugID));
		titl.setText(bug.getDescription().get(bugID));
		cate.setText(bug.getBugType().get(bugID));
		stat.setText(bug.getStatus().get((bugID)));
		rpt.setText(user.getUsernameHM().get(bug.getReporterID().get(bugID)));
		dev.setText(user.getUsernameHM().get(bug.getDeveloperID().get(bugID)));
		date.setText(bug.getDate().get(bugID));
	}
	
	public void displayComment(JPanel partition) {
		HashMap<Integer, Integer> bID = new HashMap<Integer, Integer>();
		bID.putAll(comment.getBugID());
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
		
		for(int k : bID.keySet()) {
			if(bID.get(k) == bugID) {
				JPanel newPanel = new JPanel(new BorderLayout());
		        newPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
				partition.add(newPanel, gbc, 0);
				
				JPanel detailPanel = new JPanel(new BorderLayout());
		        newPanel.add(detailPanel, BorderLayout.NORTH);
		        
		        JLabel lblName = new JLabel();
		        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		        lblName.setText(user.getUsernameHM().get(comment.getUserID().get(k)));
		        detailPanel.add(lblName, BorderLayout.WEST);
		        
		        JLabel lblDate = new JLabel();
		        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		        lblDate.setText(comment.getDate().get(k));
		        detailPanel.add(lblDate, BorderLayout.EAST);
				
				JTextArea textarea = new JTextArea();
                textarea.setTabSize(2);
                textarea.setText(comment.getText().get(k));
                textarea.setEditable(false);
                newPanel.add(textarea, BorderLayout.CENTER);
			}
		}
	}
	
	public void addComment(JPanel partition, String cmnt) {
		JPanel newPanel = new JPanel(new BorderLayout());
        newPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        
        JPanel detailPanel = new JPanel(new BorderLayout());
        newPanel.add(detailPanel, BorderLayout.NORTH);
        
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
		Date dt = new Date(System.currentTimeMillis());
		String today = sdf.format(dt).toString();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        partition.add(newPanel, gbc, 0);
        
        JLabel lblName = new JLabel();
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setText(user.getUsernameHM().get(reporterID));
        detailPanel.add(lblName, BorderLayout.WEST);
        
        JLabel lblDate = new JLabel();
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDate.setText(today);
        detailPanel.add(lblDate, BorderLayout.EAST);
        
        JTextArea textarea = new JTextArea();
        textarea.setTabSize(2);
        textarea.setText(cmnt);
        textarea.setEditable(false);
        newPanel.add(textarea, BorderLayout.CENTER);
        
		comment.uploadDB(reporterID, bugID, cmnt);
	}
}
