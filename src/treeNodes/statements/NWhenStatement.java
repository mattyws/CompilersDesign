package treeNodes.statements;

import treeNodes.NExp;
import treeNodes.NStatement;
import treeNodes.exp.NIdentifier;

public class NWhenStatement extends NStatement{
	
	public NIdentifier id;
	public NExp value;
	public NStatement body;
	
	public NWhenStatement(NIdentifier id, NExp cond, NStatement body){
		super();
		this.id=id;
		this.value=cond;
		this.body=body;
	}
	

}
