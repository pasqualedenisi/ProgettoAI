package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constants.BlockType;
import constants.Screens;
import levels.MatchLevel;
import view.GameScreen;

//singleton
public class Game {

	private Block[][] blocks;
	private Integer moves;
	private Car myCar;
	private ArrayList<Vehicle> otherVehicles;
	private Integer level;
	private static Game instance;
	private Boolean isMatrixWritten;
	private GameScreen movesDisplayer;
	
	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	public Block[][] getBlocks() {
		return blocks;
	}

	public Car getMyCar() {
		return myCar;
	}

	public ArrayList<Vehicle> getOtherVehicles() {
		return otherVehicles;
	}

	public Integer getLevel() {
		return level;
	}

	private Game() {
		blocks = new Block[BlockType.ROWS][BlockType.COLUMNS];
		for ( int i = 0; i < BlockType.ROWS; ++i )
			for ( int j = 0; j < BlockType.COLUMNS; ++j )
				blocks[i][j] = new Block(i,j);
		moves=0;
		level = 1;
		otherVehicles = new ArrayList<Vehicle>();
		isMatrixWritten = false;
	}

	public void setLevel(Integer levelPassed) {
		resetMoves();
		this.level = levelPassed;
		if ( isMatrixWritten ) {
			resetMatrix();
			otherVehicles.clear();
		}
		try {
			BufferedReader bIn = new BufferedReader(new FileReader(Screens.LEVEL_PATH));
			Boolean levelFound = false;
			while (bIn.ready()) { //scan file rows
				String line = bIn.readLine();
				System.out.println("LINE = "+line);
				String levelLine = MatchLevel.level+level.toString();
				if ( !levelFound && line.equals(levelLine)) {
					System.out.println("primo if");
					levelFound=true;
				}
				else if ( Pattern.compile(MatchLevel.levelRegex).matcher(line).lookingAt() && levelFound ) {
					System.out.print("secondo if: ");
					System.out.println("prossimo livello raggiunto");
					break;
				}
				else if (!levelFound) {System.out.println("terzo if"); continue;}
				else {
					System.out.println("quarto if");
					String regex = "\\t(\\w+:)\\s+(.*)";
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(line);
					if ( matcher.matches() ) {
						String vehicle = matcher.group(1);
						String busyBlocks = matcher.group(2);
						regex = "(\\d+,\\d+);*";
						pattern = Pattern.compile(regex);
						matcher = pattern.matcher(busyBlocks);
						//System.out.println("Vehicle = " + vehicle);
						ArrayList<Block> blocks = new ArrayList<Block>();
						while (matcher.find()) {
							String coordinateString = matcher.group(1);
							String[] parts = coordinateString.split(",");
							Coordinate coordinate = new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
							blocks.add(Game.getInstance().getBlocks()[coordinate.getRow()][coordinate.getColumn()]);
						}
						switch(vehicle) {
							case (MatchLevel.myCar):
								myCar = new Car(blocks);
								myCar.setMine(true);
								break;
							case (MatchLevel.anotherCar):
								Car anotherCar = new Car(blocks);
								otherVehicles.add(anotherCar);
								break;
							case (MatchLevel.truck):
								Truck truck = new Truck(blocks);
								otherVehicles.add(truck);
								break;
							case (MatchLevel.finalPosition):
								if ( blocks.get(0) != null )
									blocks.get(0).setWinner(true);
								break;
							default:
								break;
						}
					}
				}
			}
			disposeVehicles();
			printMatrix();
			bIn.close();
			isMatrixWritten = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printMatrix() {
		for ( int i = 0; i < BlockType.ROWS; ++i ) {
			for ( int j = 0; j < BlockType.COLUMNS; ++j )
				System.out.print(blocks[i][j].getState() + " ");
			System.out.println();
		}
	}

	private void disposeVehicles() {
		for ( Block b : myCar.getBusyBlocks() ) {
			//blocks[b.getCoordinate().getRow()][b.getCoordinate().getColumn()].setOccupier(myCar);
			b.occupate(myCar);
		}
		for ( Vehicle v : otherVehicles ) {
			for ( Block b : v.getBusyBlocks() ) {
				//blocks[b.getCoordinate().getRow()][b.getCoordinate().getColumn()].setOccupier(v);
				b.occupate(v);
			}
		}
	}
	
	private void resetMatrix() {
		for ( int i = 0; i < BlockType.ROWS; ++i )
			for ( int j = 0; j < BlockType.COLUMNS; ++j )
				blocks[i][j].reset();
	}
	

	public void incrementMoves() {
		++moves;
		movesDisplayer.updateMoves();
	}
	
	public void resetMoves() {
		moves=0;
		movesDisplayer.updateMoves();
	}

	public void attachObserver(GameScreen gameScreen) {
		movesDisplayer = gameScreen;
	}
	
	public Integer getMoves() {
		return moves;
	}
	
	public Boolean checkWin(Vehicle v) {
		if ( !(v instanceof Car) )
			return false;
		Car carMoved = (Car) v;
		return carMoved.checkWin();
	}
}
