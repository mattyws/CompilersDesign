package treeNodes.statements;

import java.util.List;

import treeNodes.NExpParam;
import treeNodes.NParameter;
import treeNodes.NStatement;
import treeNodes.Node;
import treeNodes.exp.NIdentifier;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NMethodCall extends NStatement {
	public NIdentifier id;
	public NExpParam parameters;
	public NMethodCall(NIdentifier id, NExpParam parameters) {
		this.id = id;
		this.parameters = parameters;
	}
}
