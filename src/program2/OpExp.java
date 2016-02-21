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
public class OpExp extends Exp {

    private Exp left;
    private Exp right;
    private Operations op;

    public OpExp(Exp left, Operations op, Exp right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public IntAndTable interp(Table t) {
        // Create the IntAndTable and get the result from left and right interp
        IntAndTable iTable = new IntAndTable();
        IntAndTable leftTable = left.interp(t);
        IntAndTable rightTable = right.interp(t);
        // makes the operation and get the result
        Integer result = doOperation(leftTable.getResult(), rightTable.getResult());
        iTable.setTable(t);
        iTable.setResult(result);
        // return the result and the table
        return iTable;
    }

    @Override
    public String toString() {
        return left.toString() + getOperationSymbol() + right.toString();
    }

    /**
     * Get the operation and return the mathematical symbol of this operation
     *
     * @return the operation symbol string
     */
    private String getOperationSymbol() {
        switch (op) {
            case ADD:
                return " + ";
            case SUB:
                return " - ";
            case MUL:
                return " * ";
            default :
                return " ";
        }        
    }

    /**
     * Do the operation and return the result
     * @param leftResult the left expression result
     * @param rightResult the right expression result
     * @return the result of the operation
     */
    private Integer doOperation(Integer leftResult, Integer rightResult) {
        if(leftResult == null || rightResult == null){
            return null;
        }
        Integer result = null;
        switch(op){
            case ADD :
                result = leftResult + rightResult;
                break;
            case SUB:
                result = leftResult - rightResult;
                break;
            case MUL:
                result = leftResult * rightResult;
        }
        return result;
    }

}
