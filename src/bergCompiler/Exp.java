package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * Extended by all the Expression classes
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public abstract class Exp
{    
    Exp left, right;
    
    Exp (Exp l, Exp r)
    {  left = l;
       right = r;
    }
}
