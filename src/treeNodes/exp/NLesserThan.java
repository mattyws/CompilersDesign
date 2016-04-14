package treeNodes.exp;

import treeNodes.NExp;

public class NLesserThan extends NExp {

	public NLesserThan(NExp l, NExp r) {
		super(l, r);
	}
	
	public NLesserThan(NExp r){
		super(r);
	}
	
	public NLesserThan(){
		super();
	}

}
