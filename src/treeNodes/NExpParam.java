package treeNodes;

import java.util.List;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NExpParam extends Node{

	public List<NExp> expressionParam;

	public NExpParam(List<NExp> expressionParam) {
		super();
		this.expressionParam = expressionParam;
	}
	
	public List<NExp> getParams(){
		return expressionParam;
	}
	
}
