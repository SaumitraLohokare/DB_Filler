package com.saumitralohokare.main;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class App extends JPanel{
	
	private JScrollBar scrollPane;
	
	private GridBagConstraints gbc;
	
	private List<JTextField> labels;
	private List<JLabel> fileNames;
	private List<JButton> browseButton;
	private HashMap<Integer, File> files;
	
	private int fieldCount = 0, generateCount = 0;

	public App() {

		labels = new ArrayList<>();
		fileNames = new ArrayList<>();
		browseButton = new ArrayList<>();
		
		setSize(new Dimension(720, 720));
		
		setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(20, 20, 20, 20);
		
		/*
		scrollPane = new JScrollBar();
		gbc.gridx = 7;
		gbc.gridy = 0;
		gbc.gridheight = 10;
		gbc.fill = GridBagConstraints.VERTICAL;
		add(scrollPane, gbc);		
		*/
		createPage();
	}
	
	public void createPage() {
		// THE FIELDS SELECTION
		JLabel fieldCounterLable, generateNumberLabel;
		JTextField generateNumber, fieldCounter;
		JButton plus, minus, go, gen, plusGen, minusGen;
		fieldCounterLable = new JLabel("Fields : ");
		fieldCounter = new JTextField(String.valueOf(fieldCount));
		plus = new JButton(" + ");
		minus = new JButton(" - ");
		go = new JButton("Make Fields");
		gen = new JButton("Generate");
		generateNumberLabel = new JLabel("Generate : ");
		generateNumber = new JTextField(String.valueOf(generateCount));
		plusGen = new JButton(" + ");
		minusGen = new JButton(" - ");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		fieldCounterLable.setVisible(true);
		add(fieldCounterLable, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		fieldCounter.setVisible(true);
		add(fieldCounter, gbc);
		
		plus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldCount += 1;
				fieldCounter.setText(String.valueOf(fieldCount));
			}
			
		});
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		plus.setVisible(true);
		add(plus, gbc);
		
		minus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldCount -= 1;
				fieldCounter.setText(String.valueOf(fieldCount));
			}
			
		});
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		minus.setVisible(true);
		add(minus, gbc);
		
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!labels.isEmpty()) {
					for (JTextField comp : labels) {
						comp.setVisible(false);
						remove(comp);
					}
				}
				
				if(!fileNames.isEmpty()) {
					for (JLabel comp : fileNames) {
						comp.setVisible(false);
						remove(comp);
					}
				}
				
				if(!browseButton.isEmpty()) {
					for (JButton comp : browseButton) {
						comp.setVisible(false);
						remove(comp);
					}
				}
				
				// generate fields here
				labels = new ArrayList<>();
				fileNames = new ArrayList<>();
				browseButton = new ArrayList<>();
				files = new HashMap<>();
				
				for (int i = 0; i < Integer.parseInt(fieldCounter.getText()); i++) {
					JTextField tempTF = new JTextField(20);
					JLabel tempLab = new JLabel("No file selected.");
					JButton tempB = new JButton(UIManager.getIcon("FileView.directoryIcon"));
					
					labels.add(tempTF);
					fileNames.add(tempLab);
					browseButton.add(tempB);
					
					tempB.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							OpenFile of = new OpenFile();
							int i = browseButton.indexOf(tempB);
							try {
								files.put(i, of.pickFile());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							tempLab.setText(files.get(i).getName());
						}
						
					});
					
					gbc.gridx = 0;
					gbc.gridy = i + 2;
					gbc.gridwidth = 3;
					gbc.fill = GridBagConstraints.HORIZONTAL;
					tempTF.setVisible(true);
					add(tempTF, gbc);
					
					gbc.gridx = 3;
					gbc.gridy = i + 2;
					gbc.gridwidth = 3;
					gbc.fill = GridBagConstraints.HORIZONTAL;
					tempLab.setVisible(true);
					add(tempLab, gbc);
					
					gbc.gridx = 6;
					gbc.gridy = i + 2;
					gbc.gridwidth = 1;
					tempB.setVisible(true);
					add(tempB, gbc);
				}
				
				updateUI();
			}
			
		});
	
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		go.setVisible(true);
		add(go, gbc);

		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		generateNumberLabel.setVisible(true);
		add(generateNumberLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		generateNumber.setVisible(true);
		add(generateNumber, gbc);
		
		plusGen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generateCount += 1;
				generateNumber.setText(String.valueOf(generateCount));
			}
			
		});
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		plusGen.setVisible(true);
		add(plusGen, gbc);
		
		minusGen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				generateCount -= 1;
				generateNumber.setText(String.valueOf(generateCount));
			}
			
		});
		
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		minusGen.setVisible(true);
		add(minusGen, gbc);
		
		
		gen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OpenFile of = new OpenFile();	
				String path = "C:\\";
				try {
					path = of.pickPath();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				List<String> values = new ArrayList<>();
				for (int i = 0; i < Integer.parseInt(generateNumber.getText()); i++) {
					values.add(generateData());
				}
				File results = new File(path + "\\Results_" + LocalDate.now().toString() + ".txt");
				try {
					results.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				results.setWritable(true);
				FileWriter fr = null;
		        BufferedWriter br = null;
		        try{
		            fr = new FileWriter(path + "\\Results_" + LocalDate.now().toString() + ".txt");
		            br = new BufferedWriter(fr);
		            for(int i = 0; i < values.size(); i++){
		            	String dataWithNewLine = values.get(i) + System.getProperty("line.separator");
		                br.write(dataWithNewLine);
		            }
		        } catch (IOException x) {
		            x.printStackTrace();
		        }finally{
		            try {
		                br.close();
		                fr.close();
		            } catch (IOException y) {
		                y.printStackTrace();
		            }
		        }
		        
			}
			
		});
		
		gbc.gridx = 5;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gen.setVisible(true);
		add(gen, gbc);
		
	}

	protected String generateData() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		for(int i = 0; i < labels.size(); i++) {
			JTextField comp = labels.get(i);
			sb.append("\"" + comp.getText() + "\" : ");
			sb.append("\"" + getRandomFromFile(files.get(i)) + "\"");
			if (i < labels.size() - 1) sb.append(", ");
		}
		sb.append(" }");
				
		return sb.toString();
	}
	
	private String getRandomFromFile(File f) {
		String res = "";
		
		try {
			List<String> lines = Files.readAllLines(f.toPath());
			Random r = new Random();
			res = lines.get(r.nextInt(lines.size()));
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return res;
	}

}
