package treeNodes.exp;

import treeNodes.NExp;

public class NBoolean extends NExp{
	
	public boolean value;
	
	public NBoolean(boolean value) {
		super(null,null);
		this.value=value;
	}

}
