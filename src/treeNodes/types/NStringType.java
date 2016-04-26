package treeNodes.types;

import treeNodes.NType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NStringType extends NType{

	@Override
	public String toString() {
		return "string";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(! (obj instanceof NStringType))
			return false;
		return true;
	}

}
