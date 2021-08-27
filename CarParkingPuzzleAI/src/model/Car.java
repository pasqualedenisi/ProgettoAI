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
	public ArrayList<Block> getBusyBlocks() {
		return busyBlocks;
	}

	
	public Boolean checkWin() {
		if ( ! mine ) return false;
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
	
	@Override
	public Coordinate[] getExtremities(VehicleOperator calculator) {
		return calculator.calculateExtremities(this);
	}
	
	@Override
	public void setBusyBlocks(ArrayList<Block> busyBlocks) {
		this.busyBlocks = busyBlocks;
	}
}
