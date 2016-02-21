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
public class CompoundStm extends Stm {
    
    private Stm first, second;

    public CompoundStm(Stm first, Stm second) {
        this.first = first;
        this.second = second;
    }
        
    @Override
    public Table interp(Table t) {
        t = first.interp(t);
        return second.interp(t);
    }

    @Override
    public String toString() {
        return first.toString() + "; " + second.toString();
    }
    
}
