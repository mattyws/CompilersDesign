package bergCompiler;

/**
 * MiniJava 
 * Abstract Syntax Trees
 * 
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Identifier 
{   String name;

    Identifier (String n)
    {  name = n;    }
    
    String getName()
    {   return name;  }
}
