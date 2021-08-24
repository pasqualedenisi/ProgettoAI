package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import constants.BlockType;
import model.Block;
import model.Game;
import model.Vehicle;
import view.GamePanel;

public class ClickHandler extends MouseAdapter {

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		int xPixels = e.getX();
		int yPixels = e.getY();
		Integer row = yPixels / BlockType.BLOCK_PIXELS;
		Integer column = xPixels / BlockType.BLOCK_PIXELS;
		//System.out.println(row.toString() + column.toString());
		Vehicle v = Game.getInstance().getBlocks()[row][column].getOccupier();
		GamePanel gp = (GamePanel)e.getSource();
		if (v != null) {
			gp.setSelected(v);
			System.out.println("vehicle selected");
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
			//System.out.println("startx= "+startX+ "\nendx= "+endX+"\nstarty= "+startY+"\nendy= "+endY);
			ArrayList<Block> freeBlocks = new ArrayList<Block>();
			Block blocks[][] = Game.getInstance().getBlocks();
			if  (v.getOrientation()) {
				int rowIterator = startY-1;
				while ( rowIterator >= 0 ) {
					//System.out.println("\nri = "+rowIterator+"\nstx = "+startX);
					if ( blocks[rowIterator][startX].getState() == BlockType.EMPTY ) {
						freeBlocks.add(blocks[rowIterator][startX]);
						--rowIterator;
					}
					else break;
				}
				rowIterator = endY+1;
				while ( rowIterator < BlockType.ROWS ) {
					if ( blocks[rowIterator][startX].getState() == BlockType.EMPTY ) {
						freeBlocks.add(blocks[rowIterator][startX]);
						++rowIterator;
					}
					else break;
				}
			}
			else {
				int columnIterator = startX-1;
				while ( columnIterator >= 0 ) {
					//System.out.println("\nri = "+rowIterator+"\nstx = "+startX);
					if ( blocks[startY][columnIterator].getState() == BlockType.EMPTY ) {
						freeBlocks.add(blocks[startY][columnIterator]);
						--columnIterator;
					}
					else break;
				}
				columnIterator = endX+1;
				while ( columnIterator < BlockType.COLUMNS ) {
					if ( blocks[startY][columnIterator].getState() == BlockType.EMPTY ) {
						freeBlocks.add(blocks[startY][columnIterator]);
						++columnIterator;
					}
					else break;
				}
			}
			gp.setFreeBlocks(freeBlocks);
			System.out.println(gp.getFreeBlocks());
		}
		else {
			Vehicle selected = gp.getSelected();
			if ( selected != null ) {
				System.out.println("Pressing empty  block: vehicle selected");
				ArrayList<Block> freeBlocks = gp.getFreeBlocks();
				Block movedTo = null;
				for ( Block b : freeBlocks ) {
					if ( b.getCoordinate().getRow() == row && b.getCoordinate().getColumn() == column ) {
						movedTo = b;
						break;
					}
				}
				if ( movedTo != null )
					selected.move(movedTo);
			}
			else
				System.out.println("Pressing empty  block: vehicle not selected");
			resetPanel(gp);
		}
	}
	
	
	void resetPanel(GamePanel gp) {
		gp.setSelected(null);
		gp.setFreeBlocks(null);
	}
	
}
