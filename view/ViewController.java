package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

//Handles bulk view changes made when moving to different screens
public class ViewController {
	
	//Views
	private VoteView voteView;
	private MenuView menuView;
	
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
		
		mainCards = new JPanel();
		mainCards.setLayout(new CardLayout());
		
		mainCards.add(this.menuView, "menu");
		mainCards.add(this.voteView, "vote");
		
		mainLayout = (CardLayout) mainCards.getLayout();
		mainLayout.show(mainCards, "menu");
	}
	
	public void login(){
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "login");
		menuView.addRemoveElement("admin", false);
		menuView.addRemoveElement("back", true);
		menuView.addRemoveElement("login", true);
		menuView.setMessage("Please login");
		mainWindow.repaint();
	}
	
	public void loggedIn(String username){
		menuView.setUsername(username);
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "loggedin");
		menuView.addRemoveElement("admin", true);
		menuView.addRemoveElement("back", false);
		menuView.addRemoveElement("login", false);
		menuView.setMessage("Login successful");
		mainWindow.repaint();
	}
	
	public void register(){
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "login");
		menuView.addRemoveElement("admin", false);
		menuView.addRemoveElement("back", true);
		menuView.addRemoveElement("register", true);
		menuView.setMessage("Please register");
		mainWindow.repaint();
	}
	
	public void registered(){
		menuView.loginRegisterLayout.show(menuView.loginRegisterCards, "loginregister");
		menuView.addRemoveElement("admin", true);
		menuView.addRemoveElement("back", false);
		menuView.addRemoveElement("register", false);
		menuView.setMessage("Registration successful, please login");
		mainWindow.repaint();
	}

}
