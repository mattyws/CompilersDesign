package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Assignment Statement for arrays
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class ArrayAssign extends Stmt
{   Identifier id; 
    Exp value, index;
    
    public ArrayAssign (Identifier i, Exp ndx, Exp v)
    {  super();
       id = i;
       index = ndx;
       value = v;
    }
}
