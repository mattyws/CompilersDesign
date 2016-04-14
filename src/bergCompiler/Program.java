package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Program
{   MainClass mainClass;
    ClassDeclList classDecls;
    
    Program (MainClass m, ClassDeclList cl)
    {   mainClass = m;
        classDecls = cl;
    }
}
