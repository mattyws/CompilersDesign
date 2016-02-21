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
public class PrintStm extends Stm{
    
    private ExpList list;

    public PrintStm(ExpList list) {
        this.list = list;
    }
        
    @Override
    public Table interp(Table t) {
        return list.interp(t);
    }

    @Override
    public String toString() {
        return "print("+ list.toString() + ")";
    }
    
}
