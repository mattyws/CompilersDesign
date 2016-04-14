package treeNodes.exp;

import treeNodes.NExp;

public class NMod extends NExp {

	public NMod(NExp l, NExp r) {
		super(l, r);
	}
	
	public NMod(NExp r){
		super(r);
	}
	
	public NMod(){
		super();
	}

}
