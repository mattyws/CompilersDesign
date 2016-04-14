package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * Formal Parameter for Methods
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Formal

{   Type type;
    Identifier var;
    
    Formal (Type t, Identifier v)
    {   type = t;
        var = v;
    }
    
}
