package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * Class which is not a subclass
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class ClassDeclSimple extends ClassDecl 

{   
    
    ClassDeclSimple (Identifier cn, VarDeclList vl, MethodDeclList ms)
    {   super (cn, vl, ms);    }
    
}
