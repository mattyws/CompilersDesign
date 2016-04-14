package treeNodes.exp;

import treeNodes.NExp;

public class NOr extends NExp{

	public NOr(NExp l, NExp r) {
		super(l, r);
	}
	
	public NOr(NExp r){
		super(r);
	}
	
	public NOr(){
		super();
	}

}
