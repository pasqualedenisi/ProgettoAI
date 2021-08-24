package model;

import constants.BlockType;

public class Block {

	Coordinate coordinate;
	private Integer state;
	private Vehicle occupier; //observer
	private Boolean winner;
	
	public Block(Integer row, Integer column) {
		state = BlockType.EMPTY;
		coordinate = new Coordinate(row,column);
		winner = false;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public void setOccupier(Vehicle occupier) {
		this.occupier = occupier;
		if ( occupier != null )
			setState(occupier.getBlockState());
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
}
