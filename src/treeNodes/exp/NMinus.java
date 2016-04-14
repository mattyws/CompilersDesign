package treeNodes.exp;

import treeNodes.NExp;

public class NMinus extends NExp {

	public NMinus(NExp l, NExp r) {
		super(l, r);
	}
	
	public NMinus(NExp r){
		super(r);
	}
	
	public NMinus(){
		super();
	}

}
