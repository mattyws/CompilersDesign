package treeNodes.exp;

import treeNodes.NExp;

public class NMult extends NExp {

	public NMult(NExp l, NExp r) {
		super(l, r);
	}
	
	public NMult(NExp r){
		super(r);
	}
	
	public NMult(){
		super();
	}

}
