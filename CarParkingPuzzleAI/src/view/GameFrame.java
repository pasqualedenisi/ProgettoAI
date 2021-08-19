package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import constants.Screens;

public class GameFrame extends JFrame {

	
	private ScreenManager screenContainer;
	
	public GameFrame() {
		//setUndecorated(true);
		/*
		GamePanel gp = new GamePanel();
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(50,50));
		add(space, BorderLayout.NORTH);
		add(gp, BorderLayout.CENTER);
		*/
		/*
		StartMenuPanel menu = new StartMenuPanel();
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension(50,50));
		add(space, BorderLayout.NORTH);

		add(menu);
		LevelsPanel levelPane = new LevelsPanel();
		add(levelPane);
		 */
		
		screenContainer = ScreenManager.getInstance();
		
		GamePanel gp = new GamePanel();
		LevelsPanel levelPane = new LevelsPanel();
		StartMenuPanel menu = new StartMenuPanel();
		add(screenContainer);
		screenContainer.add(menu);
		screenContainer.add(gp);
		screenContainer.add(levelPane);
		screenContainer.getInstance().getCards().addLayoutComponent(menu, Screens.START_MENU);
		screenContainer.getInstance().getCards().addLayoutComponent(gp, Screens.DURING_GAME);
		screenContainer.getInstance().getCards().addLayoutComponent(levelPane, Screens.LEVELS_OVERVIEW);
		screenContainer.getInstance().getCards().show(screenContainer, Screens.START_MENU);
		setSize(new Dimension(700,700));
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
	}
}
