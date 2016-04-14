package treeNodes;

import java.util.List;

import treeNodes.exp.NIdentifier;

public class NMainFunc extends Node{
	
	public NIdentifier name;
	public List<NStatement> body;
	public NMainFunc(NIdentifier name, List<NStatement> body) {
		this.name = name;
		this.body = body;
	}
	
	

}
