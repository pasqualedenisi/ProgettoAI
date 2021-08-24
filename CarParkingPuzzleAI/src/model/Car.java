package model;

import java.util.ArrayList;

import constants.BlockType;
import constants.Screens;

public class Car implements Vehicle {
	
	private Boolean orientation; //true -> verticale; false -> orizzontale
	private ArrayList<Block> busyBlocks;
	private Boolean mine;
	
	

	public Car(ArrayList<Block> busyBlocks) {
		super();
		this.busyBlocks = busyBlocks;
		mine = false;
		if (busyBlocks.size()>1) {
			if ( busyBlocks.get(0).getCoordinate().getRow() == busyBlocks.get(1).getCoordinate().getRow() )
				orientation = false;
			else orientation = true;
		}
		for ( Block b : busyBlocks )
			b.setOccupier(this);
	}

	@Override
	public Boolean getOrientation() {
		return orientation;
	}

	public void setOrientation(Boolean orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public ArrayList<Block> getBusyBlocks() {
		return busyBlocks;
	}

	@Override
	public void move(Block destinationBlock) {
		System.out.println("Moving to"+destinationBlock);
		Integer minDistance = BlockType.COLUMNS + BlockType.ROWS;
		Integer currentDistance;
		Integer signedDistance = minDistance;
		Block blockMatrix[][] = Game.getInstance().getBlocks();
		for ( Block b : busyBlocks ) {
			blockMatrix[b.getCoordinate().getRow()][b.getCoordinate().getColumn()].setState(BlockType.EMPTY);
			if ( orientation )
				currentDistance = b.getCoordinate().getRow()-destinationBlock.getCoordinate().getRow();
			else
				currentDistance = b.getCoordinate().getColumn()-destinationBlock.getCoordinate().getColumn();
			if ( Math.abs(currentDistance) < minDistance ) {
				minDistance = Math.abs(currentDistance);
				signedDistance = currentDistance;
			}
		}
		System.out.println("min dist = " + minDistance);
		ArrayList<Block> newBusyList = new ArrayList<Block>();
		for ( Block b : busyBlocks ) {
			Integer newPosition;
			Block newBlockBusy;
			if ( orientation ) {
				newPosition = b.getCoordinate().getRow()-signedDistance;
				newBlockBusy = blockMatrix[newPosition][b.getCoordinate().getColumn()];
			}
			else {
				newPosition = b.getCoordinate().getColumn()-signedDistance;
				newBlockBusy = blockMatrix[b.getCoordinate().getRow()][newPosition];
			}
			newBlockBusy.setState(this.getBlockState());
			newBlockBusy.setOccupier(this);
			newBusyList.add(newBlockBusy);
			b.setOccupier(null);
		}
		busyBlocks = newBusyList;
		System.out.println("New blocks = "+busyBlocks);
		Game.getInstance().printMatrix();
		if ( getBlockState() == BlockType.MYCAR )
			if (checkWin())
				System.out.println("Finish");
	}
	
	private Boolean checkWin() {
		for ( Block b : busyBlocks )
			if (b.getWinner())
				return true;
		return false;
	}

	@Override
	public String toString() {
		return busyBlocks.toString();
	}

	@Override
	public Integer getBlockState() {
		if (mine)
			return BlockType.MYCAR;
		return BlockType.CAR;
	}

	public void setMine(boolean b) {
		mine = b;
	}
	
	@Override
	public String getImageString() {
		if ( orientation && mine )
			return Screens.MY_CAR_V;
		else if ( orientation && !mine )
			return Screens.OTHER_CAR_V;
		else if ( !orientation && mine )
			return Screens.MY_CAR_H;
		else if ( !orientation && !mine )
			return Screens.OTHER_CAR_H;
		return null;
	}
}
