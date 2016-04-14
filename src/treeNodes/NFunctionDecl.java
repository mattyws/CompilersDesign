package treeNodes;

import java.util.List;

import treeNodes.exp.NIdentifier;

public class NFunctionDecl extends Node {
	
	public NType type;
	public NIdentifier name;
	public List<NParameter> parameters;
	public List<NStatement> body;
	
	public NFunctionDecl(NType type, NIdentifier name, List<NParameter> parameters, List<NStatement> body) {
		this.type=type;
		this.name=name;
		this.parameters=parameters;
		this.body=body;
	}

}
