package treeNodes.statements;

import treeNodes.NExp;
import treeNodes.NStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NWhileStatement extends NStatement {
	
	public NExp condition;
	public NStatement stm;
	
	public NWhileStatement(NExp condition, NStatement body) {
		super();
		this.condition = condition;
		this.stm = body;
	}

}
