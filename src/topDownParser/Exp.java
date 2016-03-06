package topDownParser;

import java.util.Queue;

import lexer.Token;

public class Exp {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {		
		CompareExp.parse(tokenQueue);
		CList.parse(tokenQueue);		
	}

}
