package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Logical Not
 * operand is right, left is null
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Not  extends Exp
{   

    Not (Exp r)
    {  super (null, r );    }
}
