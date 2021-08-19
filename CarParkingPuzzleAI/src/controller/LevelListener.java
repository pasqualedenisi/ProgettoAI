package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;

import constants.Screens;
import levels.MatchLevel;
import model.Car;
import model.Coordinate;
import model.Game;
import model.Truck;
import view.ScreenManager;

public class LevelListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String level = button.getText();
		Game.getInstance().setLevel(Integer.parseInt(level));
		ScreenManager screenContainer = ScreenManager.getInstance();
		screenContainer.getCards().show(screenContainer, Screens.DURING_GAME);
	}

}
