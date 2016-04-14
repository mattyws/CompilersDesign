package treeNodes.statements;

import treeNodes.NStatement;

public class NElseStatement extends NStatement{
	
	public NStatement stm;

	public NElseStatement(NStatement stm) {
		super();
		this.stm = stm;
	}
	
}
