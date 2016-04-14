package treeNodes.exp;

import treeNodes.NExp;

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
