package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenBoolean;
import lexer.TokenChar;
import lexer.TokenComma;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIntNum;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;
import lexer.TokenString;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class FunctionExp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenIdentifier.class) 
				&& Helper.is((Token)tokenQueue.toArray()[1], TokenLParenthesis.class)) {
			Helper.eat(tokenQueue, TokenIdentifier.class);
			Helper.eat(tokenQueue, TokenLParenthesis.class);
			ExpParam.parse(tokenQueue);
			Helper.eat(tokenQueue, TokenRParenthesis.class);
		}  else if (Helper.is(tokenQueue.peek(), TokenIntNum.class) || Helper.is(tokenQueue.peek(), TokenFloatNum.class)
				|| Helper.is(tokenQueue.peek(), TokenBoolean.class)
				|| Helper.is(tokenQueue.peek(), TokenIdentifier.class)) {
			Factor.parse(tokenQueue);
		}
	}

}
