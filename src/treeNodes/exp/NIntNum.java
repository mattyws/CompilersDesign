package treeNodes.exp;

import treeNodes.NExp;

public class NIntNum extends NExp{

	public int value;
	
	public NIntNum(int value){
		super(null, null);
		this.value=value;
	}
}
