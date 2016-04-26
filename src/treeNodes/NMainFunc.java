package treeNodes;

import java.util.List;

import treeNodes.exp.NIdentifier;
import visitors.Table;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NMainFunc extends Node{
	
	public NIdentifier name;
	public List<NStatement> body;
	public Table tab;
	public NMainFunc(NIdentifier name, List<NStatement> body) {
		this.name = name;
		this.body = body;
	}
	public NIdentifier getName() {
		return name;
	}
	public void setName(NIdentifier name) {
		this.name = name;
	}
	public List<NStatement> getBody() {
		return body;
	}
	public void setBody(List<NStatement> body) {
		this.body = body;
	}
	public Table getTab() {
		return tab;
	}
	public void setTab(Table tab) {
		this.tab = tab;
	}
	
	

}
