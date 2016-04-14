package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Print Statement
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Print extends Stmt
{    
    Exp value;
    
    public Print ( Exp v)
    {  super();  
       value = v;
    }
}
