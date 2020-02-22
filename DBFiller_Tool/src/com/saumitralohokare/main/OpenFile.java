package com.saumitralohokare.main;

import java.io.File;

import javax.swing.JFileChooser;

public class OpenFile {

	JFileChooser fileChooser = new JFileChooser();
	JFileChooser chooser = new JFileChooser();

	public File pickFile() throws Exception {

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			return file;
		}

		return null;
	}

	public String pickPath() throws Exception {
		chooser.setDialogTitle("Choose Directory to save to");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//
		// disable the "All files" option.
		//
		chooser.setAcceptAllFileFilterUsed(false);
		//
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();			
		}

		return "C:\\";
	}

}
