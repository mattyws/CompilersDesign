package treeNodes.types;

import treeNodes.NType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NVoidType extends NType {

	@Override
	public String toString() {
		return "void";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(! (obj instanceof NVoidType))
			return false;
		return true;
	}
	

}
