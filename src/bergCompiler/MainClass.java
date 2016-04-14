package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * Main class has a main method and that's all
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class MainClass
{   Identifier className;
    Identifier args;
    Stmt stmt;
    SymbolTable symTab;     // A MainClass needs its own symbol table
    
    MainClass (Identifier cn, Identifier a, Stmt s)
    {   className = cn;
        args = a;
        stmt = s;
    }

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
