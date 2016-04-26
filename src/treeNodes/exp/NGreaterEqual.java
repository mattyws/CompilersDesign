package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NGreaterEqual extends NExp {

	public NGreaterEqual(NExp l, NExp r) {
		super(l, r);
	}
	
	public NGreaterEqual(NExp r){
		super(r);
	}
	
	public NGreaterEqual(){
		super();
	}

}
