package bottomUpParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import lexer.Token;
import lexer.TokenComment;
import lexer.TokenMisc;
import lexer.TokenSpace;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class Helper {

	public static final List<NonTerminal> getNonTerminals(List<Rule> rules){
		List<NonTerminal> nonTerminals = new ArrayList<NonTerminal>();
		for(Rule r : rules) {
			if(!nonTerminals.contains(r.getNonTerminal()))
				nonTerminals.add(r.getNonTerminal());
		}
		return nonTerminals;
	}
	
	public static final boolean isNonTerminal(String item, List<NonTerminal> nonTerminals){
		for(NonTerminal nt : nonTerminals){
			if(nt.getName().equals(item)){
				return true;
			}
		}
		return false;
	}
	
	public static final List<Rule> getRules(String nonTerminal, List<Rule> rules){
		List<Rule> ntRules = new ArrayList<>();
		for(Rule r : rules) {
			if(r.getNonTerminal().getName().equals(nonTerminal)){
				ntRules.add(r);
			}
		}
		return ntRules;
		
	}
	
	public static final NonTerminal getNonTerminalByName(String name, List<NonTerminal> nonterminals) {
		for (NonTerminal n : nonterminals) {
			if (n.getName().equals(name)) {
				return n;
			}
		}
		return null;
	}
	
	/**
	 * Remove ignored token
	 * @param tokens
	 * @return
	 */
	public static final List<Token> removeIgnored(List<Token> tokens) {
		List<Token> tokenQueue = new ArrayList<>();
		for(Token t : tokens){
			if(t.getClass() != TokenSpace.class && t.getClass() != TokenComment.class
					&& t.getClass() != TokenMisc.class){
				tokenQueue.add(t);
			}
		}
		return tokenQueue;
	}

	public static String first(String lookAhead, String lookAheadSym, List<NonTerminal> nonTerminals) {
		if(lookAhead.isEmpty() || Helper.isNonTerminal(lookAhead, nonTerminals)){
			return lookAheadSym;
		} else {
			return lookAhead;
		}
	}
}
