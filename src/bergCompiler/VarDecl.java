package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * Variable declaration
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class VarDecl

{   Type type;
    Identifier var;
    
    VarDecl (Type t, Identifier v)
    {   type = t;
        var = v;
    }
    
}
