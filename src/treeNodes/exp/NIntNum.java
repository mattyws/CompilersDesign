package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NIntNum extends NExp{

	public int value;
	
	public NIntNum(int value){
		super(null, null);
		this.value=value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
