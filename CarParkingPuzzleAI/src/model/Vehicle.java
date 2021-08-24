package model;

import java.util.ArrayList;

public interface Vehicle {

	void move(Block b);
	public ArrayList<Block> getBusyBlocks();
	public Integer getBlockState();
	public Boolean getOrientation();
	public String getImageString();
}
