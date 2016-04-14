package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenChar;
import lexer.TokenIdentifier;
import lexer.TokenLParenthesis;
import lexer.TokenRParenthesis;
import lexer.TokenString;
import treeNodes.NExp;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class Exp {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		NExp exp = CompareExp.parse(tokenQueue);
		NExp exp2 = CList.parse(tokenQueue);
		if(exp2 != null){
			exp2.setL(exp);
			return exp2;
		} else {
			return exp;
		}
	}

}
