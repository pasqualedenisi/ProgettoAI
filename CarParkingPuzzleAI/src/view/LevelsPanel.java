package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Screens;
import controller.LevelListener;
import levels.MatchLevel;

public class LevelsPanel extends JPanel {

	ArrayList<JButton> levelButtons;
	JPanel levelGrid;
	
	public LevelsPanel() {
		levelButtons = new ArrayList<JButton>();
		setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Select level:");
		titlePanel.add(titleLabel);
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setAlignmentY(CENTER_ALIGNMENT);
		add(titlePanel, BorderLayout.NORTH);
		levelGrid = new JPanel();
		levelGrid.setLayout(new FlowLayout());
		add(levelGrid, BorderLayout.CENTER);
		createLevels();
	}
	
	private void createLevels() {
		try {
			BufferedReader bIn = new BufferedReader(new FileReader(Screens.LEVEL_PATH));
			while (bIn.ready()) { //scan file rows
				String line = bIn.readLine();
				//System.out.println(line);
				String regex = MatchLevel.level+"(\\d+)";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(line);
				if ( !matcher.matches() )
					continue;
				else {
					String levelNumber = matcher.group(1);
					System.out.println(levelNumber);
					JButton button = new JButton(levelNumber);
					levelButtons.add(button);
					levelGrid.add(button);
					button.addActionListener(new LevelListener());
				}
			}
			bIn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
