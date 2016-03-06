package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import lexer.TokenIf;
import lexer.TokenLBrace;
import lexer.TokenLParenthesis;
import lexer.TokenRBrace;
import lexer.TokenRParenthesis;
import lexer.TokenType;
import lexer.TokenWhile;

public class FuncDecl {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenType.class);
		Helper.eat(tokenQueue, TokenIdentifier.class);
		Helper.eat(tokenQueue, TokenLParenthesis.class);
		Parameters.parse(tokenQueue);
		Helper.eat(tokenQueue, TokenRParenthesis.class);
		Helper.eat(tokenQueue, TokenLBrace.class);
		while (Helper.is(tokenQueue.peek(), TokenLBrace.class) || Helper.is(tokenQueue.peek(), TokenType.class)
				|| Helper.is(tokenQueue.peek(), TokenIdentifier.class) || Helper.is(tokenQueue.peek(), TokenWhile.class)
				|| Helper.is(tokenQueue.peek(), TokenIf.class)) {
			Stm.pase(tokenQueue);
		}
		Helper.eat(tokenQueue, TokenRBrace.class);
	}

}
