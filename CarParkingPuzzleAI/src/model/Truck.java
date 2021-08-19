package model;

import java.util.ArrayList;

import constants.BlockType;
import constants.Screens;

public class Truck implements Vehicle {

	private Boolean orientation;
	private ArrayList<Coordinate> busyBlocks;
	
	public Truck(ArrayList<Coordinate> busyBlocks) {
		super();
		this.busyBlocks = busyBlocks;
		if (busyBlocks.size()>1) {
			if ( busyBlocks.get(0).getRow() == busyBlocks.get(1).getRow() )
				orientation = false;
			else orientation = true;
		}
		for ( Coordinate c : busyBlocks )
			c.setOccupier(this);
	}

	@Override
	public Boolean getOrientation() {
		return orientation;
	}

	public void setOrientation(Boolean orientation) {
		this.orientation = orientation;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return busyBlocks.toString();
	}

	@Override
	public ArrayList<Coordinate> getBusyBlocks() {
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
