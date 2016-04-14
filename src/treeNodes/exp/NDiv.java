package treeNodes.exp;

import treeNodes.NExp;

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
