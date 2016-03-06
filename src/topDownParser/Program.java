package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import lexer.TokenType;

public class Program {

	public static void parse(Queue<Token> tokenQueue) throws ParserException{
		while(!Helper.is((Token)tokenQueue.toArray()[1], TokenIdentifier.class, "main")){
			FuncDecl.parse(tokenQueue);
		}
		MainFunc.parse(tokenQueue);
	}
}
