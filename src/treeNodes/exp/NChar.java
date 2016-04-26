package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NChar extends NExp{
	
	public char value;
	
	public NChar(char value) {
		super(null,null);
		this.value=value;
	}

}
