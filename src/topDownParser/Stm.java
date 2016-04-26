package topDownParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import lexer.Token;
import lexer.TokenIdentifier;
import lexer.TokenIf;
import lexer.TokenLBrace;
import lexer.TokenRBrace;
import lexer.TokenReturn;
import lexer.TokenType;
import lexer.TokenWhen;
import lexer.TokenWhile;
import treeNodes.NStatement;
import treeNodes.statements.NIfStatement;
import treeNodes.statements.NReturnStatement;
import treeNodes.statements.NScopedStatement;
import treeNodes.statements.NVarDeclStatement;
import treeNodes.statements.NWhenStatement;
import treeNodes.statements.NWhileStatement;

/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */

public class Stm {

	public static NStatement pase(Queue<Token> tokenQueue) throws ParserException {
		if (Helper.is(tokenQueue.peek(), TokenLBrace.class)) {
			Helper.eat(tokenQueue, TokenLBrace.class);
			List<NStatement> statements = new ArrayList<>();
			while (Helper.is(tokenQueue.peek(), TokenLBrace.class) || Helper.is(tokenQueue.peek(), TokenType.class)
					|| Helper.is(tokenQueue.peek(), TokenIdentifier.class) || Helper.is(tokenQueue.peek(), TokenWhen.class)
					|| Helper.is(tokenQueue.peek(), TokenWhile.class) || Helper.is(tokenQueue.peek(), TokenIf.class)
					|| Helper.is(tokenQueue.peek(), TokenReturn.class) ) {
				NStatement statement = Stm.pase(tokenQueue);
				if (statement != null)
					statements.add(statement);
			}
			Helper.eat(tokenQueue, TokenRBrace.class);
			NScopedStatement scoped = new NScopedStatement(statements);
			return scoped;
		} else if (Helper.is(tokenQueue.peek(), TokenType.class)) {
			NVarDeclStatement varDecl = VarDecl.parse(tokenQueue);
			return varDecl;
		} else if (Helper.is(tokenQueue.peek(), TokenIdentifier.class)) {
			NStatement idStm = IdStm.parse(tokenQueue);
			return idStm;
		} else if (Helper.is(tokenQueue.peek(), TokenWhile.class)) {
			NWhileStatement whileStm = WhileStm.parse(tokenQueue);
			return whileStm;
		} else if (Helper.is(tokenQueue.peek(), TokenIf.class)) {
			NIfStatement ifStm = IfStm.parse(tokenQueue);
			return ifStm;
		} else if (Helper.is(tokenQueue.peek(), TokenReturn.class)) {
			NReturnStatement returnStm = ReturnStm.parse(tokenQueue);
			return returnStm;
		} else if (Helper.is(tokenQueue.peek(), TokenWhen.class)) {
			NWhenStatement whenStm = WhenStm.parse(tokenQueue);
			return whenStm;
		} else {
			Helper.raiseError("Expecting \"{\", \"int\", \"boolean\", \"float\","
					+ " \"char\", \"string\", a identifier, \"while\", \"if\", got " + tokenQueue.peek().getToken());
		}
		return null;
	}

}