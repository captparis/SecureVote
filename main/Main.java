package main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.ApplicationController;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Starting with main method");
		
		JFrame mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		ApplicationController applicationController = new ApplicationController(mainWindow);
		mainWindow.pack();
	}

}
