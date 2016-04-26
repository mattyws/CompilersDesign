package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NPlus extends NExp {

	public NPlus(NExp l, NExp r) {
		super(l, r);
	}
	
	public NPlus(NExp r){
		super(r);
	}
	
	public NPlus(){
		super();
	}

}
