package lexer;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class Classifier {

	
	public static final Token classify(String token){
		if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") 
				|| token.equals("%")){
			return new TokenArithOp(token);
		}
		if(token.equals("&") || token.equals("|") || token.equals("!")){
			return new TokenBoolOp(token);
		}
		if(token.equals("==") || token.equals(">") || token.equals("<") || token.equals("<=")
				|| token.equals(">=")){
			return new TokenCompare(token);
		}
		if(token.equals("=")){
			return new TokenAssign(token);
		}
		if(token.equals("(")){
			return new TokenLParenthesis(token);
		}
		if(token.equals(")")){
			return new TokenRParenthesis(token);
		}
		if(token.equals("{")){
			return new TokenLBrace(token);
		}
		if(token.equals("}")){
			return new TokenRBrace(token);
		}
		if(token.equals(";")){
			return new TokenEndCommand(token);
		}
		if(token.equals(",")){
			return new TokenComma(token);
		}
		if(token.equals("[")){
			return new TokenLBracket(token);
		}
		if(token.equals("]")){
			return new TokenRBracket(token);
		}
		if(token.equals(" ") || token.equals(String.valueOf((char)9)) || token.equals("\n")){
			return new TokenSpace(token);
		}
		if(token.contains("//")){
			return new TokenComment(token);
		}
		if(token.contains("\'")){
			return new TokenChar(token);
		}
		if(token.contains("\"")){
			return new TokenString(token);
		}
		if(token.equalsIgnoreCase("main")){
			return new TokenMain(token);
		}
		if(token.equalsIgnoreCase("true") || token.equalsIgnoreCase("false")){
			return new TokenBoolean(token);
		}
		if(token.equalsIgnoreCase("int") || token.equalsIgnoreCase("float") || token.equalsIgnoreCase("boolean")
				|| token.equalsIgnoreCase("char") || token.equalsIgnoreCase("string") || token.equalsIgnoreCase("void")){
			return new TokenType(token);
		}
		if(token.equalsIgnoreCase("while")){
			return new TokenWhile(token);
		}
		if(token.equalsIgnoreCase("if")){
			return new TokenIf(token);
		}
		if(token.equalsIgnoreCase("else")){
			return new TokenElse(token);
		}
		if(Character.isDigit(token.charAt(0))){
			if(token.contains(".")){
				return new TokenFloatNum(token);
			} else {
				return new TokenIntNum(token);
			}
		}
		if(Character.isLetter(token.charAt(0))){
			return new TokenIdentifier(token);
		}
		return new TokenMisc(token);		
	}
}
