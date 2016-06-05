package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.*;

//Contains views for menu, including login, register and admin settings
//Maintains constant header and footer areas

public class MenuView extends JPanel {
	
	//private VoteView voteView;
	
	//JPanels
	private JPanel headerPanel;
	private JPanel headerWest;
	private JPanel headerEast;
	private JPanel mainPanel;
	private JPanel loginRegisterPanel;
	private JPanel loginPanel;
	private JPanel registerPanel;
	private JPanel footerPanel;
	private JPanel footerWest;
	private JPanel footerEast;
	private JPanel loginUserPanel;
	private JPanel loginPassPanel;
	private JPanel registerFirstPanel;
	private JPanel registerLastPanel;
	private JPanel registerIdPanel;
	private JPanel registerUserPanel;
	private JPanel registerPassPanel;
	private JPanel adminLoginPanel;
	private JPanel adminUserPanel;
	private JPanel adminPassPanel;
	private JPanel loggedInPanel;
	private JPanel adminPanel;
	private JPanel adminSettings;
	private JPanel adminTally;
	
	public JPanel mainCards;
	public JPanel loginRegisterCards;
	public JPanel adminCards;
	
	//JButtons
	private JButton backAdmin;
	private JButton backLogin;
	private JButton backRegister;
	private JButton backTally;
	private JButton exit;
	private JButton admin;
	private JButton ok;
	private JButton loginConfirm;
	private JButton registerConfirm;
	private JButton vote;
	private JButton logout;
	private JButton adminLogin;
	
	private JButton login;
	private JButton register;
	
	private JButton openRegistration;
	private JButton openVoting;
	private JButton closeVoting;
	private JButton tallyVotes;
	private JButton restartVoting;
	
	
	
	//JLabels
	private JLabel username;
	private JLabel logo;
	private JLabel adminLogo;
	private JLabel titleMessage;
	private JLabel adminTitleMessage;
	private JLabel loginUser;
	private JLabel loginPass;
	private JLabel adminUser;
	private JLabel adminPass;
	private JLabel numberOfVoters;
	private JLabel tallyTitle;
	
	private JLabel regUser;
	private JLabel regPass;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel id;
	
	//JTextFields
	private JTextField userField;
	private JPasswordField passField;
	
	private JTextField regUserField;
	private JTextField regPassField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField idField;

	private JTextField adminUserField;
	private JPasswordField adminPassField;
	
	//Layouts
	CardLayout mainLayout;
	CardLayout loginRegisterLayout;
	CardLayout adminLayout;
	

	public MenuView(ActionListener menuButtonListener, VoteView voteView) {
		//JPanel setup
		headerPanel = new JPanel();
		headerWest = new JPanel();
		headerEast = new JPanel();
		mainPanel = new JPanel();
		loginRegisterPanel = new JPanel();
		loginPanel = new JPanel();
		loginUserPanel = new JPanel();
		loginPassPanel = new JPanel();
		registerUserPanel = new JPanel();
		registerPassPanel = new JPanel();
		registerFirstPanel = new JPanel();
		registerLastPanel = new JPanel();
		registerIdPanel = new JPanel();
		registerPanel = new JPanel();
		footerPanel = new JPanel();
		footerEast = new JPanel();
		footerWest = new JPanel();
		mainCards = new JPanel();
		loginRegisterCards = new JPanel();
		loggedInPanel = new JPanel();
		adminPanel = new JPanel();
		adminLoginPanel = new JPanel();
		adminUserPanel = new JPanel();
		adminPassPanel = new JPanel();
		adminCards = new JPanel();
		adminSettings = new JPanel();
		adminTally = new JPanel();
		
		this.setPreferredSize(new Dimension (1024, 640));
		this.setBackground(new Color(102, 224, 255));
		
		headerPanel.setPreferredSize(new Dimension(1024, 40));
		headerWest.setLayout(new FlowLayout(FlowLayout.LEFT));
		headerWest.setPreferredSize(new Dimension(507,40));
		headerEast.setLayout(new FlowLayout(FlowLayout.RIGHT));
		headerEast.setPreferredSize(new Dimension(507,40));
		
		mainPanel.setPreferredSize(new Dimension(1024, 540));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		adminPanel.setPreferredSize(new Dimension(1024, 540));
		adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
		
		adminTally.setLayout(new BoxLayout(adminTally, BoxLayout.Y_AXIS));
		
		adminPanel.setPreferredSize(new Dimension(1024, 540));
		adminSettings.setLayout(new BoxLayout(adminSettings, BoxLayout.Y_AXIS));
		
		
		loggedInPanel.setPreferredSize(new Dimension(1024, 90));
		loggedInPanel.setLayout(new BoxLayout(loggedInPanel, BoxLayout.Y_AXIS));
		
		loginRegisterPanel.setLayout(new BoxLayout(loginRegisterPanel, BoxLayout.Y_AXIS));
		
		loginPanel.setPreferredSize(new Dimension(1024, 180));
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		
		registerPanel.setPreferredSize(new Dimension(1024, 180));
		registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
		
		loginUserPanel.setSize(new Dimension(1024, 40));
		loginPassPanel.setSize(new Dimension(1024, 40));
		adminUserPanel.setSize(new Dimension(1024, 40));
		adminPassPanel.setSize(new Dimension(1024, 40));
		registerFirstPanel.setSize(new Dimension(1024, 40));
		registerLastPanel.setSize(new Dimension(1024, 40));
		registerIdPanel.setSize(new Dimension(1024, 40));
		
		footerPanel.setPreferredSize(new Dimension(1024, 40));
		footerWest.setLayout(new FlowLayout(FlowLayout.LEFT));
		footerWest.setPreferredSize(new Dimension(507,40));
		footerEast.setLayout(new FlowLayout(FlowLayout.RIGHT));
		footerEast.setPreferredSize(new Dimension(507,40));
		
		//Remove panel backgrounds
		headerPanel.setOpaque(false);
		headerWest.setOpaque(false);
		headerEast.setOpaque(false);
		mainPanel.setOpaque(false);
		loginPanel.setOpaque(false);
		loginUserPanel.setOpaque(false);
		loginPassPanel.setOpaque(false);
		registerUserPanel.setOpaque(false);
		registerPassPanel.setOpaque(false);
		registerFirstPanel.setOpaque(false);
		registerLastPanel.setOpaque(false);
		registerIdPanel.setOpaque(false);
		registerPanel.setOpaque(false);
		footerPanel.setOpaque(false);
		footerEast.setOpaque(false);
		footerWest.setOpaque(false);
		mainCards.setOpaque(false);
		loginRegisterPanel.setOpaque(false);
		loginRegisterCards.setOpaque(false);
		loggedInPanel.setOpaque(false);
		adminPanel.setOpaque(false);
		adminLoginPanel.setOpaque(false);
		adminUserPanel.setOpaque(false);
		adminPassPanel.setOpaque(false);
		adminCards.setOpaque(false);
		adminSettings.setOpaque(false);
		adminTally.setOpaque(false);
		
		//CardLayout setup
		mainCards.setLayout(new CardLayout());
		mainLayout = (CardLayout) mainCards.getLayout();
		loginRegisterCards.setLayout(new CardLayout());
		loginRegisterLayout = (CardLayout) loginRegisterCards.getLayout();
		adminCards.setLayout(new CardLayout());
		adminLayout = (CardLayout) adminCards.getLayout();
		
		//Image setup
		ImageIcon exitIcon = new ImageIcon("bin/images/exit2.png");
        Image exitImage = exitIcon.getImage() ;  
        exitImage = exitImage.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
        exitIcon.setImage(exitImage);
		
		//JButton setup
		backAdmin = new JButton("Back");
		backLogin = new JButton("Back");
		backRegister = new JButton("Back");
		backTally = new JButton("Back");
		exit = new JButton();
		admin = new JButton("Admin");
		ok = new JButton("Ok");
		register = new JButton("Register");
		login = new JButton ("Login");
		loginConfirm = new JButton ("Login");
		registerConfirm = new JButton("Register");
		vote = new JButton("Vote");
		logout = new JButton("Logout");
		adminLogin = new JButton("Admin Login");
		openVoting = new JButton("Open Voting");
		closeVoting = new JButton("Close Voting");
		tallyVotes = new JButton("Tally Votes");
		openRegistration = new JButton("Open Registration");
		
		backAdmin.setName("backadmin");
		backLogin.setName("backlogin");
		backRegister.setName("backregister");
		backTally.setName("backtally");
		exit.setName("exit");
		loginConfirm.setName("loginconfirm");
		registerConfirm.setName("registerconfirm");
		exit.setIcon(exitIcon);
		admin.setName("admin");
		ok.setName("ok");
		vote.setName("vote");
		register.setName("register");
		login.setName("login");
		logout.setName("logout");
		adminLogin.setName("adminloginconfirm");
		openVoting.setName("openvoting");
		openRegistration.setName("openregistration");
		closeVoting.setName("closevoting");
		tallyVotes.setName("tallyvotes");
		
		register.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		vote.setAlignmentX(Component.CENTER_ALIGNMENT);
		logout.setAlignmentX(Component.CENTER_ALIGNMENT);
		openVoting.setAlignmentX(Component.CENTER_ALIGNMENT);
		closeVoting.setAlignmentX(Component.CENTER_ALIGNMENT);
		tallyVotes.setAlignmentX(Component.CENTER_ALIGNMENT);
		openRegistration.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		backAdmin.addActionListener(menuButtonListener);
		backLogin.addActionListener(menuButtonListener);
		backRegister.addActionListener(menuButtonListener);
		backTally.addActionListener(menuButtonListener);
		exit.addActionListener(menuButtonListener);
		admin.addActionListener(menuButtonListener);
		ok.addActionListener(menuButtonListener);
		register.addActionListener(menuButtonListener);
		login.addActionListener(menuButtonListener);
		loginConfirm.addActionListener(menuButtonListener);
		registerConfirm.addActionListener(menuButtonListener);
		adminLogin.addActionListener(menuButtonListener);
		logout.addActionListener(menuButtonListener);
		vote.addActionListener(menuButtonListener);
		openVoting.addActionListener(menuButtonListener);
		openRegistration.addActionListener(menuButtonListener);
		closeVoting.addActionListener(menuButtonListener);
		tallyVotes.addActionListener(menuButtonListener);
		
		
		//JLabel setup
		username = new JLabel("Not logged in");
		username.setForeground(Color.WHITE);
		
		tallyTitle = new JLabel("Calculating vote tally");
		tallyTitle.setForeground(Color.WHITE);
		
		ImageIcon logoImg = new ImageIcon("bin/images/logo.png");
		logo = new JLabel();
		logo.setIcon(logoImg);
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		adminLogo = new JLabel();
		adminLogo.setIcon(logoImg);
		adminLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		titleMessage = new JLabel("Welcome to e-Vote");
		titleMessage.setForeground(Color.WHITE);
		titleMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleMessage.setFont(new Font (titleMessage.getText(), Font.PLAIN, 20));
		
		adminTitleMessage = new JLabel("Admin Login");
		adminTitleMessage.setForeground(Color.WHITE);
		adminTitleMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		adminTitleMessage.setFont(new Font (adminTitleMessage.getText(), Font.PLAIN, 20));
		
		loginUser = new JLabel("Username: ");
		loginPass = new JLabel("Password: ");
		loginUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginPass.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginUser.setForeground(Color.WHITE);
		loginPass.setForeground(Color.WHITE);
		
		adminUser = new JLabel("Username: ");
		adminPass = new JLabel("Password: ");
		adminUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		adminPass.setAlignmentX(Component.CENTER_ALIGNMENT);
		adminUser.setForeground(Color.WHITE);
		adminPass.setForeground(Color.WHITE);
		
		numberOfVoters = new JLabel("Number of voters: ");
		numberOfVoters.setForeground(Color.WHITE);
		numberOfVoters.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		regUser = new JLabel("Username: ");
		regPass = new JLabel("Password: ");
		regUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		regPass.setAlignmentX(Component.CENTER_ALIGNMENT);
		regUser.setForeground(Color.WHITE);
		regPass.setForeground(Color.WHITE);
		firstName = new JLabel("First name: ");
		firstName.setForeground(Color.WHITE);
		firstName.setAlignmentX(Component.CENTER_ALIGNMENT);
		lastName = new JLabel("Last name: ");
		lastName.setForeground(Color.WHITE);
		lastName.setAlignmentX(Component.CENTER_ALIGNMENT);
		id = new JLabel("ID number: ");
		id.setForeground(Color.WHITE);
		id.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		//JTextFieldSetup
		userField = new JTextField();
		passField = new JPasswordField();
		regUserField = new JTextField();
		regPassField = new JPasswordField();
		firstNameField = new JTextField();
		lastNameField = new JTextField();
		idField = new JTextField();
		regUserField.setColumns(10);
		regPassField.setColumns(10);
		userField.setColumns(10);
		passField.setColumns(10);
		firstNameField.setColumns(10);
		lastNameField.setColumns(10);
		idField.setColumns(10);
		
		
		adminUserField = new JTextField();
		adminPassField = new JPasswordField();
		adminUserField.setColumns(10);
		adminPassField.setColumns(10);
		
		
		//Build Panels
		headerWest.add(username);
		headerEast.add(exit);
		headerPanel.add(headerWest);
		headerPanel.add(headerEast);
		
		mainPanel.add(Box.createRigidArea(new Dimension(1,20)));
		mainPanel.add(logo);
		mainPanel.add(Box.createRigidArea(new Dimension(1,20)));
		mainPanel.add(titleMessage);
		mainPanel.add(Box.createRigidArea(new Dimension(1,20)));
		mainPanel.add(loginRegisterCards);
		
		loginRegisterPanel.add(login);
		loginRegisterPanel.add(Box.createRigidArea(new Dimension(50,20)));
		loginRegisterPanel.add(register);
		
		loginUserPanel.add(loginUser);
		loginUserPanel.add(Box.createRigidArea(new Dimension(10,1)));
		loginUserPanel.add(userField);
		
		loginPassPanel.add(loginPass);
		loginPassPanel.add(Box.createRigidArea(new Dimension(10,1)));
		loginPassPanel.add(passField);
		
		loginPanel.add(loginUserPanel);
		loginPanel.add(Box.createRigidArea(new Dimension(1,5)));
		loginPanel.add(loginPassPanel);
		loginPanel.add(Box.createRigidArea(new Dimension(1,200)));
		
		registerUserPanel.add(regUser);
		registerUserPanel.add(Box.createRigidArea(new Dimension(10,1)));
		registerUserPanel.add(regUserField);
		
		registerPassPanel.add(regPass);
		registerPassPanel.add(Box.createRigidArea(new Dimension(10,1)));
		registerPassPanel.add(regPassField);
		
		registerFirstPanel.add(firstName);
		registerFirstPanel.add(Box.createRigidArea(new Dimension(10,1)));
		registerFirstPanel.add(firstNameField);
		
		registerLastPanel.add(lastName);
		registerLastPanel.add(Box.createRigidArea(new Dimension(10,1)));
		registerLastPanel.add(lastNameField);
		
		registerIdPanel.add(id);
		registerIdPanel.add(Box.createRigidArea(new Dimension(10,1)));
		registerIdPanel.add(idField);
		
		registerPanel.add(registerFirstPanel);
		registerPanel.add(Box.createRigidArea(new Dimension(1,5)));
		registerPanel.add(registerLastPanel);
		registerPanel.add(Box.createRigidArea(new Dimension(1,5)));
		registerPanel.add(registerIdPanel);
		registerPanel.add(Box.createRigidArea(new Dimension(1,5)));
		registerPanel.add(registerUserPanel);
		registerPanel.add(Box.createRigidArea(new Dimension(1,5)));
		registerPanel.add(registerPassPanel);
		
		loggedInPanel.add(vote);
		loginPanel.add(Box.createRigidArea(new Dimension(100,50)));
		loggedInPanel.add(logout);	
		
		adminUserPanel.add(adminUser);
		adminUserPanel.add(Box.createRigidArea(new Dimension(10,1)));
		adminUserPanel.add(adminUserField);
		
		adminPassPanel.add(adminPass);
		adminPassPanel.add(Box.createRigidArea(new Dimension(10,1)));
		adminPassPanel.add(adminPassField);
		
		adminLoginPanel.add(adminUserPanel);
		adminLoginPanel.add(adminPassPanel);
		
		adminSettings.add(openRegistration);
		adminSettings.add(Box.createRigidArea(new Dimension(1,20)));
		adminSettings.add(openVoting);
		adminSettings.add(Box.createRigidArea(new Dimension(1,20)));
		adminSettings.add(closeVoting);
		adminSettings.add(Box.createRigidArea(new Dimension(1,20)));
		adminSettings.add(tallyVotes);
		adminSettings.setPreferredSize(new Dimension(1024, 800));
		adminSettings.add(Box.createRigidArea(new Dimension(1,100)));
		
		adminTally.add(voteView.voteCount1);
		adminTally.add(voteView.voteCount2);
		adminTally.add(voteView.voteCount3);
		adminTally.add(voteView.voteCount4);
		adminTally.add(voteView.voteCount5);
		adminTally.add(backTally);
		
		adminCards.add(adminLoginPanel, "login");
		adminCards.add(adminSettings, "settings");
		adminCards.add(adminTally, "tally");
		adminCards.setPreferredSize(new Dimension(1024, 700));
		
		//TODO change logo for different image to indicate admin login
		adminPanel.add(Box.createRigidArea(new Dimension(1,20)));
		adminPanel.add(adminLogo);
		adminPanel.add(Box.createRigidArea(new Dimension(1,20)));
		adminPanel.add(adminTitleMessage);
		adminPanel.add(Box.createRigidArea(new Dimension(1,20)));
		adminPanel.add(adminCards);
		//adminPanel.add(Box.createRigidArea(new Dimension(1,200)));
		
		footerWest.add(admin);
		footerPanel.add(footerWest);
		footerPanel.add(footerEast);
		
		loginRegisterCards.add(loginRegisterPanel, "loginregister");
		loginRegisterCards.add(loginPanel, "login");
		loginRegisterCards.add(registerPanel, "register");
		loginRegisterCards.add(loggedInPanel, "loggedin");
		
		
		mainCards.add(mainPanel, "home");
		mainCards.add(adminPanel, "admin");
		mainCards.add(voteView, "vote");
		
		this.add(headerPanel);
		this.add(mainCards);
		this.add(footerPanel);
	}
	
	//Used to disable buttons - i.e. still visible but cannot be clicked
	public void setElementEnabled(String element, boolean enabled){
		if (element == "backlogin")
			backLogin.setEnabled(enabled);
		else if (element == "admin")
			admin.setEnabled(enabled);
		else if (element == "exit")
			exit.setEnabled(enabled);
		else if (element == "openvoting")
			openVoting.setEnabled(enabled);
		else if (element == "closevoting")
			closeVoting.setEnabled(enabled);
		else if (element == "tallyvotes")
			tallyVotes.setEnabled(enabled);
		else if (element == "openregistration")
			openRegistration.setEnabled(enabled);
		else if (element == "register")
			register.setEnabled(enabled);
		else if (element == "vote")
			vote.setEnabled(enabled);
		else if (element == "register")
			register.setEnabled(enabled);
		
	}
	
	//Used to completely remove buttons
	public void addRemoveElement(String element, boolean added){
		if (added){
			if (element == "backadmin")
				footerWest.add(backAdmin);
			else if (element == "backlogin")
				footerWest.add(backLogin);
			else if (element == "backregister")
				footerWest.add(backRegister);
			else if (element == "backtally")
				footerWest.add(backTally);
			else if (element == "admin")
				footerWest.add(admin);
			else if (element == "login")
				footerEast.add(loginConfirm);
			else if (element == "register")
				footerEast.add(registerConfirm);
			else if (element == "adminlogin")
				footerEast.add(adminLogin);
		} else {
			if (element == "backadmin")
				footerWest.remove(backAdmin);
			else if (element == "backlogin")
				footerWest.remove(backLogin);
			else if (element == "backregister")
				footerWest.remove(backRegister);
			else if (element == "admin")
				footerWest.remove(admin);
			else if (element == "backtally")
				footerWest.remove(backTally);
			else if (element == "login"){
				footerEast.remove(loginConfirm);
				userField.setText("");
				passField.setText("");
			}
			else if (element == "register"){
				footerEast.remove(registerConfirm);
				userField.setText("");
				passField.setText("");
			}
			else if (element == "adminlogin")
				footerEast.remove(adminLogin);
		}
	}
	
	public String getUserInput(){
		return userField.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPassInput(){
		return passField.getText();
	}
	
	public String getRegUserInput(){
		return regUserField.getText();
	}
	
	public String getRegPassInput(){
		return regPassField.getText();
	}
	
	public String getRegFirstInput(){
		return firstNameField.getText();
	}
	
	public String getRegLastInput(){
		return lastNameField.getText();
	}
	
	public String getRegIdInput(){
		return idField.getText();
	}
	
	public String getAdminUserInput(){
		return adminUserField.getText();
	}
	
	
	@SuppressWarnings("deprecation")
	public String getAdminPassInput(){
		return adminPassField.getText();
	}
	
	public void setMessage(String message, Color color){
		titleMessage.setText(message);
		titleMessage.setForeground(color);
	}
	
	public void setAdminMessage(String message, Color color){
		adminTitleMessage.setText(message);
	}
	
	public void setUsername(String userText){
		username.setText(userText);
	}
	
	public void clearLogins(){
		userField.setText("");
		passField.setText("");
		adminUserField.setText("");
		adminPassField.setText("");
	}
	
	//Adds and removes fields to login panel to allow it to be used for registration
	public void setLoginRegisterPanel(boolean isLogin){
		if (isLogin){
			loginPanel.remove(registerIdPanel);
			loginPanel.remove(registerFirstPanel);
			loginPanel.remove(registerLastPanel);
		} 
		else {
			loginPanel.remove(loginUserPanel);
			loginPanel.remove(loginPassPanel);
			loginPanel.remove(Box.createRigidArea(new Dimension(1,200)));
			loginPanel.add(registerFirstPanel);
			loginPanel.add(registerLastPanel);
			loginPanel.add(registerIdPanel);
			loginPanel.add(loginUserPanel);
			loginPanel.add(loginPassPanel);
		}
		
	}

}
