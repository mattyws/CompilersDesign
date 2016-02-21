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
public class LastExpList extends ExpList{
    
    private Exp unic;

    public LastExpList(Exp unic) {
        this.unic = unic;
    }        

    @Override
    public Table interp(Table t) {
        IntAndTable iTable = unic.interp(t);
        System.out.println(iTable.getResult());
        return iTable.getTable();
    }

    @Override
    public String toString() {
        return unic.toString();
    }
    
}
