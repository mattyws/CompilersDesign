package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NFloatNum extends NExp{
	
	public float value;
	
	public NFloatNum(float value) {
		super(null, null);
		this.value=value;
	}

}
