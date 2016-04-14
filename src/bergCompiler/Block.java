package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Compound Statement
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Block extends Stmt
{    
    StmtList stmts;

    
    public Block ( StmtList s)
    {  super();  
       stmts = s;
    }
}
