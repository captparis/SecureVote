package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VoteView extends JPanel {
	
	//JPanel
	public JPanel mainCards;
	private JPanel menuPanel;
	private JPanel verticalContainer;
	private JPanel candidatePanel;
	private JPanel buttonPanel;
	
	//Buttons
	private JButton openVoting;
	private JButton vote;
	private JButton exit;
	private JButton tally;
	private JButton decrypt;
	
	//Labels
	private JLabel title;
	private JLabel howMany;
	private JLabel votersLeft;
	
	//Checkboxes
	private JCheckBox check1;
	private JCheckBox check2;
	private JCheckBox check3;
	
	//Textfields
	private JTextField voters;
	
	//Layouts
	private CardLayout mainLayout;
	
	//Variables
	private int voterCounter;
	
	public VoteView(ItemListener listener, ActionListener buttonListener){
		
		//JPanel setup
		mainCards = new JPanel();
		menuPanel = new JPanel();
		verticalContainer = new JPanel();
		candidatePanel = new JPanel();
		buttonPanel = new JPanel();
		
		mainCards.setLayout(new CardLayout());
		candidatePanel.setLayout(new BoxLayout(candidatePanel, BoxLayout.Y_AXIS));
		verticalContainer.setLayout(new BoxLayout(verticalContainer, BoxLayout.Y_AXIS));
		
		candidatePanel.setPreferredSize(new Dimension(512, 100));
		
		mainLayout = (CardLayout)mainCards.getLayout();
		
		//Button setup
		vote = new JButton("Vote");
		exit = new JButton("Exit");
		tally = new JButton("Tally Votes");
		decrypt = new JButton("Decrypt");
		openVoting = new JButton("Open Voting");
		vote.setName("vote");
		exit.setName("exit");
		tally.setName("tally");
		decrypt.setName("decrypt");
		openVoting.setName("open");
		vote.addActionListener(buttonListener);
		exit.addActionListener(buttonListener);
		tally.addActionListener(buttonListener);
		decrypt.addActionListener(buttonListener);
		openVoting.addActionListener(buttonListener);
		
		//Label setup
		title = new JLabel("Cast your vote");
		howMany = new JLabel ("How many voters?");
		votersLeft = new JLabel ("Voters left to vote: " + 0);
		
		//Checkbox setup
		check1 = new JCheckBox("Jacob");
		check2 = new JCheckBox("Nicole");
		check3 = new JCheckBox("Moogle");
		check1.setName("1");
		check2.setName("2");
		check3.setName("3");
		check1.addItemListener(listener);
		check2.addItemListener(listener);
		check3.addItemListener(listener);
		
		//Textfield setup
		voters = new JTextField();
		voters.setColumns(5);
		
		menuPanel.add(howMany);
		menuPanel.add(voters);
		menuPanel.add(openVoting);
		
		candidatePanel.add(check1);
		candidatePanel.add(check2);
		candidatePanel.add(check3);
		
		buttonPanel.add(vote);
		buttonPanel.add(exit);
		buttonPanel.add(tally);
		
		verticalContainer.add(Box.createRigidArea(new Dimension(50,20)));
		verticalContainer.add(title);
		verticalContainer.add(Box.createRigidArea(new Dimension(50,50)));
		verticalContainer.add(candidatePanel);
		verticalContainer.add(Box.createRigidArea(new Dimension(50,50)));
		verticalContainer.add(buttonPanel);
		verticalContainer.add(Box.createRigidArea(new Dimension(50,50)));
		verticalContainer.add(votersLeft);
		
		mainCards.add(menuPanel, "menuScreen");
		mainCards.add(verticalContainer, "voteScreen");
		
		
		
		this.add(mainCards);
		this.setPreferredSize(new Dimension(512, 512));
		
		mainLayout.show(mainCards, "menuScreen");
	}
	
	public void swapCards(String cardName){
		mainLayout.show(mainCards, cardName);
	}
	
	public int getVotersNum(){
		String text = voters.getText();
		if (!text.trim().isEmpty()){
			int temp = Integer.parseInt(text);
			voterCounter = temp;
			votersLeft.setText("Voters left to vote: " + voterCounter);
			return temp;
		}
		else return 0;
	}
	
	public void reduceVoters(){
		voterCounter--;
		votersLeft.setText("Voters left to vote: " + voterCounter);
	}
	

}
