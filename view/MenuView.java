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

public class MenuView extends JPanel {
	
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
	private JPanel registerUserPanel;
	private JPanel registerPassPanel;
	private JPanel loggedInPanel;
	
	public JPanel mainCards;
	public JPanel loginRegisterCards;
	
	//JButtons
	private JButton back;
	private JButton exit;
	private JButton admin;
	private JButton ok;
	private JButton loginConfirm;
	private JButton registerConfirm;
	private JButton vote;
	private JButton logout;
	
	private JButton login;
	private JButton register;
	
	
	
	//JLabels
	private JLabel username;
	private JLabel logo;
	private JLabel titleMessage;
	private JLabel loginUser;
	private JLabel loginPass;
	
	//JTextFields
	private JTextField userField;
	private JTextField passField;
	
	private JTextField registerUserField;
	private JTextField registerPassField;
	
	//Layouts
	CardLayout mainLayout;
	CardLayout loginRegisterLayout;
	

	public MenuView(ActionListener menuButtonListener) {
		//JPanel setup
		headerPanel = new JPanel();
		headerWest = new JPanel();
		headerEast = new JPanel();
		mainPanel = new JPanel();
		loginRegisterPanel = new JPanel();
		loginPanel = new JPanel();
		loginUserPanel = new JPanel();
		loginPassPanel = new JPanel();
		registerPanel = new JPanel();
		footerPanel = new JPanel();
		footerEast = new JPanel();
		footerWest = new JPanel();
		mainCards = new JPanel();
		loginRegisterCards = new JPanel();
		loggedInPanel = new JPanel();
		
		this.setPreferredSize(new Dimension (1024, 640));
		this.setBackground(new Color(102, 224, 255));
		
		headerPanel.setPreferredSize(new Dimension(1024, 40));
		headerWest.setLayout(new FlowLayout(FlowLayout.LEFT));
		headerWest.setPreferredSize(new Dimension(507,40));
		headerEast.setLayout(new FlowLayout(FlowLayout.RIGHT));
		headerEast.setPreferredSize(new Dimension(507,40));
		
		mainPanel.setPreferredSize(new Dimension(1024, 540));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.BLACK);
		
		loggedInPanel.setPreferredSize(new Dimension(1024, 90));
		loggedInPanel.setLayout(new BoxLayout(loggedInPanel, BoxLayout.Y_AXIS));
		
		loginRegisterPanel.setLayout(new BoxLayout(loginRegisterPanel, BoxLayout.Y_AXIS));
		
		loginPanel.setPreferredSize(new Dimension(1024, 90));
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		
		loginUserPanel.setSize(new Dimension(1024, 40));
		loginPassPanel.setSize(new Dimension(1024, 40));
		
		footerPanel.setPreferredSize(new Dimension(1024, 40));
		footerWest.setLayout(new FlowLayout(FlowLayout.LEFT));
		footerWest.setPreferredSize(new Dimension(507,40));
		footerWest.setBackground(Color.BLACK);
		footerEast.setLayout(new FlowLayout(FlowLayout.RIGHT));
		footerEast.setPreferredSize(new Dimension(507,40));
		footerEast.setBackground(Color.BLACK);
		
		//Remove panel backgrounds
		headerPanel.setOpaque(false);
		headerWest.setOpaque(false);
		headerEast.setOpaque(false);
		mainPanel.setOpaque(false);
		loginPanel.setOpaque(false);
		loginUserPanel.setOpaque(false);
		loginPassPanel.setOpaque(false);
		registerPanel.setOpaque(false);
		footerPanel.setOpaque(false);
		footerEast.setOpaque(false);
		footerWest.setOpaque(false);
		mainCards.setOpaque(false);
		loginRegisterPanel.setOpaque(false);
		loginRegisterCards.setOpaque(false);
		loggedInPanel.setOpaque(false);
		
		//CardLayout setup
		mainCards.setLayout(new CardLayout());
		mainLayout = (CardLayout) mainCards.getLayout();
		loginRegisterCards.setLayout(new CardLayout());
		loginRegisterLayout = (CardLayout) loginRegisterCards.getLayout();
		
		//Image setup
		ImageIcon exitIcon = new ImageIcon("bin/images/exit2.png");
        Image exitImage = exitIcon.getImage() ;  
        exitImage = exitImage.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
        exitIcon.setImage(exitImage);
		
		//JButton setup
		back = new JButton("Back");
		exit = new JButton();
		admin = new JButton("Admin");
		ok = new JButton("Ok");
		register = new JButton("Register");
		login = new JButton ("Login");
		loginConfirm = new JButton ("Login");
		registerConfirm = new JButton("Register");
		vote = new JButton("Vote");
		logout = new JButton("Logout");
		
		back.setName("back");
		exit.setName("exit");
		loginConfirm.setName("loginconfirm");
		registerConfirm.setName("registerconfirm");
		exit.setIcon(exitIcon);
		admin.setName("admin");
		ok.setName("ok");
		register.setName("register");
		login.setName("login");
		
		register.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		vote.setAlignmentX(Component.CENTER_ALIGNMENT);
		logout.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		back.addActionListener(menuButtonListener);
		exit.addActionListener(menuButtonListener);
		admin.addActionListener(menuButtonListener);
		ok.addActionListener(menuButtonListener);
		register.addActionListener(menuButtonListener);
		login.addActionListener(menuButtonListener);
		loginConfirm.addActionListener(menuButtonListener);
		registerConfirm.addActionListener(menuButtonListener);
		
		
		//JLabel setup
		username = new JLabel("Not logged in");
		username.setForeground(Color.WHITE);
		
		ImageIcon logoImg = new ImageIcon("bin/images/logo.png");
		logo = new JLabel();
		logo.setIcon(logoImg);
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		titleMessage = new JLabel("Welcome to e-Vote");
		titleMessage.setForeground(Color.WHITE);
		titleMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleMessage.setFont(new Font (titleMessage.getText(), Font.PLAIN, 20));
		
		loginUser = new JLabel("Username: ");
		loginPass = new JLabel("Password: ");
		loginUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginPass.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginUser.setForeground(Color.WHITE);
		loginPass.setForeground(Color.WHITE);
		
		//JTextFieldSetup
		userField = new JTextField();
		passField = new JTextField();
		userField.setColumns(10);
		passField.setColumns(10);
		
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
		
		loggedInPanel.add(vote);
		loginPanel.add(Box.createRigidArea(new Dimension(100,50)));
		loggedInPanel.add(logout);
		
		/*
		registerUserPanel.add(loginUser);
		registerUserPanel.add(userField);
		
		registerPassPanel.add(loginPass);
		registerPassPanel.add(passField);
		
		registerPanel.add(registerUserPanel);
		loginPanel.add(Box.createRigidArea(new Dimension(1,5)));
		registerPanel.add(registerPassPanel);
		loginPanel.add(Box.createRigidArea(new Dimension(1,200)));
		*/
		
		
		footerWest.add(admin);
		footerPanel.add(footerWest);
		footerPanel.add(footerEast);
		
		loginRegisterCards.add(loginRegisterPanel, "loginregister");
		loginRegisterCards.add(loginPanel, "login");
		loginRegisterCards.add(registerPanel, "register");
		loginRegisterCards.add(loggedInPanel, "loggedin");
		
		
		//TODO mainCards currently not used for anything, should change to admin view
		mainCards.add(mainPanel, "home");
		
		this.add(headerPanel);
		this.add(mainCards);
		this.add(footerPanel);
	}
	
	//Used to disable buttons - i.e. still visible but cannot be clicked
	public void setElementEnabled(String element, boolean enabled){
		if (element == "back")
			back.setEnabled(enabled);
		if (element == "admin")
			admin.setEnabled(enabled);
		if (element == "exit")
			exit.setEnabled(enabled);
	}
	
	//Used to completely remove buttons
	public void addRemoveElement(String element, boolean added){
		if (added){
			if (element == "back")
				footerWest.add(back);
			else if (element == "admin")
				footerWest.add(admin);
			else if (element == "login"){
				footerEast.add(loginConfirm);
			}
			else if (element == "register"){
				footerEast.add(registerConfirm);
			}
		} else {
			if (element == "back")
				footerWest.remove(back);
			else if (element == "admin")
				footerWest.remove(admin);
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
		}
	}
	
	public String getUserInput(){
		return userField.getText();
	}
	
	public String getPassInput(){
		return passField.getText();
	}
	
	public void setMessage(String message){
		titleMessage.setText(message);
	}
	
	public void setUsername(String userText){
		username.setText(userText);
	}

}
