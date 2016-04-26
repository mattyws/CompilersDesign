package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NArrayCall extends NExp{
	
	public NIdentifier id;
	public NExp exp;
	
	public NArrayCall(NIdentifier id,NExp exp) {
		super(null,null);
		this.id = id;
		this.exp=exp;
	}

}
