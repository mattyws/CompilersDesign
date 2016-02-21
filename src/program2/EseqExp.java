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
public class EseqExp extends Exp{
    
    private Stm statement;
    private Exp expr;

    public EseqExp(Stm statement, Exp expr) {
        this.statement = statement;
        this.expr = expr;
    }        

    @Override
    public IntAndTable interp(Table t) {
        // Update the table interpreting the statement
        t = statement.interp(t);
        // return the expression interp with the new table
        return this.expr.interp(t);
    }

    @Override
    public String toString() {
        return "( "+ statement.toString() + ", " + expr.toString() + ")";
    }
    
}
