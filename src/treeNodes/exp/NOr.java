package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NOr extends NExp{

	public NOr(NExp l, NExp r) {
		super(l, r);
	}
	
	public NOr(NExp r){
		super(r);
	}
	
	public NOr(){
		super();
	}

}
