package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NEqual extends NExp {

	public NEqual(NExp l, NExp r) {
		super(l, r);
	}
	
	public NEqual(NExp r){
		super(r);
	}
	
	public NEqual(){
		super();
	}

}
