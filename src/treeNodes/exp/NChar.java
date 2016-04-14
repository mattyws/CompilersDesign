package treeNodes.exp;

import treeNodes.NExp;

public class NChar extends NExp{
	
	public char value;
	
	public NChar(char value) {
		super(null,null);
		this.value=value;
	}

}
