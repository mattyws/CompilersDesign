package visitors;

import java.util.HashMap;
import java.util.Map;

import treeNodes.exp.NIdentifier;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class Table {

	public Map<String, Binding> symTable;

	public Table()
	   {    symTable = new HashMap <String, Binding> (); }

	public Table put(NIdentifier i, Binding b) {
		if (!symTable.containsKey(i.name)) {
			symTable.put(i.name, b);
			return this;
		}
		System.err.println("Identifier " + i + " already defined in this context");
		return null;
	}
	
	public boolean containsKey(String s){
		return symTable.containsKey(s);
	}
	
	public Binding get(String s) {
		return symTable.get(s);
	}

	@Override
	public String toString() {
		String value = "";
		for(String s : symTable.keySet()){
			value += s + " : " + symTable.get(s) + "\n";
		}
		return value;
	}
	
	

}
