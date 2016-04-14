package bergCompiler;

/**
 * A Visitor can perform operations on a Syntax Tree, such as
 *    Build symbol tables
 *    Type check
 *    Optimize
 *    Code Gen...
 * 
 * @author (sdb) 
 * @version (Mar 2016)
 */
public interface Visitor
{
    void visit (Program n);
    void visit (MainClass n);
    void visit(ClassDeclList<ClassDecl> n);
    void visit (ClassDecl n);
    void visit (ClassDeclSimple n);
    void visit (ClassDeclExtends n);
    
    void visit (MethodDecl n);
    
    String visit (Exp n);
    String visit (And n);
    String visit (LessThan n);
    String visit (Plus n);
    String visit (Times n);
    
    String visit (IdentifierExp n);
    String visit (Product n);
    String visit (Quotient n);
    String visit(Mod n);
    String visit (Assign n);

}

