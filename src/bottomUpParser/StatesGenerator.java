package bottomUpParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class StatesGenerator {

	
	/**
	 * Generate the first state using the rules listed. The starting non-terminal is the first one
	 * that appears in the list of rules ( the first line on the grammar file) 
	 * @param rules the set of rules that correspond the language
	 * @param nonTerminals the non-terminals of the language
	 * @return the first state of the DFA
	 */
	private State generateStateZero(List<Rule> rules, List<NonTerminal> nonTerminals) {
		// mount the first cell, the cell contain the begining of the grammar
		Expression e = new Expression();
		e.setRule(rules.get(0));
		e.setPosition(0);
		e.setLookAheadSym(null);
		State first = new State();
		first.addExpression(e);
		first.setId(0);
		if (Helper.isNonTerminal(e.getPositionItem(), nonTerminals)) {
			ArrayList<Expression> expressions = new ArrayList<>();
			expressions.add(e);
			for (int i = 0; i < expressions.size(); i++) {
				if (Helper.isNonTerminal(expressions.get(i).getPositionItem(), nonTerminals)) {
					for (Rule r : rules) {
						if (r.getNonTerminal().getName().equals(expressions.get(i).getPositionItem())) {
							if (Helper.isNonTerminal(expressions.get(i).lookAhead(), nonTerminals)) {
								Set<String> firstList = Helper
										.getNonTerminalByName(expressions.get(i).lookAhead(), nonTerminals)
										.getFirstSet();
								if (!firstList.isEmpty()) {
									for (String firstToken : firstList) {
										e = new Expression();
										e.setRule(r);
										e.setPosition(0);
										e.setLookAheadSym(firstToken);
										if (!expressions.contains(e)) {
											first.addExpression(e);
											expressions.add(e);
											// //System.out.println(e);
										}
									}
								} else {
									e = new Expression();
									e.setRule(r);
									e.setPosition(0);
									e.setLookAheadSym(expressions.get(i).getLookAheadSym());
									if (!expressions.contains(e)) {
										first.addExpression(e);
										expressions.add(e);
										// //System.out.println(e);
									}
								}
							} else {
								e = new Expression();
								e.setRule(r);
								e.setPosition(0);
								e.setLookAheadSym(Helper.first(expressions.get(i).lookAhead(),
										expressions.get(i).getLookAheadSym(), nonTerminals));
								if (!expressions.contains(e)) {
									first.addExpression(e);
									expressions.add(e);
									// //System.out.println(e);
								}
							}

						}
					}
				}
			}
		}
		for (Expression c : first.getExpressions()) {
			if (!first.getTransictions().keySet().contains(c.getPositionItem()) && !c.getPositionItem().isEmpty()
					&& !c.getPositionItem().equals("$")) {
				first.addState(c.getPositionItem(), null);
			}
		}
		return first;
	}

	/**
	 * Add the expression on the new state using already add expressions. The @param{newState} will
	 * have the new completed state at the end of this function.
	 * @param newState the new created state.
	 * @param rules the rules from the grammar
	 * @param nonTerminals the non-terminals from the grammar
	 */
	private void generateState(State newState, List<Rule> rules, List<NonTerminal> nonTerminals) {
		for (int j = 0; j < newState.getExpressions().size(); j++) {
			Expression e = newState.getExpressions().get(j);
			if (Helper.isNonTerminal(e.getPositionItem(), nonTerminals)) {
				ArrayList<Expression> expressions = new ArrayList<>();
				expressions.addAll(newState.getExpressions());
				for (int i = 0; i < expressions.size(); i++) {
					if (Helper.isNonTerminal(expressions.get(i).getPositionItem(), nonTerminals)) {
						for (Rule r : rules) {
							if (r.getNonTerminal().getName().equals(expressions.get(i).getPositionItem())) {
								if (Helper.isNonTerminal(expressions.get(i).lookAhead(), nonTerminals)) {
									Set<String> firstList = Helper
											.getNonTerminalByName(expressions.get(i).lookAhead(), nonTerminals)
											.getFirstSet();
									if (!firstList.isEmpty()) {
										for (String first : firstList) {
											e = new Expression();
											e.setRule(r);
											e.setPosition(0);
											e.setLookAheadSym(first);
											if (!expressions.contains(e)) {
												newState.addExpression(e);
												expressions.add(e);
											}
										}
									} else {
										e = new Expression();
										e.setRule(r);
										e.setPosition(0);
										e.setLookAheadSym(expressions.get(i).getLookAheadSym());
										if (!expressions.contains(e)) {
											newState.addExpression(e);
											expressions.add(e);
											// //System.out.println(e);
										}
									}
								} else {
									e = new Expression();
									e.setRule(r);
									e.setPosition(0);
									e.setLookAheadSym(Helper.first(expressions.get(i).lookAhead(),
											expressions.get(i).getLookAheadSym(), nonTerminals));
									if (!expressions.contains(e)) {
										newState.addExpression(e);
										expressions.add(e);
										// //System.out.println(e);
									}
								}
							}
						}
					}
				}

			}
		}
		for (Expression e : newState.getExpressions()) {
			if (!newState.getTransictions().keySet().contains(e.getPositionItem()) && !e.getPositionItem().isEmpty()
					&& !e.getPositionItem().equals("$")) {
				newState.addState(e.getPositionItem(), null);
			}
		}
	}

	/**
	 * Generate all the DFA states using the grammar's rules and the set of non-terminals
	 * @param rules the grammar's rules
	 * @param nonTerminals the grammar's non-terminal
	 * @return the list of the DFA states
	 */
	public List<State> generateStates(List<Rule> rules, List<NonTerminal> nonTerminals) {
		System.out.println("Generating states");
		State stateZero = generateStateZero(rules, nonTerminals);
		List<State> stateList = new ArrayList<>();
		stateList.add(stateZero);
		int id = 0;
		for (int i = 0; i < stateList.size(); i++) {
			State aux = stateList.get(i);
			//System.out.println(aux);
			Set<String> transictionsSet = aux.getTransictions().keySet();
			for (String s : transictionsSet) {
				ArrayList<Expression> expressions = aux.getExpressions();
				ArrayList<Expression> nextStateExpressions = new ArrayList<>();
				for (Expression e : expressions) {
					if (e.getPositionItem().equals(s)) {
						Expression newEx = new Expression();
						newEx.setRule(e.getRule());
						newEx.setPosition(e.getPosition() + 1);
						newEx.setLookAheadSym(e.getLookAheadSym());
						nextStateExpressions.add(newEx);
					}
				}
				State newState = new State();
				newState.setExpressions(nextStateExpressions);
				generateState(newState, rules, nonTerminals);
				if (!stateList.contains(newState)) {
					newState.setId(++id);
					stateList.add(newState);
					aux.addState(s, newState);
				} else {
					aux.addState(s, stateList.get(stateList.indexOf(newState)));
				}
			}
//			for(String s : aux.getTransictions().keySet()){
//				//System.out.print(s + " ");
//			}
			//System.out.println();			
		}
		return stateList;
	}
}
