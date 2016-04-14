package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Array.length
 * left is the array expression 
 * right is null
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class ArrayLength extends Exp
{        
    ArrayLength (Exp size)
    {  super (size,null);    }
}
