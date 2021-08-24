package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import constants.BlockType;
import constants.Screens;
import controller.ClickHandler;
import model.Block;
import model.Car;
import model.Game;
import model.Vehicle;

public class GamePanel extends JPanel {

	Game game;
	HashMap<String, Image> images;
	ArrayList<Block> freeBlocks;
	Vehicle selected;
	
	
	public GamePanel() {
		images = new HashMap<>();
		game = Game.getInstance();
		freeBlocks = new ArrayList<>();
		try {
			images.put(Screens.MY_CAR_H, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.MY_CAR_H)));
			images.put(Screens.MY_CAR_V, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.MY_CAR_V)));
			images.put(Screens.OTHER_CAR_H, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.OTHER_CAR_H)));
			images.put(Screens.OTHER_CAR_V, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.OTHER_CAR_V)));
			images.put(Screens.TRUCK_H, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.TRUCK_H)));
			images.put(Screens.TRUCK_V, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.TRUCK_V)));
		} catch (IOException e) {
			System.out.println("Voolaareee oooooohhhh cantareee oooooohhh");
		}
		setPreferredSize(new Dimension(BlockType.BLOCK_PIXELS*BlockType.COLUMNS,BlockType.BLOCK_PIXELS*BlockType.ROWS));
		setBackground(Color.GRAY);
		addMouseListener(new ClickHandler());
	}
	
	/*
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Block[][] b = game.getBlocks();
		for ( int i = 0; i < b.length; ++i ) {
			for ( int j = 0; j < b[i].length; ++j )	
				switch (b[i][j].getState()) {
				case BlockType.MYCAR:
					g.drawString("mine", j*BlockType.BLOCK_PIXELS, i*BlockType.BLOCK_PIXELS+20);
					break;
				case BlockType.CAR:
					g.drawString("other", j*BlockType.BLOCK_PIXELS, i*BlockType.BLOCK_PIXELS+20);
					break;
				case BlockType.TRUCK:
					g.drawString("truck", j*BlockType.BLOCK_PIXELS, i*BlockType.BLOCK_PIXELS+20);
					break;
				default:
					g.drawString("empty", j*BlockType.BLOCK_PIXELS, i*BlockType.BLOCK_PIXELS+20);
					break;
					
			}
		}	
	}
	 */
	
	public ArrayList<Block> getFreeBlocks() {
		return freeBlocks;
	}

	public void setFreeBlocks(ArrayList<Block> freeBlocksPassed) {
		if (freeBlocksPassed == null)
			freeBlocks.clear();
		else 
			freeBlocks = freeBlocksPassed;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Car myCar = Game.getInstance().getMyCar();
		drawVehicle(myCar, g);
		ArrayList<Vehicle> otherVehicles = Game.getInstance().getOtherVehicles();
		for ( Vehicle v : otherVehicles ) {
			drawVehicle(v, g);
		}
		g.setColor(Color.GREEN);
		for ( Block b : freeBlocks )
			g.fillRect(b.getCoordinate().getColumn()*BlockType.BLOCK_PIXELS+BlockType.FREE_MARGIN/2, b.getCoordinate().getRow()*BlockType.BLOCK_PIXELS+BlockType.FREE_MARGIN/2, BlockType.BLOCK_PIXELS-BlockType.FREE_MARGIN, BlockType.BLOCK_PIXELS-BlockType.FREE_MARGIN);
	}
	
	private void drawVehicle(Vehicle v, Graphics g){
		/*
		ArrayList<Integer> rowIndexes = new ArrayList<Integer>();
		ArrayList<Integer> columnIndexes = new ArrayList<Integer>();
		for ( Block b : v.getBusyBlocks() ) {
			rowIndexes.add(b.getCoordinate().getRow());
			columnIndexes.add(b.getCoordinate().getColumn());
		}
		Integer startX = Collections.min(columnIndexes);
		Integer startY = Collections.min(rowIndexes);
		Integer endX = Collections.max(columnIndexes);
		Integer endY = Collections.max(rowIndexes);
		*/
		Integer startX = BlockType.COLUMNS;
		Integer startY = BlockType.ROWS;
		Integer endX = 0;
		Integer endY = 0;
		Integer blockRow;
		Integer blockColumn;
		for ( Block b : v.getBusyBlocks() ) {
			blockRow = b.getCoordinate().getRow();
			blockColumn = b.getCoordinate().getColumn();
			if ( blockColumn < startX )
				startX = blockColumn;
			if ( blockColumn > endX )
				endX = blockColumn;
			if ( blockRow < startY )
				startY = blockRow;
			if ( blockRow > endY )
				endY = blockRow;
		}
		g.drawImage(images.get(v.getImageString()), startX*BlockType.BLOCK_PIXELS, startY*BlockType.BLOCK_PIXELS, (endX-startX+1)*BlockType.BLOCK_PIXELS, (endY-startY+1)*BlockType.BLOCK_PIXELS, null);
	}

	public Vehicle getSelected() {
		return selected;
	}

	public void setSelected(Vehicle selected) {
		this.selected = selected;
	}
	
}
