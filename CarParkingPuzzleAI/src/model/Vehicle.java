package model;

import java.util.ArrayList;

public interface Vehicle {

	public ArrayList<Block> getBusyBlocks();
	public void setBusyBlocks(ArrayList<Block> busyBlocks);
	public Integer getBlockState();
	public Boolean getOrientation();
	public String getImageString();
	public Coordinate[] getExtremities(VehicleOperator calculator);
	public void initVehicleNumber();
	public int getVehicleNumber();
}
