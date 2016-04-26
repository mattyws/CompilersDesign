package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NBoolean extends NExp{
	
	public boolean value;
	
	public NBoolean(boolean value) {
		super(null,null);
		this.value=value;
	}

}
