package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NDiv extends NExp {

	public NDiv(NExp l, NExp r) {
		super(l, r);
	}
	
	public NDiv(NExp r){
		super(r);
	}
	
	public NDiv(){
		super();
	}

}
