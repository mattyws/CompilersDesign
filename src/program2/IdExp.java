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
public class IdExp extends Exp {
    
    private String name;

    public IdExp(String name) {
        this.name = name;
    }        

    @Override
    public IntAndTable interp(Table t) {
        IntAndTable iTable = new IntAndTable();
        if(t.containsKey(name)){
            iTable.setResult(t.get(name));
        } else {
            iTable.setResult(null);
        }
        iTable.setTable(t);
        return iTable;
    }

    @Override
    public String toString() {
        return name;       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
