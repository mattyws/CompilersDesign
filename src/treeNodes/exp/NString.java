package treeNodes.exp;

import treeNodes.NExp;

public class NString extends NExp{
	
	public String value;
	
	public NString(String value) {
		super(null,null);
		this.value=value;
	}

}
