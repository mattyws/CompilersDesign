package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Assignment Statement
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Assign extends Stmt
{   Identifier id; 
    Exp value;
    
    public Assign (Identifier i, Exp v)
    {  super();
       id = i;
       value = v;
    }
}
