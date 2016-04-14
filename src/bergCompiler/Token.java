package bergCompiler;

/**
 * Token stores a TokenType and a value
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Token
{
    private TokenType t;
    private String value;
    
    public Token (TokenType t, String v)
    {   this.t = t;
        value = v;
    }
    
    public Token (TokenType t)
    {   this.t = t;
        value = null;
    }
    
    public String toString()
    {   return t + " " + value; }
    
    public TokenType getType()
    {   return t;  }
    
    public String getValue()
    {   return value;  }
    
}
