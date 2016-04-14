package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * Extended by 'simple' and 'extends' classDecl classes
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public abstract class ClassDecl
{   Identifier className;
    VarDeclList fields;
    MethodDeclList methods;
    
    ClassDecl (Identifier cn, VarDeclList fs, MethodDeclList ms)
    {   className = cn;
        fields = fs;
        methods = ms;
    }
}
