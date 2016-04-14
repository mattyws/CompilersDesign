package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * new int [size]
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class NewArray  extends Exp
{   Exp size;

    NewArray (Exp s)
    {  super (null, null );
       size = s;
    }
}
