package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VoteView extends JPanel {
	
	//JPanel
	private JPanel verticalContainer;
	private JPanel candidatePanel;
	private JPanel buttonPanel;
	
	//Buttons
	private JButton vote;
	private JButton exit;
	private JButton decrypt;
	
	//Labels
	private JLabel title;
	
	//Checkboxes
	private JCheckBox check1;
	private JCheckBox check2;
	private JCheckBox check3;
	
	public VoteView(ItemListener listener, ActionListener buttonListener){
		
		//JPanel setup
		verticalContainer = new JPanel();
		candidatePanel = new JPanel();
		buttonPanel = new JPanel();
		
		candidatePanel.setLayout(new BoxLayout(candidatePanel, BoxLayout.Y_AXIS));
		verticalContainer.setLayout(new BoxLayout(verticalContainer, BoxLayout.Y_AXIS));
		
		candidatePanel.setPreferredSize(new Dimension(512, 100));
		
		//Button setup
		vote = new JButton("Vote");
		exit = new JButton("Exit");
		decrypt = new JButton("Decrypt");
		vote.setName("vote");
		exit.setName("exit");
		decrypt.setName("decrypt");
		vote.addActionListener(buttonListener);
		exit.addActionListener(buttonListener);
		decrypt.addActionListener(buttonListener);
		
		//Label setup
		title = new JLabel("Cast your vote");
		
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
		
		candidatePanel.add(check1);
		candidatePanel.add(check2);
		candidatePanel.add(check3);
		
		buttonPanel.add(vote);
		buttonPanel.add(exit);
		
		verticalContainer.add(Box.createRigidArea(new Dimension(50,20)));
		verticalContainer.add(title);
		verticalContainer.add(Box.createRigidArea(new Dimension(50,50)));
		verticalContainer.add(candidatePanel);
		verticalContainer.add(Box.createRigidArea(new Dimension(50,50)));
		verticalContainer.add(buttonPanel);
		
		this.add(verticalContainer);
		this.setPreferredSize(new Dimension(512, 512));
	}

}
