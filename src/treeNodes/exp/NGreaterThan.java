package treeNodes.exp;

import treeNodes.NExp;

public class NGreaterThan extends NExp {

	public NGreaterThan(NExp l, NExp r) {
		super(l, r);
	}
	
	public NGreaterThan(NExp r){
		super(r);
	}
	
	public NGreaterThan(){
		super();
	}

}
