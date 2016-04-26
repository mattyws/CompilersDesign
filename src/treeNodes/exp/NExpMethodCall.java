package treeNodes.exp;

import treeNodes.NExp;
import treeNodes.NExpParam;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NExpMethodCall extends NExp{
	
	public NIdentifier id;
	public NExpParam parameters;
	public NExpMethodCall(NIdentifier id, NExpParam parameters) {
		super(null,null);
		this.id = id;
		this.parameters = parameters;
	}
	
}
