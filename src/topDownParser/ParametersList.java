package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import lexer.TokenType;

public class ParametersList {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenType.class);
		Helper.eat(tokenQueue, TokenIdentifier.class);
	}

}
