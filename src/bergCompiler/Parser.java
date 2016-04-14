package bergCompiler;
 

import java.util.*;

/**
 * LL(2) Parser for MiniJava.
 * 
 * The language of method body is LL(2), as far as I can tell.
 * 
 * @author (sdb) 
 * @version (Jan 2011)
 */
public class Parser
{   LexerDFA lexer = new LexerDFA();
    Token token, prev;
    TokenType tok;
    String value;
    boolean retracted = false;      // back tracked one token
    Token next;                     // next token after a backtrack
    
    public Parser ()
    {
    }
    
    public Parser (Scanner scanner)
    {   lexer = new LexerDFA (scanner);
    }
    
    public void parse()
    {   
    
    }
    
    public Program Program ()
    {   advance();     // get first token
        MainClass mainClass = MainClass();
        ClassDeclList <ClassDecl> otherClasses = new ClassDeclList <ClassDecl> ();
        while (is (TokenType.KEY, "class"))
            otherClasses.add (ClassDecl());
        if (token != null)    
            error();
        else
            System.out.println ("Success");
        return new Program (mainClass, otherClasses);
        }
    
    
    public MainClass MainClass()
    { 
               eat ("class");      
               Identifier className = new Identifier (value);
                eat (TokenType.ID);
                eat (TokenType.LBRACE);
                eat ("public");
                eat ("static");
                eat ("void");
                eat ("main");
                eat (TokenType.PAREN);
                eat ("String");
                eat (TokenType.LBRACKET);
                eat (TokenType.RBRACKET);
                Identifier args = new Identifier (value);
                eat (TokenType.ID);
                eat (TokenType.THESIS);
                eat (TokenType.LBRACE);
                Stmt stmt = Stmt();
                eat (TokenType.RBRACE);
                eat (TokenType.RBRACE);
                return new MainClass (className, args, stmt);
    }
    
    public ClassDecl ClassDecl()
    {   Identifier className = ClassDeclSpec();
        ClassDecl tmp = ClassDeclDef(className);
        return tmp;
    }
    
    public Identifier ClassDeclSpec()
    {   eat ("class");
        Identifier className = new Identifier (value);
        eat (TokenType.ID);
        return className;
    }
    
    public ClassDecl ClassDeclDef(Identifier className)
    {   MethodDeclList <MethodDecl> methods = new MethodDeclList <MethodDecl> ();
        VarDeclList <VarDecl> fields = new VarDeclList <VarDecl> ();
        if (tok==TokenType.LBRACE)
        {   eat (TokenType.LBRACE);
            while (tok==TokenType.ID || is(TokenType.KEY, "int") 
                        || is (TokenType.KEY, "boolean"))
                fields.add (VarDecl());
            
            while (is (TokenType.KEY, "public"))
                methods.add (MethodDecl());
            eat (TokenType.RBRACE);
            return new ClassDeclSimple (className, fields, methods);
        }
        else
            {   eat ("extends");
                Identifier superClass = new Identifier (value);
                eat (TokenType.ID);
                eat (TokenType.LBRACE);
                eat (TokenType.ID);
                eat (TokenType.LBRACE);
                while (tok==TokenType.ID)
                    fields.add (VarDecl());
                while (is (TokenType.KEY, "public"))
                    methods.add (MethodDecl());
                eat (TokenType.RBRACE);
                return new ClassDeclExtends (className, superClass, fields, methods);
            }
    }
    
    public VarDecl VarDecl()
    {   Type t = Type();
        Identifier varName = new Identifier (value);
        eat (TokenType.ID);
        eat (TokenType.SEMI);
        return new VarDecl (t, varName);
    }
    
    public MethodDecl MethodDecl()
    {   eat ("public");
        Type returnType = Type();
        Identifier methodName = new Identifier (value);
        eat (TokenType.ID);
        eat (TokenType.PAREN);
        FormalList <Formal> formals = formalList ();
        eat (TokenType.THESIS);
        eat (TokenType.LBRACE);
        boolean moreLocals = true;      
        // processing declarations of local variables in a method body
        VarDeclList <VarDecl> locals = new VarDeclList <VarDecl> ();
        while ((tok==TokenType.ID  || is(TokenType.KEY,"int")
                        || is (TokenType.KEY,"boolean"))&& moreLocals)
            {   eat ();  
                // Need to look ahead for an ASSIGN
                if (is (TokenType.RELOP, "=")) // ASSIGN indicates start of a Stmt
                    {   retract();
                        moreLocals = false;
                    }
                else
                    {   retract();
                        locals.add (VarDecl());
                    }
            }
        // processing the statements in the method body
        StmtList <Stmt> stmts = new StmtList <Stmt>();
        while (stmtStart())
            stmts.add (Stmt());
        eat ("return");
        Exp returnValue = Exp();
        eat (TokenType.SEMI);
        eat (TokenType.RBRACE);
        return new MethodDecl (returnType, methodName, formals, locals, stmts, returnValue);
    }
    
   private boolean stmtStart()
   {    switch (tok)
        {   case LBRACE:    return true;
            case ID:        return true;
            case KEY:       return value.equals("if") ||
                                    value.equals ("while") ||
                                    value.equals ("System.out.println");
            default:        return false;
        }
    }
        
    public FormalList <Formal> formalList()
    {   FormalList <Formal> formals = new FormalList <Formal> ();
        if (tok==TokenType.ID || is(TokenType.KEY,"int")
                              || is (TokenType.KEY,"boolean"))
            {   formals.add (new Formal (Type(), new Identifier(value)));
                eat (TokenType.ID);
                while (tok==TokenType.COMMA)
                    FormalRest(formals);
             }
        return formals;
    }
    
    public void FormalRest(FormalList <Formal> formals)
    {   eat (TokenType.COMMA);
        formals.add (new Formal (Type(), new Identifier (value)));
        eat (TokenType.ID);
    }
    
    public Type Type()
    {   if (is(TokenType.KEY, "int"))
            {   eat ("int");
                return IntType();
            }
        if (is(TokenType.KEY, "boolean"))
            {   eat ("boolean");
                return new BooleanType();
            }
        String className = value;
        eat (TokenType.ID);
        return new IdentifierType (className);
    }
    
    public Type IntType()
    {   if (tok==TokenType.LBRACKET)
            {   eat (TokenType.LBRACKET);
                eat (TokenType.RBRACKET);
                return new IntArrayType();
            }
        return new IntegerType();
    }
    
    public Stmt Stmt()
    {   switch (tok)
        {   case LBRACE: eat (TokenType.LBRACE);
                         StmtList <Stmt> stmts = new StmtList <Stmt> ();
                            while (tok!=TokenType.RBRACE)
                                   stmts.add (Stmt());
                            eat (TokenType.RBRACE);
                            return new Block (stmts);
                            
            case ID:     Identifier target = new Identifier (value);
                         eat (TokenType.ID);
                         return Assign(target);

            case KEY:
                if (value.equals ("if"))
                    {   eat("if");
                        eat (TokenType.PAREN);
                        Exp cond = Exp();
                        eat (TokenType.THESIS);
                        Stmt ifPart = Stmt();
                        eat ("else");
                        return new If (cond, ifPart, Stmt());
                    }
                else if (value.equals ("while"))
                    {   eat ("while");
                        eat (TokenType.PAREN);
                        Exp cond = Exp();
                        eat (TokenType.THESIS);
                        return new While (cond, Stmt());
                    }
                else if (value.equals ("System.out.println"))
                    {   eat ("System.out.println");
                        eat (TokenType.PAREN);
                        Exp exp = Exp();
                        eat (TokenType.THESIS);
                        eat (TokenType.SEMI);
                        return new Print (exp);
                    }
                default: error();
        }
        return null;
    }
    
    public Stmt Assign(Identifier target)
    {   switch (tok)
        {   case RELOP: if (value.equals("="))
                            eat (TokenType.RELOP);
                            Exp source = Exp();
                            eat (TokenType.SEMI);
                            return new Assign (target, source);
            case LBRACKET:
                            eat (TokenType.LBRACKET);
                            Exp subscript = Exp ();
                            eat (TokenType.RBRACKET);
                            if (tok==TokenType.RELOP && value.equals ("="))
                                eat (TokenType.RELOP);
                            source = Exp();
                            eat (TokenType.SEMI);
                            return new ArrayAssign (target, subscript, source);
                            
            default:        error();
        }
        return null;
    }
    
    public Exp Exp()
    {   Exp left = And();
        Exp right = Elist(left);
        if (right==null)
            return left;
        return right;
    }
    
    public Exp Elist(Exp left)
    {   switch (tok)
        {   case DOT:    eat(TokenType.DOT);
                         return Member(left);
                      
            case LBRACKET: eat (TokenType.LBRACKET);
                           Exp array = new ArrayLookup (left, Exp());
                           eat (TokenType.RBRACKET);
                           return array;
                           
            case RELOP:  if (value.equals ("&&"))
                                {   eat (TokenType.RELOP);
                                    Exp and = new And (left, And());
                                    Exp nextE = Elist(and);
                                    if (nextE==null)
                                        return and;
                                    return nextE;
                                }
//             default:  error();
        }
        return null;
    }
    
    public Exp And()
    {   Exp left = Less();
        Exp right = AList(left);
        if (right==null)
            return left;
        return right;
    }
    
    public Exp AList(Exp left)
    {   if (is (TokenType.RELOP, "<"))
            {   eat (TokenType.RELOP);
                Exp less = new LessThan(left, And());
                Exp nextA = AList(less);
                if (nextA==null)
                    return less;
                return nextA;
            }
        return null;
    }
    
    public Exp Less()
    {   Exp left = Term();
        Exp right = LList(left);
        if (right==null)
            return left;
        return right;
    }
    
    public Exp LList(Exp left)
    {   if (is(TokenType.ARITHOP, "+"))
         {   eat (TokenType.ARITHOP);
             Exp plus = new Plus (left, Term());
             Exp nextL = LList (plus);
             if (nextL==null)
                return plus;
             return nextL;
         }
        if (is (TokenType.ARITHOP, "-"))
         {  eat (TokenType.ARITHOP);
            Exp minus = new Minus (left, Term());
             Exp nextL = LList (minus);
             if (nextL==null)
                return minus;
             return nextL;
         }
        return null;
    }
    
    public Exp Term()
    {    Exp left = Factor();
         Exp right = TList(left);
         if (right==null)
            return left;
         return right;
    }
    
    public Exp TList(Exp left)
    {   if (is(TokenType.ARITHOP, "*"))      // no division in MiniJava!
         {  eat (TokenType.ARITHOP);
            Exp times = new Times (left, Factor());
            Exp nextT = TList(times);
            if (nextT==null)
                return times;
            return nextT;
        }
        return null;
    }
    
    public Exp Factor()
    {   switch (tok)
        {   case NUMBER:    String num = value;
                            eat (TokenType.NUMBER);
                            return new IntegerLiteral(new Integer(num));
                            
            case ID:        String source = value;
                            eat (TokenType.ID);
                            return new IdentifierExp (source);
                            
            case NOT:       eat (TokenType.NOT);
                            return new Not (Exp());
                            
            case PAREN:     eat (TokenType.PAREN);
                            Exp tmp = Exp();
                            eat (TokenType.THESIS);
                            return tmp;
                            
            case KEY:
                            if (value.equals("true"))
                                {   eat ("true");
                                    return new True();
                                }
                            if (value.equals ("false"))
                                {   eat ("false");
                                    return new False();
                                }
                            if (value.equals ("this"))
                                {   eat ("this");
                                    return new This();
                                }
                            else if (value.equals ("new"))
                                {   eat ("new");
                                    return New();
                                }
            default:        error();
                            return null;
        }
    }
    
    public Exp Member(Exp left)
    {   if (is (TokenType.KEY, "length"))       // array.lenth
            {   eat ("length");
                return new ArrayLength(left);
            }
        if (tok==TokenType.ID)                  // method call 
            {   Identifier id = new Identifier (value);
                eat (TokenType.ID);
                eat (TokenType.PAREN);
                ExpList parms = expList();
                eat (TokenType.THESIS);
                return new Call (left, id, parms);
            }
        error();
        return null;
    }
    
    public Exp New()
    {   if (is (TokenType.KEY, "int"))      // new int[size]
            {   eat ("int");
                eat (TokenType.LBRACKET);
                Exp size = Exp();
                eat (TokenType.RBRACKET);
                return new NewArray (size);
            }
        if (tok==TokenType.ID)
            {   Identifier id = new Identifier (value);
                eat (TokenType.ID);
                eat (TokenType.PAREN);
                eat (TokenType.THESIS);
                return new NewObject (id);
            }
        error();
        return null;
    }
    
    public ExpList expList()
    {   ExpList <Exp> exps = new ExpList <Exp> ();
        if (expStart())
        {   exps.add (Exp());
            while (tok==TokenType.COMMA)
                exps.add (ExpRest());
        }
         return exps;
        
    }
    
    public Exp ExpRest()
    {   eat (TokenType.COMMA);
        return Exp();
    }     
    
    // Is the current token equal to the given token?
    private boolean is (TokenType type, String value)
    {   return type==tok && value.equals (this.value);  }
    
    // Advance to the next input token
    private void advance()
    {   prev = token;
        if (retracted)
            {   token = next;
                setFields();
                retracted = false;
            }
        else 
            token = lexer.getToken();
        if (token!=null)
            setFields();
    }
    
    // Back up to the previous token
    private void retract()
    {   next = token;
        token = prev;
        retracted = true;
        setFields();
    }
    
    private void setFields()
    {   tok = token.getType();
        value = token.getValue();
    }
    
    // Move past the current token, or indicate a syntax error
    private void eat (TokenType t)
    {   if (tok==t)
            advance();
        else
            error();
    }
    
    // Move past the current token, which should be the given key word
    private void eat (String key)
    {   if (tok==TokenType.KEY && value.equals (key))
            advance();
        else
            error();
    }
    
    // eat the token whatever it is
    private  void eat()
    {   advance();  }
    
    // Is the current token the start of an Exp?
    private boolean expStart()
    {  
        if (tok==TokenType.NUMBER)  return true;
        if (tok==TokenType.ID)  return true;
        if (tok==TokenType.NOT)  return true;
        if (tok==TokenType.PAREN)  return true;
        if (tok==TokenType.KEY)  
            return value.equals("true") || value.equals("false") || value.equals("this")
                || value.equals("new");
        return false;
    }
        
    private void error()
    {   System.err.println ("Error on " + token); 
        System.exit(0);
    }
    
    private void error (String msg)
    {   error();
        System.out.println ("\t " + msg);
    }
    
}
