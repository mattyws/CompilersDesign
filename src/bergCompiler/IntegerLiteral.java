package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * int constant
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class IntegerLiteral  extends Exp
{   int value;

    IntegerLiteral (int i)
    {  super (null, null); 
       value = i;
    }
}
