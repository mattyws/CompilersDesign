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
class NumExp extends Exp{
    
    private Integer num;

    public NumExp(int i) {
        this.num = i;
    }

    @Override
    public IntAndTable interp(Table t) {
        IntAndTable iTable = new IntAndTable();
        iTable.setResult(num);
        iTable.setTable(t);
        return iTable;
    }

    @Override
    public String toString() {
        return num.toString();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    
    
    
}
