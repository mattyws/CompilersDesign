package topDownParser;

import java.util.Queue;

import lexer.Token;
import lexer.TokenBoolean;
import lexer.TokenChar;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIntNum;
import lexer.TokenLBracket;
import lexer.TokenLParenthesis;
import lexer.TokenRBracket;
import lexer.TokenRParenthesis;
import lexer.TokenString;
import treeNodes.NExp;
import treeNodes.NExpParam;
import treeNodes.exp.NArrayCall;
import treeNodes.exp.NBoolean;
import treeNodes.exp.NChar;
import treeNodes.exp.NExpMethodCall;
import treeNodes.exp.NFloatNum;
import treeNodes.exp.NIdentifier;
import treeNodes.exp.NIntNum;
import treeNodes.exp.NString;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class Factor {

	public static NExp parse(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenIntNum.class)) {
			TokenIntNum num = (TokenIntNum) Helper.eat(tokenQueue, TokenIntNum.class);
			return new NIntNum(Integer.valueOf(num.getToken()));
		} else if (Helper.is(tokenQueue.peek(), TokenFloatNum.class)) {
			TokenFloatNum num = (TokenFloatNum) Helper.eat(tokenQueue, TokenFloatNum.class);
			return new NFloatNum(Float.valueOf(num.getToken()));
		} else if (Helper.is(tokenQueue.peek(), TokenBoolean.class)) {
			TokenBoolean bool = (TokenBoolean) Helper.eat(tokenQueue, TokenBoolean.class);
			return new NBoolean(Boolean.valueOf(bool.getToken()));
		} else if (Helper.is(tokenQueue.peek(), TokenIdentifier.class)) {
			TokenIdentifier tId = (TokenIdentifier) Helper.eat(tokenQueue, TokenIdentifier.class);
			if (Helper.is(tokenQueue.peek(), TokenLBracket.class)) {
				Helper.eat(tokenQueue, TokenLBracket.class);
				NExp exp = Exp.parse(tokenQueue);
				Helper.eat(tokenQueue, TokenRBracket.class);
				NArrayCall call = new NArrayCall(new NIdentifier(tId.getToken()), exp);
				return call;
			}
			if (Helper.is(tokenQueue.peek(), TokenLParenthesis.class)) {
				Helper.eat(tokenQueue, TokenLParenthesis.class);
				NExpParam param = ExpParam.parse(tokenQueue);
				Helper.eat(tokenQueue, TokenRParenthesis.class);
				NExpMethodCall call = new NExpMethodCall(new NIdentifier(tId.getToken()), param);
				return call;
			}
			NIdentifier nId = new NIdentifier(tId.getToken());
			return nId;
		} else if (Helper.is(tokenQueue.peek(), TokenString.class)) {
			TokenString string = (TokenString) Helper.eat(tokenQueue, TokenString.class);
			return new NString(string.getToken());
		} else if (Helper.is(tokenQueue.peek(), TokenLParenthesis.class)) {
			Helper.eat(tokenQueue, TokenLParenthesis.class);
			NExp exp = Exp.parse(tokenQueue);
			Helper.eat(tokenQueue, TokenRParenthesis.class);
			return exp;
		} else {
			TokenChar tChar = (TokenChar) Helper.eat(tokenQueue, TokenChar.class);
			if (tChar.getToken().length() > 1) {
				throw new ParserException("Invalid character constant");
			}
			return new NChar(tChar.getToken().charAt(0));
		}
	}

}
