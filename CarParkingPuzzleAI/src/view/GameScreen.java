package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import dlvManagement.DLVManager;
import model.Game;

public class GameScreen extends JPanel {
	GamePanel gp = new GamePanel();
	JLabel moves = new JLabel();

	public GameScreen() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		JPanel controlPane = new JPanel();
		JButton solve = new JButton("Solve");
		controlPane.add(solve);
		solve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().factPassing();
				DLVManager.getInstance().callSynchDlv(Game.getInstance().getMaxMovesDlv(), GameScreen.this); //da spostare nel bottone solve
				gp.repaint();
			}
		});
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().setLevel(Game.getInstance().getLevel());
				GameScreen.this.gp.repaint();
			}
		});
		controlPane.add(restartButton);
		controlPane.add(moves);
		
		add(gp);
		JSeparator s = new JSeparator(JSeparator.VERTICAL);
		s.setMaximumSize(new Dimension(10,10));
		add(s, BorderLayout.LINE_START);
		add(controlPane);
		Game.getInstance().attachObserver(this);
	}

	public void updateMoves() {
		moves.setText(Game.getInstance().getMoves().toString());
	}
}
