package treeNodes.exp;

import treeNodes.NExp;

public class NAnd extends NExp{

	public NAnd(NExp l, NExp r) {
		super(l, r);
	}
	
	public NAnd(NExp r){
		super(r);
	}
	
	public NAnd(){
		super();
	}

}
