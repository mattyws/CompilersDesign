package treeNodes.types;

import treeNodes.NType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NArrayStringType extends NType{

	@Override
	public String toString() {
		return "string[]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(! (obj instanceof NArrayStringType))
			return false;
		return true;
	}

}
