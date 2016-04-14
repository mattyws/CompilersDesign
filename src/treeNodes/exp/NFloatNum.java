package treeNodes.exp;

import treeNodes.NExp;

public class NFloatNum extends NExp{
	
	public float value;
	
	public NFloatNum(float value) {
		super(null, null);
		this.value=value;
	}

}
