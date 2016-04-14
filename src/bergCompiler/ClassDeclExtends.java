package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * Class which is a subclass
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class ClassDeclExtends extends ClassDecl 

{   Identifier superClass;
    
    ClassDeclExtends (Identifier cn, Identifier sc, VarDeclList vl, MethodDeclList ms)
    {   super (cn, vl, ms);    
        superClass = sc;
    }
    
}
