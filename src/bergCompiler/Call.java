package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Method call
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 *
 */
public class Call extends Exp
{   Exp returnValue;
    Identifier methodName;
    ExpList parms;
    
    Call (Exp rv, Identifier mn, ExpList ps)
    {  super (null, null); 
       returnValue = rv;
       methodName = mn;
       parms = ps;
    }
}
