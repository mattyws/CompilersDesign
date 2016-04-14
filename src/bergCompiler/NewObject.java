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
public class NewObject  extends Exp
{   Identifier className;

    NewObject (Identifier i)     // Appel says the parm is Identifier
    {  super (null, null );
       className =  i;
    }
}
