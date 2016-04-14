package treeNodes.exp;

import treeNodes.NExp;

public class NArrayCall extends NExp{
	
	public NExp exp;
	
	public NArrayCall(NExp exp) {
		super(null,null);
		this.exp=exp;
	}

}
