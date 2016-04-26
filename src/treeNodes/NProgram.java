package treeNodes;

import java.util.List;

import visitors.Table;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NProgram extends Node{
	
	public List<NFunctionDecl> functionDeclList;
	public NMainFunc main;
	public Table tab;
	
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

	public Table getTab() {
		return tab;
	}

	public void setTab(Table tab) {
		this.tab = tab;
	}
	
	
	
}
