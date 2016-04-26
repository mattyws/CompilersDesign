package visitors;

import java.util.LinkedList;
import java.util.List;

import treeNodes.NParameter;
import treeNodes.NType;
import treeNodes.exp.NIdentifier;
import treeNodes.exp.NString;
import treeNodes.types.NArrayBooleanType;
import treeNodes.types.NArrayCharType;
import treeNodes.types.NArrayFloatType;
import treeNodes.types.NArrayIntType;
import treeNodes.types.NArrayStringType;
import treeNodes.types.NBooleanType;
import treeNodes.types.NCharType;
import treeNodes.types.NFloatType;
import treeNodes.types.NIntType;
import treeNodes.types.NStringType;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class Binding {

	NIdentifier id;
	IdType usage;
	NType type;
	List<NParameter> params; // parameter types

	public Binding(NIdentifier i, IdType u, NType t) {
		id = i;
		usage = u;
		type = t;
		params = null;
	}

	public Binding(NIdentifier i, IdType u, NType t, List<NParameter> params) {
		id = i;
		usage = u;
		type = t;
		this.params = params;
	}

	@Override
	public String toString() {
		String value = "";
		value += (usage == IdType.METHOD ? "Method" : "Variable") + " ";
		value += "type : ";
		if(type instanceof NArrayBooleanType){
			value += "boolean[]";
		} else if(type instanceof NArrayCharType){
			value += "char[]";
		} else if(type instanceof NArrayFloatType){
			value += "float[]";
		} else if(type instanceof NArrayIntType){
			value += "int[]";
		} else if(type instanceof NArrayStringType){
			value += "string[]";
		} else if(type instanceof NBooleanType){
			value += "boolean";
		} else if(type instanceof NCharType){
			value += "char";
		} else if(type instanceof NFloatType){
			value += "float";
		} else if(type instanceof NIntType){
			value += "int";
		} else if(type instanceof NStringType){
			value += "string";
		} else {
			value += "void";
		}
		return value;
	}

}
