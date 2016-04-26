package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NLessThan extends NExp {

	public NLessThan(NExp l, NExp r) {
		super(l, r);
	}
	
	public NLessThan(NExp r){
		super(r);
	}
	
	public NLessThan(){
		super();
	}

}
