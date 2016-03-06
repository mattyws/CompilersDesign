package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIf;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;

public class IfStm {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		Helper.eat(tokenQueue, TokenIf.class);
		Helper.eat(tokenQueue, TokenLParenthesis.class);
		Exp.parse(tokenQueue);
		Helper.eat(tokenQueue, TokenRParenthesis.class);
		Stm.pase(tokenQueue);
		ElseStm.parse(tokenQueue);
	}

}
