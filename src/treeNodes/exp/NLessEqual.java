package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NLessEqual extends NExp {

	public NLessEqual(NExp l, NExp r) {
		super(l, r);
		// TODO Auto-generated constructor stub
	}
	
	public NLessEqual(NExp r){
		super(r);
	}
	
	public NLessEqual(){
		super();
	}

}
