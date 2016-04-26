package treeNodes;

import treeNodes.exp.NIdentifier;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NParameter extends Node{
	
	public NType type;
	public NIdentifier name;
	
	public NParameter(NType type, NIdentifier name) {
		this.type=type;
		this.name=name;
	}

}
