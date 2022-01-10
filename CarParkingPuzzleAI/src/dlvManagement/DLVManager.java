package dlvManagement;

import java.util.List;

import constants.BlockType;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.languages.asp.SymbolicConstant;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.platforms.desktop.DesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import model.Car;
import model.Game;
import model.Vehicle;
import model.VehicleOperator;
import view.GamePanel;
import view.GameScreen;

public class DLVManager {

	private static DLVManager instance;
	private DesktopService service; 
	private Handler handler;
	private InputProgram program;
	public static final SymbolicConstant vertical = new SymbolicConstant(BlockType.vertical);
	public static final SymbolicConstant horizontal = new SymbolicConstant(BlockType.horizontal);
	private InputProgram attemptsTried;
	
	public static DLVManager getInstance() {
		if (instance == null)
			instance = new DLVManager();
		return instance;
	}
	
	private DLVManager() {
		service = new DLV2DesktopService("src/lib/dlv2.exe");
		handler = new DesktopHandler(service);
		program = new ASPInputProgram();
		attemptsTried = new ASPInputProgram();
		program.addFilesPath("src/dlvManagement/encoding3");
		handler.addProgram(program);
		//callSynchDlv();
		//queste due istruzioni servono ad aggiungere opzioni all'esecuzione di dlv (in questo caso vengono prodotti tutti gli answer set)
		//option descriptor è una classe di embasp
		//OptionDescriptor option = new OptionDescriptor("-n 0");
		//handler.addOption(option);
	}
	
//	public void callSynchDlv(Integer maxMoves) {
//		int attempt = 1;
//		while ( attempt <= maxMoves ) {
//			attemptsTried.clearAll();
//			attemptsTried.addProgram("maxMoves("+attempt+").");
//			handler.addProgram(attemptsTried);
//			Output o =  handler.startSync();
//			AnswerSets as = (AnswerSets) o;
//			if (as.getAnswersets().isEmpty()) {
//				System.out.println(attempt);
//				attempt++;
//				continue;
//			}
//			for ( AnswerSet a : as.getAnswersets() ) {
//				List<String> atoms = a.getAnswerSet();
//				for ( String atom : atoms ) {
//					if ( atom.substring(0, 5).equals("move(") ) {
//						String attributes = atom.substring(5, atom.length()-1);
//						System.out.println(attributes);
//						String[] attributeList = attributes.split(",");
//						Integer vehicleId = Integer.parseInt(attributeList[0]);
//						Integer oldBlockRow = Integer.parseInt(attributeList[4]);
//						Integer oldBlockColumn = Integer.parseInt(attributeList[5]);
//						String orientation = attributeList[3];
//						int orientationBinary = 0; //if horizontal
//						if (orientation.equals(BlockType.vertical))
//							orientationBinary = 1;
//						Integer nSteps = Integer.parseInt(attributeList[1]);
//						Integer direction = Integer.parseInt(attributeList[2]);
//						Vehicle moved = Game.getInstance().getVehicleByNumber(vehicleId);
//						Integer offset = 0;
//						/*
//						if ( moved instanceof Car )
//							offset = 1;
//						else
//							offset = 2;
//						 */
//						Integer newBlockRow = oldBlockRow + orientationBinary*nSteps*direction + offset*orientationBinary;
//						Integer newBlockColumn = oldBlockColumn + (1-orientationBinary)*nSteps*direction + offset*(1-orientationBinary);
//												 //3+(1-0)*2*1 + 1*(1-0) = 3+2+1
//						System.out.printf("oldcol: %d; orient: %d; nsteps: %d; direction: %d; offset: %d \n", 
//								oldBlockColumn, orientationBinary, nSteps, direction, offset);
//						System.out.printf("oldrow: %d; orient: %d; nsteps: %d; direction: %d; offset: %d \n", 
//								oldBlockRow, orientationBinary, nSteps, direction, offset);
//						VehicleOperator operator = new VehicleOperator();
//						System.out.println("Sposto il veicolo " + vehicleId + " nel blocco " + newBlockRow + "," + newBlockColumn);
//						operator.move(moved, Game.getInstance().getBlocks()[newBlockRow][newBlockColumn]);
//					}
//				}
//			}
//			break;
//		}
//	}
	
//	public void callSynchDlv() {
//		Output o =  handler.startSync();
//		AnswerSets as = (AnswerSets) o;
//		for ( AnswerSet a : as.getAnswersets() ) {
//			List<String> atoms = a.getAnswerSet();
//			for ( String atom : atoms ) {
//				if ( atom.substring(0, 5).equals("move(") ) {
//					System.out.println(atom);
//					String attributes = atom.substring(5, atom.length()-1);
//					String[] attributeList = attributes.split(",");
//					Integer vehicleId = Integer.parseInt(attributeList[0]);
//					Integer oldBlockRow = Integer.parseInt(attributeList[4]);
//					Integer oldBlockColumn = Integer.parseInt(attributeList[5]);
//					String orientation = attributeList[3];
//					int orientationBinary = 0; //if horizontal
//					if (orientation.equals(BlockType.vertical))
//						orientationBinary = 1;
//					Integer nSteps = Integer.parseInt(attributeList[1]);
//					String direction = attributeList[2];
//					int dirNumber = 1;
//					if ( direction.equals("backward") )
//						dirNumber = -1;
//					Vehicle moved = Game.getInstance().getVehicleByNumber(vehicleId);
//					Integer offset = 0;
//					if (dirNumber != -1) {
//						if ( moved instanceof Car )
//							offset = 1;
//						else
//							offset = 2;
//					}
//					Integer newBlockRow = oldBlockRow + orientationBinary*nSteps*dirNumber + offset*orientationBinary;
//					Integer newBlockColumn = oldBlockColumn + (1-orientationBinary)*nSteps*dirNumber + offset*(1-orientationBinary);
//											 //3+(1-0)*2*1 + 1*(1-0) = 3+2+1
////					System.out.printf("oldrow: %d; orient: %d; nsteps: %d; direction: %d; offset: %d \n", 
////							oldBlockRow, orientationBinary, nSteps, direction, offset);
////					System.out.printf("oldcol: %d; orient: %d; nsteps: %d; direction: %d; offset: %d \n", 
////							oldBlockColumn, orientationBinary, nSteps, direction, offset);
//					System.out.println("Sposto il veicolo " + vehicleId + " nel blocco " + newBlockRow + "," + newBlockColumn);
//					VehicleOperator operator = new VehicleOperator();
//					operator.move(moved, Game.getInstance().getBlocks()[newBlockRow][newBlockColumn]);
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//	}

	
//	public void callSynchDlv(Integer maxMoves, GameScreen screen) {
//		int attempt = 1;
//		while ( attempt <= maxMoves ) {
//			attemptsTried.clearAll();
//			attemptsTried.addProgram("maxMoves("+attempt+").");
//			handler.addProgram(attemptsTried);
//			Output o =  handler.startSync();
//			AnswerSets as = (AnswerSets) o;
//			if (as.getAnswersets().isEmpty()) {
//				//System.out.println(attempt);
//				attempt++;
//				continue;
//			}
//			for ( AnswerSet a : as.getAnswersets() ) {
//				List<String> atoms = a.getAnswerSet();
//				for ( String atom : atoms ) {
//					if ( atom.substring(0, 5).equals("move(") ) {
//						System.out.println(atom);
//						String attributes = atom.substring(5, atom.length()-1);
//						String[] attributeList = attributes.split(",");
//						Integer vehicleId = Integer.parseInt(attributeList[0]);
//						Integer oldBlockRow = Integer.parseInt(attributeList[4]);
//						Integer oldBlockColumn = Integer.parseInt(attributeList[5]);
//						String orientation = attributeList[3];
//						int orientationBinary = 0; //if horizontal
//						if (orientation.equals(BlockType.vertical))
//							orientationBinary = 1;
//						Integer nSteps = Integer.parseInt(attributeList[1]);
//						String direction = attributeList[2];
//						int dirNumber = 1;
//						if ( direction.equals("backward") )
//							dirNumber = -1;
//						Vehicle moved = Game.getInstance().getVehicleByNumber(vehicleId);
//						Integer offset = 0;
//						if (dirNumber != -1) {
//							if ( moved instanceof Car )
//								offset = 1;
//							else
//								offset = 2;
//						}
//						Integer newBlockRow = oldBlockRow + orientationBinary*nSteps*dirNumber + offset*orientationBinary;
//						Integer newBlockColumn = oldBlockColumn + (1-orientationBinary)*nSteps*dirNumber + offset*(1-orientationBinary);
//												 //3+(1-0)*2*1 + 1*(1-0) = 3+2+1
//	//					System.out.printf("oldrow: %d; orient: %d; nsteps: %d; direction: %d; offset: %d \n", 
//	//							oldBlockRow, orientationBinary, nSteps, direction, offset);
//	//					System.out.printf("oldcol: %d; orient: %d; nsteps: %d; direction: %d; offset: %d \n", 
//	//							oldBlockColumn, orientationBinary, nSteps, direction, offset);
//						System.out.println("Sposto il veicolo " + vehicleId + " nel blocco " + newBlockRow + "," + newBlockColumn);
//						VehicleOperator operator = new VehicleOperator();
//						operator.move(moved, Game.getInstance().getBlocks()[newBlockRow][newBlockColumn]);
//						//screen.repaint();
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//			break;
//		}
//	}


	public void analyzeDlvOutput(AnswerSets as, GamePanel gp) {
		for ( AnswerSet a : as.getAnswersets() ) {
			List<String> atoms = a.getAnswerSet();
			for ( String atom : atoms ) {
				if ( atom.substring(0, 5).equals("move(") ) {
					System.out.println(atom);
					String attributes = atom.substring(5, atom.length()-1);
					String[] attributeList = attributes.split(",");
					Integer vehicleId = Integer.parseInt(attributeList[0]);
					Integer oldBlockRow = Integer.parseInt(attributeList[4]);
					Integer oldBlockColumn = Integer.parseInt(attributeList[5]);
					String orientation = attributeList[3];
					int orientationBinary = 0; //if horizontal
					if (orientation.equals(BlockType.vertical))
						orientationBinary = 1;
					Integer nSteps = Integer.parseInt(attributeList[1]);
					String direction = attributeList[2];
					int dirNumber = 1;
					if ( direction.equals("backward") )
						dirNumber = -1;
					Vehicle moved = Game.getInstance().getVehicleByNumber(vehicleId);
					Integer offset = 0;
					if (dirNumber != -1) {
						if ( moved instanceof Car )
							offset = 1;
						else
							offset = 2;
					}
					Integer newBlockRow = oldBlockRow + orientationBinary*nSteps*dirNumber + offset*orientationBinary;
					Integer newBlockColumn = oldBlockColumn + (1-orientationBinary)*nSteps*dirNumber + offset*(1-orientationBinary);
											 //3+(1-0)*2*1 + 1*(1-0) = 3+2+1
//					System.out.printf("oldrow: %d; orient: %d; nsteps: %d; direction: %d; offset: %d \n", 
//							oldBlockRow, orientationBinary, nSteps, direction, offset);
//					System.out.printf("oldcol: %d; orient: %d; nsteps: %d; direction: %d; offset: %d \n", 
//							oldBlockColumn, orientationBinary, nSteps, direction, offset);
					System.out.println("Sposto il veicolo " + vehicleId + " nel blocco " + newBlockRow + "," + newBlockColumn);
					VehicleOperator operator = new VehicleOperator();
					operator.move(moved, Game.getInstance().getBlocks()[newBlockRow][newBlockColumn]);
					gp.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	public InputProgram getProgram() {
		return program;
	}
	
	public InputProgram getAttemptsTried() {
		return attemptsTried;
	}
	
	public Handler getHandler() {
		return handler;
	}
	
}
