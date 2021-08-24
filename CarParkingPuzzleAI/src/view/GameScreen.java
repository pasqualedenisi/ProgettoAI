package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class GameScreen extends JPanel {

	public GameScreen() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		GamePanel gp = new GamePanel();
		JPanel controlPane = new JPanel();
		JButton pause = new JButton("Pause");
		controlPane.add(pause);
		pause.addActionListener(null);
		controlPane.setBackground(Color.BLUE);
		add(gp);
		JSeparator s = new JSeparator(JSeparator.VERTICAL);
		s.setMaximumSize(new Dimension(10,10));
		add(s, BorderLayout.LINE_START);
		add(controlPane);
	}
}
