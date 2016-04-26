package treeNodes.statements;

import treeNodes.NExp;
import treeNodes.NStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NIfStatement extends NStatement {

	public NExp condition;
	public NStatement stm;
	public NElseStatement elsePart;
	
	public NIfStatement(NExp condition, NStatement body, NElseStatement elsePart) {
		this.condition = condition;
		this.stm = body;
		this.elsePart = elsePart;
	}
}
