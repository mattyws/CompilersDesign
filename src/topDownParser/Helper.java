package topDownParser;

import java.text.ParseException;
import java.util.Queue;

import lexer.Token;
import lexer.TokenArithOp;
import lexer.TokenAssign;
import lexer.TokenBoolOp;
import lexer.TokenBoolean;
import lexer.TokenComma;
import lexer.TokenComment;
import lexer.TokenCompare;
import lexer.TokenElse;
import lexer.TokenEndCommand;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIf;
import lexer.TokenIntNum;
import lexer.TokenLBrace;
import lexer.TokenLParenthesis;
import lexer.TokenRBrace;
import lexer.TokenRParenthesis;
import lexer.TokenType;
import lexer.TokenWhile;

public class Helper {

	/**
	 * Consume a token of the queue. The token has to have the same class of
	 * tokenClass and its value have to be the same than the parameter value.
	 * Raise a error if the conditions are not met.
	 * 
	 * @param tokenQueue
	 *            the token queue
	 * @param tokenClass
	 *            the token class that the next token in the queue have to be
	 *            equal
	 * @param value
	 *            the value that the next token in the queue have to be equal
	 */
	public static void eat(Queue<Token> tokenQueue, Class tokenClass, String value) throws ParserException {
		if (tokenQueue.size() > 0) {
			if (tokenQueue.peek().getClass() == tokenClass) {
				if (tokenQueue.peek().getToken() == value) {
					tokenQueue.poll();
				} else {
					throw new ParserException(createMessage(tokenQueue.peek().getToken(), value));
				}
			} else {
				throw new ParserException(createMessage(tokenQueue.peek(), tokenClass));
			}
		} else {
			throw new ParserException("Not enough token to consume");
		}
	}

	/**
	 * Consume a token of the queue. The token has to have the same class of
	 * tokenClass. Raise a error if the conditions are not met.
	 * 
	 * @param tokenQueue
	 * @param tokenClass
	 * @throws ParserException 
	 */
	public static void eat(Queue<Token> tokenQueue, Class tokenClass) throws ParserException {
		if (tokenQueue.size() > 0) {
			if (tokenQueue.peek().getClass() == tokenClass) {
				tokenQueue.poll();
			} else {
				throw new ParserException(createMessage(tokenQueue.peek(), tokenClass));
			}
		} else {
			throw new ParserException("Not enough token to consume");
		}
	}

	/**
	 * Compare if the token have the same class as tokenClass.
	 * 
	 * @param token
	 *            the token
	 * @param tokenClass
	 *            the class that the token has to be equal
	 * @return true if it's equal, false case not
	 */
	public static boolean is(Token token, Class tokenClass) {
		return token.getClass() == tokenClass;
	}

	/**
	 * Compare if the token have the same class as tokenClass and have the same
	 * value as the tokenValue.
	 * 
	 * @param token
	 *            the token
	 * @param tokenClass
	 *            the class that the token has to be equal
	 * @param tokenValue
	 *            the value that the token has to have
	 * @return true if it's equal, false case not
	 */
	public static boolean is(Token token, Class tokenClass, String tokenValue) {
		return (token.getClass() == tokenClass) && (token.getToken().equalsIgnoreCase(tokenValue));
	}

	public static void raiseError(String message) throws ParserException{
		throw new ParserException(message);
	}

	private static String createMessage(Token token, Class tokenClass) {
		String expected = "";
		if (tokenClass == TokenArithOp.class) {
			expected += "\"+\" or \"-\" or \"/\" or \"*\"";
		} else if (tokenClass == TokenBoolOp.class) {
			expected += "\"|\" or \"&\" or \"!\"";
		} else if (tokenClass == TokenCompare.class) {
			expected += "\"==\" or \">\" or \"<\" or \">=\" or \"<=\"";
		} else if (tokenClass == TokenAssign.class) {
			expected += "\"=\"";
		} else if (tokenClass == TokenLParenthesis.class) {
			expected += "\"(\"";
		} else if (tokenClass == TokenRParenthesis.class) {
			expected += "\")\"";
		} else if (tokenClass == TokenLBrace.class) {
			expected += "\"{\"";
		} else if (tokenClass == TokenRBrace.class) {
			expected += "\"}\"";
		} else if (tokenClass == TokenEndCommand.class) {
			expected += "\";\"";
		} else if (tokenClass == TokenComma.class) {
			expected += "\",\"";
		} else if (tokenClass == TokenBoolean.class) {
			expected += "\"true\" or \"false\"";
		} else if (tokenClass == TokenType.class) {
			expected += "\"int\" or \"float\" or \"boolean\" or \"char\" ir \"string\"";
		} else if (tokenClass == TokenWhile.class) {
			expected += "\"while\"";
		} else if (tokenClass == TokenIf.class) {
			expected += "\"if\"";
		} else if (tokenClass == TokenElse.class) {
			expected += "\"else\"";
		} else if (tokenClass == TokenFloatNum.class || tokenClass == TokenIntNum.class) {
			expected += "a number";
		} else if (tokenClass == TokenIdentifier.class) {
			expected += "a identifier";
		}
		return "Expecting " + expected + " got " + token.getToken() + " instead.";
	}

	private static String createMessage(String token, String value) {
		return "Expecting " + token + " found " + value + " instead.";
	}
}
