package treeNodes.exp;

import treeNodes.NExp;

public class NKeyValue extends NExp{
	
	NExp key;
	NExp value;
	
	public NKeyValue(NExp key, NExp value) {
		super(null,null);
		this.key=key;
		this.value=value;
	}

	public NExp getKey() {
		return key;
	}

	public void setKey(NExp key) {
		this.key = key;
	}

	public NExp getValue() {
		return value;
	}

	public void setValue(NExp value) {
		this.value = value;
	}
	
	

}
