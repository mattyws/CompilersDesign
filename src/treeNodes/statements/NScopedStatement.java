package treeNodes.statements;

import java.util.List;

import treeNodes.NStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NScopedStatement extends NStatement {
	
	public List<NStatement> statements;

	public NScopedStatement(List<NStatement> statements) {
		super();
		this.statements = statements;
	}
	
}
