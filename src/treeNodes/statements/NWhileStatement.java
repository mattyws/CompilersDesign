package treeNodes.statements;

import treeNodes.NExp;
import treeNodes.NStatement;

public class NWhileStatement extends NStatement {
	
	public NExp condition;
	public NStatement body;
	
	public NWhileStatement(NExp condition, NStatement body) {
		super();
		this.condition = condition;
		this.body = body;
	}

}
