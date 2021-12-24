package model;

import constants.BlockType;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

public class Block {

	Coordinate coordinate;
	private Integer state;
	private Vehicle occupier; //observer
	private Boolean winner;
	
	
	
	
	public Block(Integer row, Integer column) {
		state = BlockType.EMPTY;
		coordinate = new Coordinate(row,column);
		winner = false;
		
		//ASP
		rowASP = row;
		columnASP = column;
	}

	public Integer getState() {
		return state;
	}

	private void setState(Integer state) {
		this.state = state;
	}
	
	private void setOccupier(Vehicle occupier) {
		this.occupier = occupier;
		if ( occupier != null ) {
			setState(occupier.getBlockState());
			//ASP
			this.occupierASP = occupier.getVehicleNumber();
		}
		else {
			this.occupierASP = 0;
		}
	}
	
	public Vehicle getOccupier() {
		return occupier;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	@Override
	public String toString() {
		return coordinate.toString();
	}
	
	public void setWinner(Boolean winner) {
		this.winner = winner;
	}
	
	public Boolean getWinner() {
		return winner;
	}
	
	public void reset() {
		setState(BlockType.EMPTY);
		setOccupier(null);
	}
	
	public void occupate(Vehicle v) {
		setState(v.getBlockState());
		setOccupier(v);
	}
	
	
	@Param(0)
	private int rowASP;
	@Param(1)
	private int columnASP;
	@Param(2)
	private int occupierASP;
	
	public Block() {
	}
	
	public Block(int r, int c, int o) {
		rowASP = r;
		columnASP = c;
		occupierASP = o;
	}

	public int getRowASP() {
		return rowASP;
	}

	public void setRowASP(int rowASP) {
		this.rowASP = rowASP;
	}

	public int getColumnASP() {
		return columnASP;
	}

	public void setColumnASP(int columnASP) {
		this.columnASP = columnASP;
	}

	public int getOccupierASP() {
		return occupierASP;
	}

	public void setOccupierASP(int occupierASP) {
		this.occupierASP = occupierASP;
	}
	
	
}
