package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;

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
	private JPanel candidateOffset;
	private JPanel buttonPanel;
	private JPanel votedPanel;
	private JPanel voteCards;
	
	//Buttons
	private JButton openVoting;
	private JButton vote;
	private JButton exit;
	private JButton tally;
	private JButton decrypt;
	private JButton back;
	
	//Labels
	private JLabel title;
	private JLabel howMany;
	private JLabel votersLeft;
	private JLabel thankYou;
	
	JLabel voteCount1;
	JLabel voteCount2;
	JLabel voteCount3;
	JLabel voteCount4;
	JLabel voteCount5;
	JLabel voteCount6;
	JLabel voteCount7;
	
	//Checkboxes
	private JCheckBox check1;
	private JCheckBox check2;
	private JCheckBox check3;
	private JCheckBox check4;
	private JCheckBox check5;
	private JCheckBox check6;
	private JCheckBox check7;
	
	//Textfields
	private JTextField voters;
	
	//Layouts
	private CardLayout mainLayout;
	private CardLayout voteLayout;
	
	//Variables
	public int voterCounter;
	
	public VoteView(ItemListener listener, ActionListener buttonListener){
		
		//JPanel setup
		mainCards = new JPanel();
		menuPanel = new JPanel();
		verticalContainer = new JPanel();
		candidatePanel = new JPanel();
		buttonPanel = new JPanel();
		votedPanel = new JPanel();
		voteCards = new JPanel();
		candidateOffset = new JPanel();
		
		mainCards.setLayout(new CardLayout());
		voteCards.setLayout(new CardLayout());
		candidatePanel.setLayout(new BoxLayout(candidatePanel, BoxLayout.Y_AXIS));
		verticalContainer.setLayout(new BoxLayout(verticalContainer, BoxLayout.Y_AXIS));
		
		
		
		mainLayout = (CardLayout)mainCards.getLayout();
		voteLayout = (CardLayout)voteCards.getLayout();
		
		this.setOpaque(false);
		mainCards.setOpaque(false);
		menuPanel.setOpaque(false);
		verticalContainer.setOpaque(false);
		candidatePanel.setOpaque(false);
		candidateOffset.setOpaque(false);
		buttonPanel.setOpaque(false);
		votedPanel.setOpaque(false);
		voteCards.setOpaque(false);
		
		//Button setup
		vote = new JButton("Vote");
		exit = new JButton("Exit");
		tally = new JButton("Tally Votes");
		decrypt = new JButton("Decrypt");
		openVoting = new JButton("Open Voting");
		back = new JButton("Back");
		vote.setName("vote");
		exit.setName("exit");
		tally.setName("tally");
		decrypt.setName("decrypt");
		openVoting.setName("open");
		back.setName("back");
		vote.addActionListener(buttonListener);
		exit.addActionListener(buttonListener);
		tally.addActionListener(buttonListener);
		decrypt.addActionListener(buttonListener);
		openVoting.addActionListener(buttonListener);
		back.addActionListener(buttonListener);
		vote.setAlignmentX(Component.CENTER_ALIGNMENT);
		tally.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Label setup
		title = new JLabel("Cast your vote");
		howMany = new JLabel ("How many voters?");
		votersLeft = new JLabel ("Voters left to vote: " + 0);
		thankYou = new JLabel("Thank you for voting");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		votersLeft.setAlignmentX(Component.CENTER_ALIGNMENT);
		thankYou.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setForeground(Color.WHITE);
		votersLeft.setForeground(Color.WHITE);
		thankYou.setForeground(Color.WHITE);
		title.setFont(new Font (title.getText(), Font.PLAIN, 20));
		
		voteCount1 = new JLabel("");
		voteCount2 = new JLabel("");
		voteCount3 = new JLabel("");
		voteCount4 = new JLabel("");
		voteCount5 = new JLabel("");
		voteCount6 = new JLabel("");
		voteCount7 = new JLabel("");
		
		voteCount1.setForeground(Color.WHITE);
		voteCount2.setForeground(Color.WHITE);
		voteCount3.setForeground(Color.WHITE);
		voteCount4.setForeground(Color.WHITE);
		voteCount5.setForeground(Color.WHITE);
		voteCount6.setForeground(Color.WHITE);
		voteCount7.setForeground(Color.WHITE);
		
		voteCount1.setAlignmentX(Component.CENTER_ALIGNMENT);
		voteCount2.setAlignmentX(Component.CENTER_ALIGNMENT);
		voteCount3.setAlignmentX(Component.CENTER_ALIGNMENT);
		voteCount4.setAlignmentX(Component.CENTER_ALIGNMENT);
		voteCount5.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Checkbox setup
		check1 = new JCheckBox("Adam Bandt");
		check2 = new JCheckBox("Brendan O'Connor");
		check3 = new JCheckBox("Helen McLeod");
		check4 = new JCheckBox("Olivia Ball");
		check5 = new JCheckBox("Kevin Andrews");
		check6 = new JCheckBox("Alan Williams");
		check7 = new JCheckBox("Samantha Ratnam");
		check1.setName("1");
		check2.setName("2");
		check3.setName("3");
		check4.setName("4");
		check5.setName("5");
		check6.setName("6");
		check7.setName("7");
		check1.addItemListener(listener);
		check2.addItemListener(listener);
		check3.addItemListener(listener);
		check4.addItemListener(listener);
		check5.addItemListener(listener);
		check6.addItemListener(listener);
		check7.addItemListener(listener);
		check1.setForeground(Color.WHITE);
		check2.setForeground(Color.WHITE);
		check3.setForeground(Color.WHITE);
		check4.setForeground(Color.WHITE);
		check5.setForeground(Color.WHITE);
		check6.setForeground(Color.WHITE);
		check7.setForeground(Color.WHITE);
		
		//Textfield setup
		voters = new JTextField();
		voters.setColumns(5);
		
		menuPanel.add(howMany);
		menuPanel.add(voters);
		menuPanel.add(openVoting);
		
		candidatePanel.add(check1);
		candidatePanel.add(Box.createRigidArea(new Dimension(10,10)));
		candidatePanel.add(check2);
		candidatePanel.add(Box.createRigidArea(new Dimension(10,10)));
		candidatePanel.add(check3);
		candidatePanel.add(Box.createRigidArea(new Dimension(10,10)));
		candidatePanel.add(check4);
		candidatePanel.add(Box.createRigidArea(new Dimension(10,10)));
		candidatePanel.add(check5);
		
		candidateOffset.add(Box.createRigidArea(new Dimension(10,20)));
		candidateOffset.add(candidatePanel);
		
		buttonPanel.add(vote);
		//buttonPanel.add(exit);
		buttonPanel.add(back);
		//buttonPanel.add(tally);
		
		
		votedPanel.add(thankYou);
		
		voteCards.add(candidateOffset, "candidates");
		voteCards.add(votedPanel, "voteOver");
		
		verticalContainer.add(Box.createRigidArea(new Dimension(50,20)));
		verticalContainer.add(title);
		verticalContainer.add(Box.createRigidArea(new Dimension(50,50)));
		verticalContainer.add(voteCards);
		verticalContainer.add(Box.createRigidArea(new Dimension(50,50)));
		verticalContainer.add(buttonPanel);
		verticalContainer.add(Box.createRigidArea(new Dimension(50,50)));
		verticalContainer.add(voteCount1);
		verticalContainer.add(voteCount2);
		verticalContainer.add(voteCount3);
		verticalContainer.add(voteCount4);
		verticalContainer.add(voteCount5);
		
		mainCards.add(menuPanel, "menuScreen");
		mainCards.add(verticalContainer, "voteScreen");
		
		
		
		this.add(mainCards);
		this.setPreferredSize(new Dimension(1024, 100));
		
		mainLayout.show(mainCards, "voteScreen");
		voteLayout.show(voteCards, "candidates");
	}
	
	public void swapCards(String cardName){
		mainLayout.show(mainCards, cardName);
	}
	
	public void setVotersNum(int totalVoters){
			voterCounter = totalVoters;
			votersLeft.setText("Voters left to vote: " + voterCounter);
	}
	
	public void reduceVoters(){
		voterCounter--;
		votersLeft.setText("Voters left to vote: " + voterCounter);
	}
	
	//Called after tally - voting is complete
	public void setVoteCount(List<Integer> votes){
		voteLayout.show(voteCards, "voteOver");
		votersLeft.setText("Registered voters who did not cast a vote: " + voterCounter);
		voteCount1.setText("Votes for candidate " + check1.getText() + ": " + votes.get(0));
		voteCount2.setText("Votes for candidate " + check2.getText() + ": " + votes.get(1));
		voteCount3.setText("Votes for candidate " + check3.getText() + ": " + votes.get(2));
		voteCount4.setText("Votes for candidate " + check4.getText() + ": " + votes.get(3));
		voteCount5.setText("Votes for candidate " + check5.getText() + ": " + votes.get(4));
		
		System.out.println("Votes for candidate " + check1.getName() + ": " + votes.get(0));
		System.out.println("Votes for candidate " + check2.getName() + ": " + votes.get(1));
		System.out.println("Votes for candidate " + check3.getName() + ": " + votes.get(2));
		System.out.println("Votes for candidate " + check4.getName() + ": " + votes.get(3));
		System.out.println("Votes for candidate " + check5.getName() + ": " + votes.get(4));

		
	}
	
	public void clearVotes(){
		check1.setSelected(false);
		check2.setSelected(false);
		check3.setSelected(false);
		check4.setSelected(false);
		check5.setSelected(false);
		
	}

}
