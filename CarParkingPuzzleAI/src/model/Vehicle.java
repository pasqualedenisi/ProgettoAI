package model;

import java.util.ArrayList;

public interface Vehicle {

	void move();
	public ArrayList<Coordinate> getBusyBlocks();
	public Integer getBlockState();
	public Boolean getOrientation();
	public String getImageString();
}
