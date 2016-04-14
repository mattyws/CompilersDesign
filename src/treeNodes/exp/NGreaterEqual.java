package treeNodes.exp;

import treeNodes.NExp;

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
