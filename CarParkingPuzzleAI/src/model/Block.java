package model;

import constants.BlockType;

public class Block {

	private Integer state;
	
	public Block() {
		state = BlockType.EMPTY;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
