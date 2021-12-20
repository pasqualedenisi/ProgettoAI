package model;

import java.util.ArrayList;

import constants.BlockType;
import constants.Screens;
import dlvManagement.DLVManager;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import it.unical.mat.embasp.languages.asp.SymbolicConstant;

@Id("truck")
public class Truck implements Vehicle {

	private Boolean orientation;
	private ArrayList<Block> busyBlocks;
	
	//aggiungo id per programma logico
	@Param(0)
	private int vehicleNumber;
	@Param(1)
	private SymbolicConstant orientationDlv;
	
	public Truck() {
	}
	
	public Truck(int id, SymbolicConstant orientationParameter){
		vehicleNumber = id;
		orientationDlv = orientationParameter;
	}
	
	public Truck(ArrayList<Block> busyBlocks) {
		super();
		this.busyBlocks = busyBlocks;
		if (busyBlocks.size()>1) {
			if ( busyBlocks.get(0).getCoordinate().getRow() == busyBlocks.get(1).getCoordinate().getRow() ){
				orientation = false;
				orientationDlv = DLVManager.horizontal;
			}
			else {
				orientation = true;
				orientationDlv = DLVManager.vertical;
			}
		}
		for ( Block b : busyBlocks )
			//b.setOccupier(this);
			b.occupate(this);
		//inizializzazione id
		initVehicleNumber();
	}

	@Override
	public Boolean getOrientation() {
		return orientation;
	}

	public void setOrientation(Boolean orientation) {
		this.orientation = orientation;
		if ( orientation )
			setOrientationDlv(DLVManager.vertical);
		else setOrientationDlv(DLVManager.horizontal);
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
	
	@Override
	public void initVehicleNumber() {
		vehicleNumber = Game.getInstance().getVehicleNumber();	
	}
	
	@Override
	public int getVehicleNumber() {
		return vehicleNumber;
	}
	
	public void setVehicleNumber(int vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
	public SymbolicConstant getOrientationDlv() {
		return orientationDlv;
	}
	
	public void setOrientationDlv(SymbolicConstant orientationDlv) {
		this.orientationDlv = orientationDlv;
	}
}
