package topDownParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import lexer.TokenIf;
import lexer.TokenLBrace;
import lexer.TokenLParenthesis;
import lexer.TokenRBrace;
import lexer.TokenRParenthesis;
import lexer.TokenType;
import lexer.TokenWhile;
import treeNodes.NFunctionDecl;
import treeNodes.NParameter;
import treeNodes.NStatement;
import treeNodes.NType;
import treeNodes.exp.NIdentifier;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class FuncDecl {

	public static NFunctionDecl parse(Queue<Token> tokenQueue) throws ParserException {
//		if (!Helper.is(tokenQueue.peek(), TokenType.class)) {
			NType type = Type.parse(tokenQueue);
			NIdentifier id = new NIdentifier(Helper.eat(tokenQueue, TokenIdentifier.class).getToken());
			Helper.eat(tokenQueue, TokenLParenthesis.class);
			List<NParameter> parameters = Parameters.parse(tokenQueue);
			Helper.eat(tokenQueue, TokenRParenthesis.class);
			Helper.eat(tokenQueue, TokenLBrace.class);
			List<NStatement> body = new ArrayList<>();
			while (Helper.is(tokenQueue.peek(), TokenLBrace.class) || Helper.is(tokenQueue.peek(), TokenType.class)
					|| Helper.is(tokenQueue.peek(), TokenIdentifier.class)
					|| Helper.is(tokenQueue.peek(), TokenWhile.class) || Helper.is(tokenQueue.peek(), TokenIf.class)) {
				NStatement stm = Stm.pase(tokenQueue);
				body.add(stm);
			}
			Helper.eat(tokenQueue, TokenRBrace.class);
			NFunctionDecl funcDecl = new NFunctionDecl(type, id, parameters, body);
			return funcDecl;
//		}
//		return null;
	}

}
