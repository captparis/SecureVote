package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import receiver.UserManager;
import receiver.VoteReceiver;
import view.MenuView;
import view.ViewController;
import view.VoteView;
import voter.VoteSender;


public class ApplicationController {
	
	private MenuView menuView;
	private VoteView voteView;
	private ViewController viewController;
	private UserManager userManager;
	
	private int selectionCounter = 0;
	private int selectionMax = 1;
	private int selection1;
	private int selection2;
	
	VoteListener voteListener = new VoteListener();
	VoteButtonListener voteButtonListener = new VoteButtonListener();
	MenuButtonListener menuButtonListener = new MenuButtonListener();
	
	private VoteSender voteSender;
	
	public Color alertColour = new Color(255, 0, 84);
	

	public ApplicationController(JFrame mainWindow) {
		
		voteView = new VoteView(voteListener, voteButtonListener);
		menuView = new MenuView(menuButtonListener);
		
		viewController = new ViewController(menuView, voteView, mainWindow);
		userManager = new UserManager();
		
		voteSender = new VoteSender();
		
		//mainWindow.add(voteView);
		
		mainWindow.getContentPane().add(viewController.mainCards);
		
		
	}
	
	//Voting methods
	
	
	//Listeners
	
	//Listens for voter selection
	
	class VoteListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e){
			JCheckBox source = (JCheckBox) e.getSource();
			
			if (source.isSelected()){
				selectionCounter ++;
				//if (selectionCounter > selectionMax){
					//selectionCounter --;
					//JOptionPane.showMessageDialog(null, "Only one candidate can be selected. Please deselect previous candidate and try again.");
				//}
				//else {
					
					String selectionName = ((Component) e.getSource()).getName();
					if (selection1 == 0)
						selection1 = Integer.parseInt(selectionName);
					else 
						selection2 = Integer.parseInt(selectionName);
				//}
			}
			else {
				selectionCounter--;
			}
		}
	
	}
	
	//Listens for button presses in the VoteView
	
	class VoteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String option = ((JButton) e.getSource()).getName();

			switch (option) {
			case "vote":
				if (voteView.voterCounter < 1){
					JOptionPane.showMessageDialog(null, "All voters have voted, please tally votes");
				}
				else {
					voteSender.sendVote(selection1, selection2);
					voteView.reduceVoters();
					selection1 = 0;
					selection2 = 0;
				}
				break;
			case "exit":
				Runtime.getRuntime().exit(0);
				break;
			case "open":
				int temp = voteView.getVotersNum();
				if (temp != 0)	{
					VoteReceiver.getInstance().setVoters(temp);
					VoteReceiver.getInstance().createKeys();
					voteSender.requestPublicKeys();
					voteView.swapCards("voteScreen");
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter a valid number of voters");
				}
				break;
			case "tally":
				List<Integer> candidateVotes = VoteReceiver.getInstance().tallyVotes();
				voteView.setVoteCount(candidateVotes);
				break;
			}
			
		}
		
	}
	
	//Listens for button presses in the MenuView
	
	class MenuButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String option = ((JButton) e.getSource()).getName();

			switch (option) {
				case "exit":
					Runtime.getRuntime().exit(0);
					break;
				case "login":
					viewController.login();
					break;
				case "loginconfirm":
					String user = menuView.getUserInput();
					String pass = menuView.getPassInput();
					if (!user.equals("") && !pass.equals("")){
						if (userManager.checkValidUser(user, pass, false)){
							viewController.loggedIn(user);
							voteSender.activeUser = user;
						}
						else {
							menuView.setMessage("Invalid username or password, please try again", Color.WHITE);
						}
					}
					else 
						menuView.setMessage("Username and password cannot be empty", Color.WHITE);
					break;
				case "registerconfirm":
					System.out.println("Registering");
					String newUser = menuView.getUserInput();
					String newPass = menuView.getPassInput();
					if (!newUser.equals("") && !newPass.equals("")){
						userManager.addUser(newUser, newPass);
						viewController.registered();
					}
					else {
						menuView.setMessage("Username and password cannot be empty", Color.WHITE);
					}
					break;
				case "register":
					viewController.register();
					break;
				case "admin":
					viewController.adminLogin();
					break;
				case "adminloginconfirm":
					String adminUser = menuView.getAdminUserInput();
					String adminPass = menuView.getAdminPassInput();
					if (!adminUser.equals("") && !adminPass.equals("")){
						if (userManager.checkValidUser(adminUser, adminPass, true)){
							viewController.adminLoggedIn();
							voteSender.activeUser = adminUser;
							System.out.println("Valid admin credentials");
						}
						else {
							menuView.setAdminMessage("Invalid username or password, please try again", Color.WHITE);
						}
					}
					else 
						menuView.setAdminMessage("Username and password cannot be empty", Color.WHITE);
					break;
				case "backadmin":
					viewController.backFromAdmin();
					break;
				case "backlogin":
					viewController.backFromLogin();
					break;
				case "backregister":
					viewController.backFromRegister();
					break;
				case "logout":
					voteSender.activeUser = "";
					viewController.logout();
					break;
				case "openvoting":
					VoteReceiver.getInstance().setVotingState("opened");
					JOptionPane.showMessageDialog(null, "Voting has been opened");
					viewController.votingOpened();
					break;
				case "vote":
					String state = VoteReceiver.getInstance().getVotingState();
					System.out.println("voting state is " + state);
					if (state == "opened")
						System.out.println("Voting is opened, go right ahead");
					if (state == "closed")
						System.out.println("Voting is closed, no voting for you");
					if (state == "finished")
						System.out.println("Voting is finished, thanks for your vote");
					break;
			}
		}
		
	}
}
