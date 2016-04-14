package bergCompiler;

/**
 * MiniJava
 * Semantic Analysis
 * This class will be used to implement
 * Type checking.  (Start with the class used
 * to build symbol tables)
 * 
 * @author (sdb) 
 * @version (Apr 2016)
 */
public class SemanticTypeCheck implements Visitor
{   private SymbolTable symTab, symTabProg, symTabMethod,
            symTabClass;

    public SymbolTable getSymbolTable()
    {   return symTabProg; }
    
    public SemanticTypeCheck ()
    {     }
    
    public void visit (Program n)
    {   symTabProg = n.symTab = new SymbolTable();      // symbol table for class names
        n.mainClass.accept (this);
        n.classDecls.accept (this);
    }
    
    public void visit (MainClass n) 
    {   symTabProg.put (n.className, new Binding (n.className, IdType.CLASS));
        symTabProg.put (n.args, new Binding (n.args, IdType.VARIABLE, null));
        n.symTab = new SymbolTable();
        n.className.accept(this);
        n.args.accept(this);
        n.stmt.accept(this);
    }
    
    public void visit (ClassDecl n)
    {   }
    
    public void visit (ClassDeclSimple n) 
    {   symTabProg.put (n.className, new Binding (n.className, IdType.CLASS));
        n.className.accept(this);
        symTab = symTabClass = n.symTab = new SymbolTable();
        n.fields.accept (this);
        n.methods.accept (this);
 System.out.println ("Symbol table of the class: " + symTab);
    }
    
    public void visit (ClassDeclExtends n) 
    {   symTab.put (n.className, new Binding (n.className, IdType.CLASS));
        n.className.accept (this);
        n.superClass.accept (this);
        symTab = symTabClass = n.symTab = new SymbolTable();
        n.fields.accept (this);
        n.methods.accept (this);
    }
    
    public void visit (VarDecl n) 
    {   symTab.put (n.var, new Binding (n.var, IdType.VARIABLE, n.type));
        n.var.accept (this);
    }
    
    // This field used by MethodDecl and Formal
    private Binding methodBinding;
    
    public void visit (MethodDecl n) 
    {   methodBinding = new Binding (n.methodName, IdType.METHOD, n.returnType);
        symTab = n.symTab = symTabMethod = new SymbolTable();
        n.parms.accept (this);
        symTabClass.put (n.methodName, methodBinding);
        n.methodName.accept (this);
        n.locals.accept (this);
        n.methodBody.accept (this);
        n.returnValue.accept (this);
    }
       
    public void visit (Formal n) 
    {   symTab.put (n.var, new Binding (n.var, IdType.VARIABLE, n.type));
        methodBinding.addParm (n.type);
        //n.type.accept (this);
        n.var.accept (this);
    }
    
    // List classes
    public void visit (ExpList n)
    {   }
    
    public void visit (MethodDeclList n)
    {   }
    
    public void visit (VarDeclList n)
    {   }
    
    public void visit (ClassDeclList n)
    {   }
        
    public void visit (FormalList n)
    {   }
    
    public void visit (StmtList n)
    {   }
    
    public void visit (TypeSuper n)
    {   }
    
    public void visit (IntArrayType n) 
    {   
    }
    
    public void visit (BooleanType n) 
    {
    }
    
    public void visit (IntegerType n) 
    {
    }
    
    public void visit (IdentifierType n) 
    {   
    }
    
    public void visit (Stmt s)
    {  }
    
    public void visit (Block n) 
    {   
    }
    
    public void visit (If n) 
    {
    }
    
    public void visit (While n) 
    {
    }
    
    public void visit (Print n) 
    {
    }
    
    public void visit (Assign n) 
    {
    }
    
    public void visit (ArrayAssign n) 
    {
    }
    
    public String visit (Exp n)
    {  return null;  }
    
    public String visit (And n) 
    {   return null;
    }
    
    public String visit (LessThan n) 
    {   return null;
    }
    
    public String visit (Plus n) 
    {   return null;
    }
    
    public String visit (Minus n) 
    {   return null;
    }
    
    public String visit (Times n) 
    {   return null;
    }
    
    public String visit (ArrayLookup n) 
    {   return null;
    }
    
    public String visit (ArrayLength n) 
    {   return null;
    }
    
    public String visit (Call n) 
    {   return null;
    }
    
    public String visit (IntegerLiteral n) 
    {   return null;
    }
    
    public String visit (True n) 
    {   return null;
    }
    
    public String visit (False n) 
    {   return null;
    }

    public String visit (IdentifierExp n) 
    {   return null;
    }
    
    public String visit (This n) 
    {   return null;
    }
    
    public String visit (NewArray n) 
    {   return null;
    }   
    
    public String visit (NewObject n) 
    {   return null;
    }
    
    public String visit (Not n) 
    {   return null;
    }
    
    public void visit (Identifier n) 
    {  
    }
    
    
}
