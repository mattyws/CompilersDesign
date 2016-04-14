package treeNodes;

import treeNodes.exp.NIdentifier;
import treeNodes.statements.NAssign;

public class NVariable extends Node{

	public NType type;
	public NIdentifier id;
	public NAssign assign;
	
	public NVariable(NType type, NIdentifier id, NAssign assign) {
		this.id=id;
		this.type=type;
		this.assign = assign;
	}
}
