package bergCompiler;

/**
 * A Visitor which builds symbol tables for a syntax tree
 * Semantic phase, part 1
 * 
 * @author (sdb) 
 * @version (Mar 2016)
 */
public class BuildST implements Visitor
{
   SymbolTable symTab, symTabMethod, symTabClass, symTabProg;
   
   public BuildST ()
   { }
   
   public void visit (Program n)
   {    symTab = symTabProg =  new SymbolTable();
        n.mainClass.accept (this);
        n.classDecls.accept (this);
    }
    
    public void visit (MainClass n)
    {   symTabProg.put (n.className, 
            new Binding (n.className, IdType.CLASS));
        symTabProg.put (n.args, new Binding (n.args, IdType.VARIABLE);
        n.symTab = new SymbolTable();
        n.className.accept(this);
        n.args.accept (this);
        n.stmt.accept (this);   //   ??
    }
    
    public void visit (ClassDecl n)
    {   }
    
    public void visit (ClassDeclSimple n)
    {   symTabProg.put (n.className, new Binding (n.className,
                    IdType.CLASS);
        n.className.accept (this);
        symTab = symTabClass = n.symtab = new SymbolTable();
        n.fields.accept (this);  // Enter fields into the symbol table
        n.methods.accept (this);
    }
    
    // Continue in this manner for the following classes:
    // ClassDeclExtends, VarDecl, MethodDecl, Formal, 
    
    
        
    }
        
    
}
