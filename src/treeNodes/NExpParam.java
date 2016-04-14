package treeNodes;

import java.util.List;

public class NExpParam extends Node{

	public List<NExp> expressionParam;

	public NExpParam(List<NExp> expressionParam) {
		super();
		this.expressionParam = expressionParam;
	}
	
}
