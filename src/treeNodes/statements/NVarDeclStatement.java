package treeNodes.statements;

import java.util.List;

import treeNodes.NStatement;
import treeNodes.NVariable;

public class NVarDeclStatement extends NStatement {
	public List<NVariable> variables;

	public NVarDeclStatement(List<NVariable> variables) {
		this.variables = variables;
	}
	
}
