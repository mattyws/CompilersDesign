/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program2;

/**
 *
 * @author mattyws
 */
public class AssignStm extends Stm {

	private String id;
	private Exp exp;

	public AssignStm(String id, Exp exp) {
		this.id = id;
		this.exp = exp;
	}

	@Override
	public Table interp(Table t) {
		IntAndTable iTable = exp.interp(t);
		t = iTable.getTable();
		if (t.containsKey(id)) {
			t.update(id, iTable.getResult());
		} else {
			t.add(id, iTable.getResult());
		}
		return t;
	}

	@Override
	public String toString() {
		return id + " := " + exp.toString();
	}

}
