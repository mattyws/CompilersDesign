package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenComma;
import lexer.TokenEndCommand;
import lexer.TokenIdentifier;
import lexer.TokenType;

public class VarDecl {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenType.class);
		Helper.eat(tokenQueue, TokenIdentifier.class);
		while (Helper.is(tokenQueue.peek(), TokenComma.class)) {
			VarDeclList.parse(tokenQueue);
		}
		Helper.eat(tokenQueue, TokenEndCommand.class);
	}

}
