package topDownParser;

import java.util.Queue;

import lexer.Token;

public class PlusMinusExp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		DivisionExp.parse(tokenQueue);
		DList.parse(tokenQueue);
	}

}
