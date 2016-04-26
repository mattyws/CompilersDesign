package treeNodes.statements;

import treeNodes.NStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NElseStatement extends NStatement{
	
	public NStatement stm;

	public NElseStatement(NStatement stm) {
		super();
		this.stm = stm;
	}
	
}
