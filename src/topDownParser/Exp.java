package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenChar;
import lexer.TokenIdentifier;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;
import lexer.TokenString;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class Exp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenString.class)) {
			Helper.eat(tokenQueue, TokenString.class);
		} else if (Helper.is(tokenQueue.peek(), TokenChar.class)) {
			Helper.eat(tokenQueue, TokenChar.class);
		} else {
			CompareExp.parse(tokenQueue);
			CList.parse(tokenQueue);
		}
	}

}
