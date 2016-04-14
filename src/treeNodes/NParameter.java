package treeNodes;

import treeNodes.exp.NIdentifier;

public class NParameter extends Node{
	
	public NType type;
	public NIdentifier name;
	
	public NParameter(NType type, NIdentifier name) {
		this.type=type;
		this.name=name;
	}

}
