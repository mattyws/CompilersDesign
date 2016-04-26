package treeNodes;

import visitors.Table;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public abstract class NStatement extends Node{
	
	public Table tab;

	public Table getTab() {
		return tab;
	}

	public void setTab(Table tab) {
		this.tab = tab;
	}
	
}
