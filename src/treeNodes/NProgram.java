package treeNodes;

import java.util.List;

public class NProgram extends Node{
	
	public List<NFunctionDecl> functionDeclList;
	public NMainFunc main;
	
	public NProgram(List<NFunctionDecl> functionDeclList, NMainFunc main) {
		this.functionDeclList = functionDeclList;
		this.main = main;
	}
	
	public List<NFunctionDecl> getFunctionDeclList() {
		return functionDeclList;
	}
	public void setFunctionDeclList(List<NFunctionDecl> functionDeclList) {
		this.functionDeclList = functionDeclList;
	}
	public NMainFunc getMain() {
		return main;
	}
	public void setMain(NMainFunc main) {
		this.main = main;
	}
	
	
}
