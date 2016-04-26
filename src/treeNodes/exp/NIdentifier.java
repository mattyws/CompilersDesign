package treeNodes.exp;

import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */


public class NIdentifier extends NExp {

	public String name = "";

	public NIdentifier(String value) {
		this.name = value;
	}
}
