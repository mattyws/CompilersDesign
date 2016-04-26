package treeNodes.statements;

import treeNodes.NExp;
import treeNodes.NStatement;
import treeNodes.exp.NIdentifier;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NAssign extends NStatement{
	
	public NIdentifier id;
	public NExp exp;
	
	public NAssign(NIdentifier id, NExp exp) {
		this.id = id;
		this.exp = exp;
	}	
	
}
