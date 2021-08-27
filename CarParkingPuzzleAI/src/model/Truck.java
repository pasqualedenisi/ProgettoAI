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
			//b.setOccupier(this);
			b.occupate(this);
	}

	@Override
	public Boolean getOrientation() {
		return orientation;
	}

	public void setOrientation(Boolean orientation) {
		this.orientation = orientation;
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
	
	@Override
	public Coordinate[] getExtremities(VehicleOperator calculator) {
		return calculator.calculateExtremities(this);
	}
	
	@Override
	public void setBusyBlocks(ArrayList<Block> busyBlocks) {
		this.busyBlocks = busyBlocks;
	}
}
