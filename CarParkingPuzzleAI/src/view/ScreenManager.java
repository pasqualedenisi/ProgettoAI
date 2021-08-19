package view;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class ScreenManager extends JPanel {

	private static ScreenManager instance;
	private CardLayout cards;
	
	public static ScreenManager getInstance() {
		if (instance == null)
			instance = new ScreenManager();
		return instance;
	}
	
	public CardLayout getCards() {
		return cards;
	}
	
	private ScreenManager() {
		cards = new CardLayout();
		setLayout(cards);
	} 
}
