package dlvManagement;

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

public class DLVManager {

	private static DLVManager instance;
	private DesktopService service; 
	private Handler handler;
	private InputProgram program;
	public static final SymbolicConstant vertical = new SymbolicConstant("vertical");
	public static final SymbolicConstant horizontal = new SymbolicConstant("horizontal");
	
	public static DLVManager getInstance() {
		if (instance == null)
			instance = new DLVManager();
		return instance;
	}
	
	private DLVManager() {
		service = new DLV2DesktopService("src/lib/dlv2.exe");
		handler = new DesktopHandler(service);
		program = new ASPInputProgram();
		program.addFilesPath("src/dlvManagement/encoding");
		handler.addProgram(program);
		//callSynchDlv();
		//queste due istruzioni servono ad aggiungere opzioni all'esecuzione di dlv (in questo caso vengono prodotti tutti gli answer set)
		//option descriptor è una classe di embasp
		//OptionDescriptor option = new OptionDescriptor("-n 0");
		//handler.addOption(option);
	}
	
	public void callSynchDlv() {
		Output o =  handler.startSync();
		AnswerSets as = (AnswerSets) o;
		for ( AnswerSet a : as.getAnswersets() ) {
			System.out.println(a.toString());
		}
	}
	
	
	public InputProgram getProgram() {
		return program;
	}
	
}
