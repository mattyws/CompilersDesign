package treeNodes.types;

import treeNodes.NType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NArrayBooleanType extends NType {

	@Override
	public String toString() {
		return "boolean[]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(! (obj instanceof NArrayBooleanType))
			return false;
		return true;
	}

}
