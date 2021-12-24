package model;

import java.util.ArrayList;

import constants.BlockType;

public class VehicleOperator {

	public Coordinate[] calculateExtremities(Vehicle v){
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
		Coordinate returnCoordinate[] = new Coordinate[2];
		returnCoordinate[0] = new Coordinate(startY, startX);
		returnCoordinate[1] = new Coordinate(endY, endX);
		return returnCoordinate;
	}
	
	public Coordinate calculateDlvPivot(Vehicle v) {
		Coordinate[] extremities = calculateExtremities(v);
		return extremities[0];
	}
	
	public void move(Vehicle v, Block destinationBlock) {
		Game.getInstance().incrementMoves();
		System.out.println("Moving to"+destinationBlock);
		Integer minDistance = BlockType.COLUMNS + BlockType.ROWS;
		Integer currentDistance;
		Integer signedDistance = minDistance;
		Block blockMatrix[][] = Game.getInstance().getBlocks();
		for ( Block b : v.getBusyBlocks() ) {
			if ( v.getOrientation() )
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
		for ( Block b : v.getBusyBlocks() ) {
			Integer newPosition;
			Block newBlockBusy;
			if ( v.getOrientation() ) {
				newPosition = b.getCoordinate().getRow()-signedDistance;
				newBlockBusy = blockMatrix[newPosition][b.getCoordinate().getColumn()];
			}
			else {
				newPosition = b.getCoordinate().getColumn()-signedDistance;
				newBlockBusy = blockMatrix[b.getCoordinate().getRow()][newPosition];
			}
			System.out.println("Da" + b + " a " + newBlockBusy);
			//newBlockBusy.setOccupier(this);
			System.out.println("occupier di "+newBlockBusy+" = "+newBlockBusy.getOccupier());
			newBusyList.add(newBlockBusy);
			b.reset();
			System.out.println("occupier di "+b+" = "+b.getOccupier());
		}
		for ( Block b : newBusyList )
			b.occupate(v);
		v.setBusyBlocks(newBusyList);
		//System.out.println("New blocks = "+busyBlocks);
		Game.getInstance().printMatrix();
		if (Game.getInstance().checkWin(v))
			System.out.println("win");
	}
	
//	public void dlvMove(Vehicle v) {
//		for ( Block b : v.getBusyBlocks() )
//
//	}
}
