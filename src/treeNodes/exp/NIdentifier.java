package treeNodes.exp;

import treeNodes.NExp;

public class NIdentifier extends NExp {

	public String name = "";

	public NIdentifier(String value) {
		this.name = value;
	}
}
