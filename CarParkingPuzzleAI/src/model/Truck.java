package model;

import java.util.ArrayList;

import constants.BlockType;
import constants.Screens;

public class Truck implements Vehicle {

	private Boolean orientation;
	private ArrayList<Block> busyBlocks;
	
	public Truck(ArrayList<Block> busyBlocks) {
		super();
		this.busyBlocks = busyBlocks;
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
			System.out.println("Da" + b + " a " + newBlockBusy);
			newBlockBusy.setState(this.getBlockState());
			//newBlockBusy.setOccupier(this);
			System.out.println("occupier di "+newBlockBusy+" = "+newBlockBusy.getOccupier());
			newBusyList.add(newBlockBusy);
			b.setOccupier(null);
			System.out.println("occupier di "+b+" = "+b.getOccupier());
		}
		busyBlocks = newBusyList;
		for ( Block b : busyBlocks ) {
			b.setOccupier(this);
		}
		System.out.println("New blocks = "+busyBlocks);
		Game.getInstance().printMatrix();
		
	}
	
	@Override
	public String toString() {
		return busyBlocks.toString();
	}

	@Override
	public ArrayList<Block> getBusyBlocks() {
		return busyBlocks;
	}
	
	@Override
	public Integer getBlockState() {
		return BlockType.TRUCK;
	}

	@Override
	public String getImageString() {
		if ( orientation )
			return Screens.TRUCK_V;
		return Screens.TRUCK_H;
	}
}
