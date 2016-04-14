package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * If Statement with Else
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class If extends Stmt
{    
    Exp cond;
    Stmt truePart, falsePart;
    
    public If ( Exp c, Stmt tp, Stmt fp)
    {  super();  
       cond = c;
       truePart = tp;
       falsePart = fp;
    }
}
