package dlvManagement;

import it.unical.mat.embasp.base.Callback;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import model.Game;
import view.GamePanel;

public class OutputManager implements Callback {

	int attempt = 1;
	GamePanel gp;
	
	public OutputManager(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void callback(Output output) {
		int maxMoves = Game.getInstance().getMaxMovesDlv();
		AnswerSets as = (AnswerSets) output;
//		for (AnswerSet a : as.getAnswersets())
//			System.out.println(a.toString());
		if (attempt <= maxMoves && as.getAnswersets().isEmpty()) 
			attempt++;
		else {
			DLVManager.getInstance().analyzeDlvOutput(as, gp);
			return;
		}
		InputProgram attemptsTried = DLVManager.getInstance().getAttemptsTried();
		attemptsTried.clearAll();
		attemptsTried.addProgram("maxMoves("+attempt+").");
		DLVManager.getInstance().getHandler().addProgram(attemptsTried);
		DLVManager.getInstance().getHandler().startAsync(this);
	}
	
	public int getAttempt() {
		return attempt;
	}
}
