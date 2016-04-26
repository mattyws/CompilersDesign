package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NString extends NExp{
	
	public String value;
	
	public NString(String value) {
		super(null,null);
		this.value=value;
	}

}
