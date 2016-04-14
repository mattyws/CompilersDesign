package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * While Statement
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class While extends Stmt
{    
    Exp cond;
    Stmt body;
    
    public While ( Exp c, Stmt b)
    {  super();  
       cond = c;
       body = b;
    }
}
