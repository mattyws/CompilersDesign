package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Simple variable
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class IdentifierExp  extends Exp
{   String varName;    // Not an Identifier ?

    IdentifierExp ( String name)
    {  super (null, null );  
       varName = name;
    }
}
