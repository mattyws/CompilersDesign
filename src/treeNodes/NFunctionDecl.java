package treeNodes;

import java.util.List;

import treeNodes.exp.NIdentifier;
import visitors.Table;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NFunctionDecl extends Node {
	
	public NType type;
	public NIdentifier name;
	public List<NParameter> parameters;
	public List<NStatement> body;
	public Table tab;
	
	public NFunctionDecl(NType type, NIdentifier name, List<NParameter> parameters, List<NStatement> body) {
		this.type=type;
		this.name=name;
		this.parameters=parameters;
		this.body=body;
	}

	public NType getType() {
		return type;
	}

	public void setType(NType type) {
		this.type = type;
	}

	public NIdentifier getName() {
		return name;
	}

	public void setName(NIdentifier name) {
		this.name = name;
	}

	public List<NParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<NParameter> parameters) {
		this.parameters = parameters;
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
