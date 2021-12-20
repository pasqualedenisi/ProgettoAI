package main;

import dlvManagement.DLVManager;
import model.Game;
import view.GameFrame;

public class Main {

	public static void main(String[] args) {
		Game g = Game.getInstance();
		GameFrame frame = new GameFrame();
	}

}
