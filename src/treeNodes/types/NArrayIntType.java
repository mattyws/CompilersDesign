package treeNodes.types;

import treeNodes.NType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NArrayIntType extends NType {

	@Override
	public String toString() {
		return "int[]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(! (obj instanceof NArrayIntType))
			return false;
		return true;
	}

}
