package treeNodes.types;

import treeNodes.NType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NArrayFloatType extends NType{

	@Override
	public String toString() {
		return "float[]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(! (obj instanceof NArrayFloatType))
			return false;
		return true;
	}

}
