package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * Type for object types (i.e. non-primitive types)
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class IdentifierType extends Type
{    
    String className;
    
    IdentifierType ( String cn)
    {  super();  
        className = cn;
    }
}
