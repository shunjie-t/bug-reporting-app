package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.LoginCT;

public class LoginUI extends JFrame {
	private LoginCT controller = new LoginCT();
	
	public LoginUI() {
		initialize();
	}
	
	private void initialize() {
		JPanel MainPanel,HeaderPanel,BodyPanel,NavigationPanel,LeftNavPanel,MiddleNavPanel,RightNavPanel,ContentPanel;
		JLabel lblLogo,lblTitle,lblUsername,lblPassword,lblUsernameError,lblPasswordError,lblWrongAccount;
		JTextField txtUsername;
		JPasswordField passwordField;
		JButton btnLogin;
		
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
	
		MiddleNavPanel = new JPanel();
		NavigationPanel.add(MiddleNavPanel, BorderLayout.CENTER);
	
		RightNavPanel = new JPanel();
		NavigationPanel.add(RightNavPanel, BorderLayout.EAST);
		
		ContentPanel = new JPanel();
		ContentPanel.setLayout(null);
		BodyPanel.add(ContentPanel, BorderLayout.CENTER);
		
		lblTitle = new JLabel("Login to your account");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(145, 94, 265, 36);
		ContentPanel.add(lblTitle);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(145, 156, 140, 30);
		ContentPanel.add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(145, 224, 140, 30);
		ContentPanel.add(lblPassword);
		
		lblUsernameError = new JLabel("Please fill up the username field");
		lblUsernameError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsernameError.setBounds(455, 186, 220, 25);
		lblUsernameError.setVisible(false);
		ContentPanel.add(lblUsernameError);
		
		lblPasswordError = new JLabel("Please fill up the password field");
		lblPasswordError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPasswordError.setBounds(455, 255, 220, 25);
		lblPasswordError.setVisible(false);
		ContentPanel.add(lblPasswordError);
		
		lblWrongAccount = new JLabel("Username or password is incorrect.");
		lblWrongAccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWrongAccount.setBounds(145, 313, 219, 23);
		lblWrongAccount.setVisible(false);
		ContentPanel.add(lblWrongAccount);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblUsernameError.setVisible(false);
				lblPasswordError.setVisible(false);
				lblWrongAccount.setVisible(false);
			}
		});
		txtUsername.setBounds(145, 187, 300, 30);
		ContentPanel.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblUsernameError.setVisible(false);
				lblPasswordError.setVisible(false);
				lblWrongAccount.setVisible(false);
			}
		});
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(145, 255, 300, 30);
		passwordField.setVisible(true);
		ContentPanel.add(passwordField);
		
		btnLogin = new JButton("Log in");
		getRootPane().setDefaultButton(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUsername.getText().length() == 0)
					lblUsernameError.setVisible(true);
				
				if(passwordField.getPassword().length == 0)
					lblPasswordError.setVisible(true);
				
				if(txtUsername.getText().length() != 0 && passwordField.getPassword().length != 0) {
					if(controller.login(txtUsername.getText(), passwordField.getPassword())) {
						// if login is successful, login() opens a new pane and dispose() will close current window pane.
						dispose();
					}
					else {
						lblWrongAccount.setVisible(true);
					}
				}
			}
		});
		btnLogin.setBounds(354, 373, 89, 23);
		ContentPanel.add(btnLogin);
	}
}
