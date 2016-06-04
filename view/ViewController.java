package view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import receiver.VoteReceiver;

//Handles bulk view changes made when moving to different screens
public class ViewController {
	
	//Views
	private VoteView voteView;
	private MenuView menuView;
	private VoteReceiver voteReceiver;
	
	//JFrame
	public JFrame mainWindow;
	
	//JPanels
	public JPanel mainCards;
	
	//Layouts
	private CardLayout mainLayout;
	

	public ViewController(MenuView menuView, VoteView voteView, JFrame mainWindow) {
		this.menuView = menuView;
		this.voteView = voteView;
		this.mainWindow = mainWindow;
		this.voteReceiver = VoteReceiver.getInstance();
		
		mainCards = new JPanel();
		mainCards.setLayout(new CardLayout());
		
		mainCards.add(this.menuView, "menu");
		//mainCards.add(this.voteView, "vote");
		
		mainLayout = (CardLayout) mainCards.getLayout();
		mainLayout.show(mainCards, "menu");
	}
	
	//sets the message for main screen based on VoteReceiver state
	public void setMessage(){
		String regState = voteReceiver.getRegistrationState();
		String voteState = voteReceiver.getVotingState();
		if (regState == "opened")
			menuView.setMessage("Registration is now open. Please register below", Color.WHITE);
		else if (voteState == "opened")
			menuView.setMessage("Voting is now open. Please vote below", Color.WHITE);
		else 
			menuView.setMessage("Welcome to e-Vote", Color.WHITE);
	}
	
	//sets which admin options are enabled based on VoteReceiver state
	public void setAdminOptions(){
		String regState = voteReceiver.getRegistrationState();
		String voteState = voteReceiver.getVotingState();
		if (regState == "closed")
			menuView.setElementEnabled("openregistration", true);
		else 
			menuView.setElementEnabled("openregistration", false);
		if (voteState == "opened") {
			menuView.setElementEnabled("closevoting", true);
			menuView.setElementEnabled("tallyvotes", true);
			menuView.setElementEnabled("openregistration", false);
		}
		else if (voteState == "closed") {
			//menuView.setElementEnabled("openregistration", true);
			menuView.setElementEnabled("closevoting", false);
			menuView.setElementEnabled("tallyvotes", false);
		}
		else if (voteState == "finished"){
			menuView.setElementEnabled("openregistration", false);
			menuView.setElementEnabled("closevoting", false);
			menuView.setElementEnabled("tallyvotes", true);
		}
		if (voteState == "closed" && regState == "opened")
			menuView.setElementEnabled("openvoting", true);
		else 
			menuView.setElementEnabled("openvoting", false);
		
	}
	
	public void login(){
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "login");
		menuView.addRemoveElement("admin", false);
		menuView.addRemoveElement("backlogin", true);
		menuView.addRemoveElement("login", true);
		menuView.setMessage("Please login", Color.WHITE);
		mainWindow.repaint();
	}
	
	public void loggedIn(String username){
		menuView.setUsername(username);
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "loggedin");
		menuView.addRemoveElement("admin", true);
		menuView.addRemoveElement("backlogin", false);
		menuView.addRemoveElement("login", false);
		menuView.setMessage("Login successful", Color.WHITE);
		mainWindow.repaint();
	}
	
	public void register(){
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "login");
		menuView.addRemoveElement("admin", false);
		menuView.addRemoveElement("backregister", true);
		menuView.addRemoveElement("register", true);
		menuView.setMessage("Please register", Color.WHITE);
		mainWindow.repaint();
	}
	
	public void registered(){
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "loginregister");
		menuView.addRemoveElement("admin", true);
		menuView.addRemoveElement("backregister", false);
		menuView.addRemoveElement("register", false);
		menuView.setMessage("Registration successful, please login", Color.WHITE);
		mainWindow.repaint();
	}
	
	public void adminLogin(){
		menuView.mainLayout.show(menuView.mainCards, "admin");
		menuView.addRemoveElement("admin", false);
		menuView.addRemoveElement("backadmin", true);
		menuView.addRemoveElement("adminlogin", true);
		menuView.setAdminMessage("Admin Login", Color.WHITE);
		mainWindow.repaint();
	}
	
	public void adminLoggedIn(){
		//menuView.addRemoveElement("backlogin", true);
		setAdminOptions();
		menuView.setUsername("Admin");
		menuView.setAdminMessage("Admin Panel", Color.WHITE);
		menuView.addRemoveElement("adminlogin", false);
		menuView.adminLayout.show(menuView.adminCards, "settings");
		mainWindow.repaint();
	}
	
	public void backFromAdmin(){
		menuView.mainLayout.show(menuView.mainCards, "home");
		menuView.adminLayout.show(menuView.adminCards, "login");
		menuView.setUsername("Not logged in");
		menuView.addRemoveElement("admin", true);
		menuView.addRemoveElement("backadmin", false);
		menuView.addRemoveElement("adminlogin", false);
		setMessage();
		mainWindow.repaint();
	}
	
	public void backFromLogin(){
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "loginregister");
		menuView.addRemoveElement("admin", true);
		menuView.addRemoveElement("backlogin", false);
		menuView.addRemoveElement("login", false);
		setMessage();
		mainWindow.repaint();
	}
	
	public void backFromRegister(){
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "loginregister");
		menuView.addRemoveElement("admin", true);
		menuView.addRemoveElement("backregister", false);
		menuView.addRemoveElement("register", false);
		setMessage();
		mainWindow.repaint();
		
	}
	
	public void logout(){
		menuView.setUsername("Not logged in");
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "loginregister");
		setMessage();
	}
	
	public void moveToVote(){
		menuView.mainLayout.show(menuView.mainCards, "vote");
		menuView.addRemoveElement("admin", false);
		mainWindow.repaint();
	}
	
	
	public void adminOptionsUpdate(){
		setAdminOptions();
		setMessage();
		mainWindow.repaint();
	}

}
