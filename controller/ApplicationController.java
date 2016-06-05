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
import javax.swing.Timer;

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
	
	private JFrame mainWindow;
	
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
		menuView = new MenuView(menuButtonListener, voteView);
		
		viewController = new ViewController(menuView, voteView, mainWindow);
		userManager = UserManager.getInstance();
		
		voteSender = new VoteSender();
		
		//mainWindow.add(voteView);
		
		mainWindow.getContentPane().add(viewController.mainCards);
		this.mainWindow = mainWindow;
		
		
	}
	
	//Voting methods
	
	
	//Listeners
	
	//Listens for voter selection
	
	class VoteListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e){
			JCheckBox source = (JCheckBox) e.getSource();
			String selectionName = ((Component) e.getSource()).getName();
			int selectionInt = Integer.parseInt(selectionName);
			
			System.out.println("selection counter is currently " + selectionCounter);
			
			if (source.isSelected()){ //candidate selected
				if (selectionCounter >= 2){
					JOptionPane.showMessageDialog(null, "You may only select a maximum of 2 candidates. Please deselect a candidate to choose another");
				}
				else {
					
					if (selection1 == 0)
						selection1 = selectionInt;
					else 
						selection2 = selectionInt;
				}
				selectionCounter ++;
				System.out.println("selection counter is now " + selectionCounter);
			}
			else { //candidate deselected
				selectionCounter--;
			
				if (selectionInt == selection1){
					selection1 = selection2;
					selection2 = 0;
					System.out.println("First selection deselected, selection 1 is now " + selection1 + " and selection 2 is now " + selection2);
				} else if (selectionInt == selection2){
					selection2 = 0;
					System.out.println("Second selection deselected, selection 1 is now " + selection1 + " and selection 2 is now " + selection2);
				}
				System.out.println("selection counter is now " + selectionCounter);
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
					//TODO Tallying should not occur here
					JOptionPane.showMessageDialog(null, "All voters have voted, please tally votes");
				}
				else if (userManager.checkVoted(userManager.getActiveUser())){
					//Should not be called, user should be taken from vote section after vote, code here is backup
					JOptionPane.showMessageDialog(null, "You have already cast a vote");
				}
				else {
					voteSender.sendVote(selection1, selection2);
					voteView.reduceVoters();
					userManager.setVoted(userManager.getActiveUser(), true);
					//selection1 = 0;
					//selection2 = 0;
					viewController.backFromVote();
				}
				break;
			case "exit":
				Runtime.getRuntime().exit(0);
				break;
			case "open":
				/*int temp = voteView.getVotersNum();
				if (temp != 0)	{
					VoteReceiver.getInstance().setVoters(temp);
					VoteReceiver.getInstance().createKeys();
					voteSender.requestPublicKeys();
					voteView.swapCards("voteScreen");
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter a valid number of voters");
				}*/
				break;
			case "tally":
				List<Integer> candidateVotes = VoteReceiver.getInstance().tallyVotes();
				voteView.setVoteCount(candidateVotes);
				break;
			case "back":
				viewController.backFromVote();
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
							userManager.setActiveUser(user);
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
							userManager.setActiveUser(adminUser);
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
					userManager.setActiveUser("");
					viewController.logout();
					break;
				case "openregistration":
					int temp = menuView.getVoterCountInput();
					if (temp != 0)	{
						VoteReceiver.getInstance().setVoters(temp);
						VoteReceiver.getInstance().setRegistrationState("opened");
						JOptionPane.showMessageDialog(null, "Registration has been opened");
						viewController.adminOptionsUpdate();
					}
					else 
						JOptionPane.showMessageDialog(null, "Please enter number of voters before opening registration");
					break;
				case "openvoting":
					VoteReceiver.getInstance().setVotingState("opened");
					VoteReceiver.getInstance().setRegistrationState("finished");
					JOptionPane.showMessageDialog(null, "Voting has been opened, registration automatically closed");
					viewController.adminOptionsUpdate();
					break;
				case "closevoting":
					VoteReceiver.getInstance().setVotingState("finished");
					JOptionPane.showMessageDialog(null, "Voting has been closed");
					viewController.adminOptionsUpdate();
					break;
				case "tallyvotes":
					List<Integer> candidateVotes = VoteReceiver.getInstance().tallyVotes();
					voteView.setVoteCount(candidateVotes);
					viewController.adminTally();
					break;
				case "backtally":
					viewController.backFromTally();
					break;
				case "vote":
					String state = VoteReceiver.getInstance().getVotingState();
					System.out.println("voting state is " + state);
					if (userManager.checkVoted(userManager.getActiveUser())){
						JOptionPane.showMessageDialog(null, "You have already cast a vote");
					}
					else if (state == "opened"){
						menuView.setMessage("Opening secure voting, please wait...", Color.WHITE);

						Timer openVotingTimer = new Timer(5, new ActionListener(){
							@Override
							public void actionPerformed (ActionEvent e){
								VoteReceiver.getInstance().createKeys();
								voteSender.requestPublicKeys();
								voteView.setVotersNum(VoteReceiver.getInstance().getVoters());
								viewController.moveToVote();
							}
						});	
						
						openVotingTimer.setRepeats(false);
						openVotingTimer.start();
					
					}
					else if (state == "closed"){
						//Should not be called as 'Vote' button is disabled when voting is closed, code here is backup
						JOptionPane.showMessageDialog(null, "Voting is currently closed");
					}
					else if (state == "finished")
						//Should not be called as 'Vote' button is disabled when voting is finished, code here is backup
						JOptionPane.showMessageDialog(null, "Voting is finished, thanks for your vote");
					break;
			}
		}
		
	}
}
