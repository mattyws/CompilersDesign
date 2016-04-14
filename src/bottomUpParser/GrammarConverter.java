package bottomUpParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import lexer.Lexer;
import lexer.Token;
import topDownParser.Parser;
import topDownParser.ParserException;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class GrammarConverter {

	public List<Rule> convert(String grammarFileName) {

		BufferedReader br = null;
		List<Rule> rules = null;
		try {
			rules = new ArrayList<Rule>();
			String sCurrentLine;
			br = new BufferedReader(new FileReader(grammarFileName));
			int ruleId = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				Rule rule = new Rule(ruleId);
				ruleId++;
				String[] tokenStream = sCurrentLine.split("->");
				rule.setNonTerminal(new NonTerminal(tokenStream[0].trim()));
				ArrayList<String> list = new ArrayList<>();
				list.addAll(Arrays.asList(tokenStream[1].trim().split(" ")));
				rule.setRule(list);
				rules.add(rule);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		List<NonTerminal> nonTerminals = Helper.getNonTerminals(rules);
		for (NonTerminal n : nonTerminals) {
			for (Rule r : rules) {
				if (n.equals(r.getNonTerminal())) {
					if (r.isNullRule()) {
						n.setHasNullRule(true);
					}
				}
			}
		}
		for (int i = 0; i < nonTerminals.size(); i++) {
			// System.out.println(nonTerminals.get(i));
//			System.out.println("First do: " + nonTerminals.get(i));
			nonTerminals.get(i).setFirstSet(first(nonTerminals.get(i), rules, nonTerminals, new ArrayList<String>()));
//			System.out.print(nonTerminals.get(i) + " : ");
//			for(String s: nonTerminals.get(i).getFirstSet()){
//				System.out.print(s + " ");
//			}
//			System.out.println();
		}
		for (int i = 0; i < nonTerminals.size(); i++) {
			nonTerminals.get(i).setFollowSet(follow(nonTerminals.get(i), rules, nonTerminals));
		}
		for (int i = 0; i < rules.size(); i++) {
			for (int j = 0; j < nonTerminals.size(); j++) {
				if (rules.get(i).getNonTerminal().equals(nonTerminals.get(j))) {
					rules.get(i).setNonTerminal(nonTerminals.get(j));
				}
			}
		}
//		System.out.println("hEAUHAEUHEA");
//		Set<String> firstSet = first(Helper.getNonTerminalByName("Program", nonTerminals), rules, nonTerminals, new ArrayList<>());
//		for(String s : firstSet){
//			System.out.println(s);
//		}
		return rules;
	}

	public Set<String> follow(NonTerminal nt, List<Rule> rules, List<NonTerminal> nonTerminals) {
		Set<String> followList = new HashSet<>();
		for (int k = 0; k < rules.size(); k++) {
			Rule r = rules.get(k);
			for (int i = 0; i < r.getRule().size() - 1; i++) {
				if (nt.getName().equals(r.getRule().get(i))) {
					if (Helper.isNonTerminal(r.getRule().get(i + 1), nonTerminals)) {
						followList.addAll(first(Helper.getNonTerminalByName(r.getRule().get(i + 1), nonTerminals), rules,
								nonTerminals, new ArrayList<String>()));
					} else {
						followList.add(r.getRule().get(i + 1));
					}
				}
			}
		}
		return followList;
	}

	

	private Set<String> first(NonTerminal nt, List<Rule> rules, List<NonTerminal> nonTerminals,
			List<String> checkedNonTerminals) {
		Set<String> firstList = new HashSet<>();
		for (int k = 0; k < rules.size(); k++) {			
			Rule r = rules.get(k);
			if (r.getNonTerminal().equals(nt)) {
				if (!r.isNullRule() && !checkedNonTerminals.contains(r.getRule().get(0))
						&& Helper.isNonTerminal(r.getRule().get(0), nonTerminals)) {
					checkedNonTerminals.add(nt.getName());
					NonTerminal ruleNonTerminal = Helper.getNonTerminalByName(r.getRule().get(0), nonTerminals);
//					System.out.println("Chamando first para o : " + r.getRule().get(0));
//					System.out.println();
					firstList.addAll(first(ruleNonTerminal, rules, nonTerminals, checkedNonTerminals));
//					checkedNonTerminals.add(r.getRule().get(0));
					int i = 1;
					// if the rule has a null rule, add the next non terminals
					// on first
					if (ruleNonTerminal.hasNullRule()) {	
//						System.out.println("RUle gave null " + ruleNonTerminal);
						while (i < r.getRule().size() && Helper.isNonTerminal(r.getRule().get(i), nonTerminals)
								&& ruleNonTerminal.hasNullRule()) {										
							ruleNonTerminal = Helper.getNonTerminalByName(r.getRule().get(i), nonTerminals);
//							System.out.println(ruleNonTerminal);
							firstList.addAll(first(ruleNonTerminal, rules, nonTerminals, checkedNonTerminals));
							i++;
						}
//						System.out.println("Leave loop with " + ruleNonTerminal);
						if (i < r.getRule().size() && !Helper.isNonTerminal(r.getRule().get(i), nonTerminals)) {
							firstList.add(r.getRule().get(i));
						} else {
							firstList.addAll(first(ruleNonTerminal, rules, nonTerminals, checkedNonTerminals));
						}
					}
				} else if (!r.isNullRule() && !Helper.isNonTerminal(r.getRule().get(0), nonTerminals)) {
					firstList.add(r.getRule().get(0));
				}
			}
		}
		return firstList;
	}

}
