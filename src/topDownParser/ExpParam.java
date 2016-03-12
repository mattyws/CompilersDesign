package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenAssign;
import lexer.TokenBoolean;
import lexer.TokenChar;
import lexer.TokenComma;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIntNum;
import lexer.TokenString;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class ExpParam {

	public static void parse(Queue<Token> tokenQueue) throws ParserException {
		if(Helper.is(tokenQueue.peek(), TokenIntNum.class) || Helper.is(tokenQueue.peek(), TokenFloatNum.class)
				|| Helper.is(tokenQueue.peek(), TokenIdentifier.class) || Helper.is(tokenQueue.peek(), TokenBoolean.class)
				|| Helper.is(tokenQueue.peek(), TokenString.class) || Helper.is(tokenQueue.peek(), TokenChar.class)){			
			Exp.parse(tokenQueue);
			while(Helper.is(tokenQueue.peek(), TokenComma.class)){
				ExpParamList.parse(tokenQueue);
			}
		}
	}

}
