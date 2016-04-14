package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * Method declaration
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class MethodDecl

{   Type returnType;
    Identifier methodName;
    FormalList parms;
    VarDeclList locals;
    StmtList methodBody;
    Exp returnValue;
    
    MethodDecl (Type t, Identifier mn, FormalList ps, VarDeclList ls, 
        StmtList body, Exp rv)
    {   returnType = t;
        methodName = mn;
        parms = ps;
        locals = ls;
        methodBody = body;
        returnValue = rv;
    }
    
}
