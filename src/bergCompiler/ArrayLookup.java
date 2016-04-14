package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Subscripted array.
 * left is the array expression 
 * right is the subscript
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class ArrayLookup extends Exp
{        
    ArrayLookup (Exp l, Exp r)
    {  super (l,r);    }
}
