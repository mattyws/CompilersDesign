package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NAnd extends NExp{

	public NAnd(NExp l, NExp r) {
		super(l, r);
	}
	
	public NAnd(NExp r){
		super(r);
	}
	
	public NAnd(){
		super();
	}

}
