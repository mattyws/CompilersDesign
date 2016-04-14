package bergCompiler;
import java.util.*;

/**
 * Lexical analysis for MiniJava
 * Produce a list of tokens
 * 
 * @author (sdb) 
 * @version (Jan 2010)
 */
public class LexerDFA
{   private Scanner scanner;
    private List <Token> tokens;
    private int state, newState;
    private int charType;
    private String tokString = "";
    private char ch;
    private static final int INITIAL=0, ID_KEY=1, NUMBER=2, RELOP=3;
    private static final int WHITE=0, ALPHA=1, NUMERIC=2, REL_OP=3, ARITHOP=4, PAREN=5, 
    THESIS=6, SEMI=7, COMMA=8, LBRACKET=9, RBRACKET=10, LBRACE=11, RBRACE=12, DOT=13, NOT=14,
    NULL=15;
    private static final int dfa[][] = 
        {       // state = INITIAL
            {   INITIAL, ID_KEY, NUMBER, RELOP, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL
                , INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL},
                // state = ID_KEY
            {   INITIAL, ID_KEY, ID_KEY, RELOP, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL
                , INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL},
                // state = NUMBER
            {   INITIAL, ID_KEY, NUMBER, RELOP, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL
                , INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL},
                // state = RELOP
            {   INITIAL, ID_KEY, NUMBER, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL 
                , INITIAL, INITIAL, INITIAL, INITIAL, INITIAL, INITIAL}
        };

    private static final int [] []actions =
       {    // state = INITIAL
           {    0, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 },  // state = INITIAL
           {    4, 1, 1, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6 },  // state = ID_KEY
           {    7, 8, 1, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },     // state = NUMBER
           {    10, 11, 11, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13}     // state = RELOP
        };
          
        public LexerDFA ()
    {  // this (new Scanner (System.in)); 
    }
    
    public LexerDFA (Scanner scanner)
    {   this.scanner = scanner;
        tokens = new LinkedList <Token> ();
        state = INITIAL;
        tokens = lex();
    }
        
    
    public static void main ()
    {   LexerDFA lexer = new LexerDFA();
        lexer.lex();
    }
    
    // return the next token, or null if there are no more tokens
    public Token getToken()
    {   if (tokens.isEmpty())
            return null;
        return tokens.remove(0);   
    }
    
    public  List<Token> lex ()
    {   System.out.println ();
        while (scanner.hasNextLine())
            {   String line = scanner.nextLine();
                for (int i=0; i<line.length(); i++)
                {   ch = line.charAt(i);
                    charType = type (ch);
                    action (actions[state] [charType]);
// System.out.println ( "state " + state);
// System.out.println ( "ch " + ch);
// System.out.println ( "charType " + charType);
// System.out.println ( "action # " + actions[state] [charType]);

                    state = dfa [state] [charType];
                }
                // end of line, put out the token.
               switch (state)
               {    case ID_KEY:    if (idKey (tokString))
                                        out (TokenType.KEY, tokString);
                                    else
                                        out (TokenType.ID, tokString);
                                    break;
                    case NUMBER:    out (TokenType.NUMBER, tokString);
                                    break;
                    case RELOP:     outRelop();
                                    break;
                }
               state = INITIAL;
            }
     return tokens;
    }
    
    private void action (int actionNumber)
    {   switch (actionNumber)
        {   case 0: break;
            case 1: appendCh();
                    break;
            case 2: appendOutKeyWord();
                    break;
            case 3: outToken();
                    break;
            case 4: outKeyId();
                    break;
            case 5: outKeyIDAppend();
                    break;
            case 6: outKeyIDSpecial();
                    break;
            case 7: outNumber();
                    break;
            case 8: outNumberAppend();
                    break;
            case 9: outNumberSpecial();
                    break;
            case 10: outRelOp();
                    break;
            case 11: outRelOpAppend();
                    break;
            case 12: appendOutRelOp();
                    break;
            case 13: outRelOpSpecial();
                    break;
                }
     }
            
    // action 1
    private void appendCh ()
    {   tokString += ch;  }
    
    // action 2 - append ch, if keyword, output keyword token
    private void appendOutKeyWord()
    {   appendCh();
        if (idKey (tokString))
            out (TokenType.KEY, tokString);
    }
    
    // action 3 - out token, token Type is taken from charType
    private void outToken ()
    {   switch (charType)
        {
        case RELOP: out (TokenType.RELOP, tokString);
                    break;
        case ARITHOP: appendCh();
                      out (TokenType.ARITHOP, tokString);
                    break;
        case PAREN:   out (TokenType.PAREN);
                    break;
        case THESIS:  out (TokenType.THESIS);
                    break;
        case SEMI:  out (TokenType.SEMI);
                    break;
        case COMMA: out (TokenType.COMMA);
                    break;
        case LBRACE: out (TokenType.LBRACE);
                    break;
        case RBRACE: out (TokenType.RBRACE);
                    break;
        case LBRACKET: out (TokenType.LBRACKET);
                    break;
        case RBRACKET: out (TokenType.RBRACKET);
                    break;
        case DOT: out (TokenType.DOT);
                    break;
        case NOT: out (TokenType.NOT);
                    break;
     }
    }
    
     // action 4
     private void outKeyId()
     {  if (idKey (tokString))
            out (TokenType.KEY, tokString);
        else
            out (TokenType.ID, tokString);
     }
    
     // action 5
     private void outKeyIDAppend()
     {  outKeyId();
        appendCh();
    }
    
    // action 6
    private void outKeyIDSpecial()
    {   outKeyId();
        outToken();
    }
    
    // action 7
    private void outNumber()
    {   out (TokenType.NUMBER, tokString);  }
    
    // action 8
    private void outNumberAppend()
    {   outNumber();
        appendCh();
    }
    
    // action 9
    private void outNumberSpecial()
    {   outNumber();
        outToken();
    }
    
    // action 10
    private void outRelOp()
    {   out (TokenType.RELOP, tokString);  }
    
    // action 11
    private void outRelOpAppend()
    {   outRelOp();
        appendCh();
    }
    
    // action 12
    private void appendOutRelOp()
    {   appendCh();
        outRelOp();
    }
    
    // action 13
    private void outRelOpSpecial()
    {   outRelOp();
        outToken();
    }
    
    private  void outRelop()
    {   if (tokString.equals ("="))
            out (TokenType.ASSIGN);
        else
            out (TokenType.RELOP, tokString);
        }
        
    private  void out (TokenType t, String value)
    {
        tokens.add (new Token (t, value));
        reset();
    }
    
    private  void out (TokenType t)
    {
        tokens.add (new Token (t));
        reset();
    }
    
    private void reset()
    {      tokString = "";
        newState = INITIAL;
//  System.out.println (tokens);
    }
    
    private  int type (char ch)
    {   if (ch==' ' || ch=='\t' || ch=='\n')
            return WHITE;
//         if (ch<='z' && ch >='a')
//             return ALPHA;
//         if (ch<='Z' && ch >='A')
//             return ALPHA;
        if (Character.isLetter(ch))
            return ALPHA;
        if (ch <='9' && ch >='0')
            return NUMERIC;
        if (ch=='+' || ch=='-' || ch=='*')
            return ARITHOP;
        if (ch=='<' || ch=='>' || ch=='=' || ch=='&')       // will need to be generalized for logical operators?
            return REL_OP;
        switch (ch)
        {   case ';':   return SEMI;
            case '(':   return PAREN;
            case ')':   return THESIS;
            case ',':   return COMMA;
            case '.':   return DOT;
            case '!':   return NOT;
            case '[':   return LBRACKET;
            case ']':   return RBRACKET;
            case '{':   return LBRACE;
            case '}':   return RBRACE;
        }
        return NULL;
    }
        
    private boolean idKey(String id)
    {   String [] keyWords = {"if", "else", "while", "for", "class", "public", "static", "void", "main",
                            "String", "extends", "return", "int", "boolean", "System.out.println", "length",
                            "true", "false", "this", "new"
                             };
        for (String key : keyWords)
            if (id.equals (key))
                return true;
        return false;
    }
    
}
