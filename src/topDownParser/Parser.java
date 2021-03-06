package topDownParser;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import lexer.Token;
import lexer.TokenComment;
import lexer.TokenMisc;
import lexer.TokenSpace;
import treeNodes.NProgram;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class Parser {
	
	public NProgram parse(List<Token> tokens) throws ParserException{
		int pos = 0;
		Queue<Token> tokenQueue = transform(tokens);
		NProgram program = Program.parse(tokenQueue);
		return program;
	}

	/**
	 * Transform the list into a queue and remove ignored token
	 * @param tokens
	 * @return
	 */
	private Queue<Token> transform(List<Token> tokens) {
		Queue<Token> tokenQueue = new LinkedBlockingQueue<>();
		for(Token t : tokens){
			if(t.getClass() != TokenSpace.class && t.getClass() != TokenComment.class
					&& t.getClass() != TokenMisc.class){
				tokenQueue.add(t);
			}
		}
		return tokenQueue;
	}
	
	
}
