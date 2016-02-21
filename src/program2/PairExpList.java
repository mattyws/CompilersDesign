package program2;

/**
 *
 * @author mattyws
 */
public class PairExpList extends ExpList{
    
    private Exp first;
    private ExpList list;

    public PairExpList(Exp fist, ExpList list) {
        this.first = fist;
        this.list = list;
    }        

    @Override
    public Table interp(Table t) {
        IntAndTable iTable = first.interp(t);        
        System.out.print(iTable.getResult()+" ");
        // Execute the first interp and pass the updated table to the other statement
        return list.interp(iTable.getTable());
    }

    @Override
    public String toString() {
        return first.toString() + ", " + list.toString();
    }
    
}
