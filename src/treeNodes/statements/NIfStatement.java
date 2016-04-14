package treeNodes.statements;

import treeNodes.NExp;
import treeNodes.NStatement;

public class NIfStatement extends NStatement {

	public NExp condition;
	public NStatement body;
	public NElseStatement elsePart;
	
	public NIfStatement(NExp condition, NStatement body, NElseStatement elsePart) {
		this.condition = condition;
		this.body = body;
		this.elsePart = elsePart;
	}
}
