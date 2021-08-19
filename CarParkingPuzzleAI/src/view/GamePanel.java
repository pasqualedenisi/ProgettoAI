package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import constants.BlockType;
import constants.Screens;
import model.Car;
import model.Coordinate;
import model.Game;
import model.Vehicle;

public class GamePanel extends JPanel {

	Game game;
	HashMap<String, Image> images;
	
	
	public GamePanel() {
		images = new HashMap<>();
		game = Game.getInstance();
		try {
			//Image myCarImage = ImageIO.read(getClass().getResourceAsStream("/resources/car1.png"));
			//Image otherCarImage = ImageIO.read(getClass().getResourceAsStream("/resources/car2.png"));
			images.put(Screens.MY_CAR_H, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.MY_CAR_H)));
			images.put(Screens.MY_CAR_V, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.MY_CAR_V)));
			images.put(Screens.OTHER_CAR_H, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.OTHER_CAR_H)));
			images.put(Screens.OTHER_CAR_V, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.OTHER_CAR_V)));
			images.put(Screens.TRUCK_H, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.TRUCK_H)));
			images.put(Screens.TRUCK_V, ImageIO.read(getClass().getResourceAsStream(Screens.IMAGES_PATH + Screens.TRUCK_V)));
		} catch (IOException e) {
			System.out.println("Voolaareee oooooohhhh cantareee oooooohhh");
		}
		//setPreferredSize(new Dimension(300,300));
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
	
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Car myCar = Game.getInstance().getMyCar();
		/*
		Integer startX = Math.min(myCar.getBusyBlocks().get(0).getColumn(), myCar.getBusyBlocks().get(1).getColumn());
		Integer startY = Math.min(myCar.getBusyBlocks().get(0).getRow(), myCar.getBusyBlocks().get(1).getRow());
		g.drawImage(images.get(0), startX*BlockType.BLOCK_PIXELS, startY*BlockType.BLOCK_PIXELS, 2*BlockType.BLOCK_PIXELS, BlockType.BLOCK_PIXELS, null);
		 */
		ArrayList<Vehicle> otherVehicles = Game.getInstance().getOtherVehicles();
		ArrayList<Integer> rowIndexes = new ArrayList<Integer>();
		ArrayList<Integer> columnIndexes = new ArrayList<Integer>();
		for ( Vehicle v : otherVehicles ) {
			for ( Coordinate c : v.getBusyBlocks() ) {
				rowIndexes.add(c.getRow());
				columnIndexes.add(c.getColumn());
			}
			Integer startX = Collections.min(columnIndexes);
			Integer startY = Collections.min(rowIndexes);
			Integer endX = Collections.max(columnIndexes);
			Integer endY = Collections.max(rowIndexes);
			System.out.println("Start x = " + startX.toString() + 
					" Start y = " + startY.toString() +
					" End x = " + endX.toString() +
					" End y = " + endY.toString());
			g.drawImage(images.get(v.getImageString()), startX*BlockType.BLOCK_PIXELS, startY*BlockType.BLOCK_PIXELS, (endX-startX+1)*BlockType.BLOCK_PIXELS, (endY-startY+1)*BlockType.BLOCK_PIXELS, null);
			/*
			if ( !v.getOrientation() && rowIndexes.size() == BlockType.CAR_SIZE )
				g.drawImage(images.get(Screens.), startX*BlockType.BLOCK_PIXELS, startY*BlockType.BLOCK_PIXELS, (endX-startX+1)*BlockType.BLOCK_PIXELS, (endY-startY+1)*BlockType.BLOCK_PIXELS, null);
			else if ( v.getOrientation() && rowIndexes.size() == BlockType.CAR_SIZE )
				g.drawImage(images.get(0), startX*BlockType.BLOCK_PIXELS, startY*BlockType.BLOCK_PIXELS, (endX-startX+1)*BlockType.BLOCK_PIXELS, (endY-startY+1)*BlockType.BLOCK_PIXELS, null);
			else if ( !v.getOrientation() && rowIndexes.size() == BlockType.TRUCK_SIZE )
				g.drawImage(images.get(0), startX*BlockType.BLOCK_PIXELS, startY*BlockType.BLOCK_PIXELS, (endX-startX+1)*BlockType.BLOCK_PIXELS, (endY-startY+1)*BlockType.BLOCK_PIXELS, null);
			else if ( v.getOrientation() && rowIndexes.size() == BlockType.TRUCK_SIZE )
				g.drawImage(images.get(0), startX*BlockType.BLOCK_PIXELS, startY*BlockType.BLOCK_PIXELS, (endX-startX+1)*BlockType.BLOCK_PIXELS, (endY-startY+1)*BlockType.BLOCK_PIXELS, null);
			*/
				
			rowIndexes.clear();
			columnIndexes.clear();
		}
		for ( Coordinate c : myCar.getBusyBlocks() ) {
			rowIndexes.add(c.getRow());
			columnIndexes.add(c.getColumn());
		}
		Integer startX = Collections.min(columnIndexes);
		Integer startY = Collections.min(rowIndexes);
		Integer endX = Collections.max(columnIndexes);
		Integer endY = Collections.max(rowIndexes);
		System.out.println("Start x = " + startX.toString() + 
				" Start y = " + startY.toString() +
				" End x = " + endX.toString() +
				" End y = " + endY.toString());
		g.drawImage(images.get(myCar.getImageString()), startX*BlockType.BLOCK_PIXELS, startY*BlockType.BLOCK_PIXELS, (endX-startX+1)*BlockType.BLOCK_PIXELS, (endY-startY+1)*BlockType.BLOCK_PIXELS, null);
	
		/*
		ArrayList<Vehicle> otherVehicles = Game.getInstance().getOtherVehicles();
		for ( Vehicle v : otherVehicles ) {
			if ( v instanceof Truck ) {
				
			}
		}
		
		for ( int i = 0; i < b.length; ++i ) {
			for ( int j = 0; j < b[i].length; ++j )
				g.drawRect(i*blockSize, j*blockSize, i*blockSize+blockSize, j*blockSize+blockSize);
		}
		*/
	}
}
