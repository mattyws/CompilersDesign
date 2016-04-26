package treeNodes.statements;

import treeNodes.NExp;
import treeNodes.NStatement;

public class NReturnStatement extends NStatement {

	public NExp returnExp;
	
	public NReturnStatement(NExp returnExp) {
		this.returnExp=returnExp;
	}
	
}
