package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import receiver.VoteReceiver;
import view.VoteView;
import voter.VoteSender;


public class ApplicationController {
	
	private VoteView voteView;
	private int selectionCounter = 0;
	private int selectionMax = 1;
	private int selection;
	
	VoteListener voteListener = new VoteListener();
	ButtonListener buttonListener = new ButtonListener();
	
	private VoteSender voteSender;
	

	public ApplicationController(JFrame mainWindow) {
		
		voteView = new VoteView(voteListener, buttonListener);
		
		voteSender = new VoteSender();
		
		//mainWindow.add(voteView);
		
		mainWindow.getContentPane().add(voteView);
		
	}
	
	//Voting methods
	
	
	//Listeners
	
	class VoteListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e){
			JCheckBox source = (JCheckBox) e.getSource();
			
			if (source.isSelected()){
				selectionCounter ++;
				if (selectionCounter > selectionMax){
					//selectionCounter --;
					JOptionPane.showMessageDialog(null, "Only one candidate can be selected. Please deselect previous candidate and try again.");
				}
				else {
					
					String selectionName = ((Component) e.getSource()).getName();
					selection = Integer.parseInt(selectionName);
				}
			}
			else {
				selectionCounter--;
			}
		}
	
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String option = ((JButton) e.getSource()).getName();

			switch (option) {
			case "vote":
				voteSender.sendVote(selection);
				break;
			case "exit":
				Runtime.getRuntime().exit(0);
				break;
			}
			
		}
		
	}

}
