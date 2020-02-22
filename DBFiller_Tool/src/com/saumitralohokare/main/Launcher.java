package com.saumitralohokare.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Launcher {
	
	private static JFrame window;

	public static void main(String[] args) {
		window = new JFrame("DB Filler Tool");
		window.setSize(new Dimension(720, 720));
		window.setFocusTraversalKeysEnabled(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setIconImage(image);

		App app = new App();
		window.add(app);		
		
		window.setVisible(true);
	}

}
