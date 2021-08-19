package model;

import java.util.ArrayList;

import constants.BlockType;
import constants.Screens;

public class Car implements Vehicle {
	
	private Boolean orientation; //true -> verticale; false -> orizzontale
	private ArrayList<Coordinate> busyBlocks;
	private Boolean mine;
	
	

	public Car(ArrayList<Coordinate> busyBlocks) {
		super();
		this.busyBlocks = busyBlocks;
		mine = false;
		if (busyBlocks.size()>1) {
			if ( busyBlocks.get(0).getRow() == busyBlocks.get(1).getRow() )
				orientation = false;
			else orientation = true;
		}
	}

	@Override
	public Boolean getOrientation() {
		return orientation;
	}

	public void setOrientation(Boolean orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public ArrayList<Coordinate> getBusyBlocks() {
		return busyBlocks;
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
