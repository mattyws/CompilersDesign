package bottomUpParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import lexer.Lexer;
import lexer.Token;
import lexer.TokenChar;
import lexer.TokenFloatNum;
import lexer.TokenIdentifier;
import lexer.TokenIntNum;
import lexer.TokenString;
/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class Parser {
	
	private StatesGenerator statesGenerator = new StatesGenerator();
	private TableGenerator tableGenerator = new TableGenerator();

	public void parse(String programFile, String grammarFile){
		BufferedReader br = null;
		String program = "";
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(programFile));
			while ((sCurrentLine = br.readLine()) != null) {
				program += sCurrentLine + "\n";
			}
			System.out.println(program);

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

		Lexer l = new Lexer();
		List<Token> tokens = Helper.removeIgnored(l.generateTokens(program));
		tokens.add(new TokenEndProgram("$"));
		GrammarConverter converter = new GrammarConverter();
		List<Rule> rules = converter.convert(grammarFile);
		List<NonTerminal> nonTerminals = Helper.getNonTerminals(rules);
		List<TableRow> table = tableGenerator.generateTable(statesGenerator.generateStates(rules, nonTerminals), nonTerminals);
		parseTable(table, rules, tokens);
	}
	
	/**
	 * Use the generated table to parse the program
	 * @param table the bottom-up table
	 * @param rules the grammar rules
	 * @param tokens the token representing the program
	 */
	public static void parseTable(List<TableRow> table, List<Rule> rules, List<Token> tokens) {
		Stack<String> acceptanceStack = new Stack<>();
		Stack<TableRow> orientationStack = new Stack<>();
		orientationStack.push(table.get(0));
		int i = 0;
		while (i < tokens.size()) {
			Token tokenNow = tokens.get(i);
			TableRow row = orientationStack.peek();
			String action;
			if (tokenNow.getClass() == TokenIdentifier.class) {
				action = row.get("id");
			} else if (tokenNow.getClass() == TokenIntNum.class || tokenNow.getClass() == TokenFloatNum.class) {
				action = row.get("number");
			} else if (tokenNow.getClass() == TokenString.class) {
				action = row.get("stringValue");
			} else if (tokenNow.getClass() == TokenChar.class) {
				action = row.get("charValue");
			} else {
				action = row.get(tokenNow.getToken());
			}

			System.out.println("Token : " + tokenNow.getToken() + " " + tokenNow.getClass());
			System.out.println("Action : " + action);
			System.out.println(row);
			if (action.equals("a")) {
				System.out.println("Program accepted!");
				break;
			} else if (action.charAt(0) == 's') {
				acceptanceStack.push(tokenNow.getToken());
				orientationStack.push(table.get(Integer.valueOf(action.substring(1, action.length()))));
				i++;
			} else if (action.charAt(0) == 'r') {
				Rule rule = rules.get(Integer.valueOf(action.substring(1, action.length())));
				int aux = 0;
				while (aux < rule.getRule().size() && !rule.isNullRule()) {
					row = orientationStack.pop();
					aux++;
					acceptanceStack.pop();
				}
				acceptanceStack.push(rule.getNonTerminal().getName());
				row = orientationStack.peek();
				action = row.get(String.valueOf(acceptanceStack.peek()));
				System.out.println(action);
				orientationStack.push(table.get(Integer.valueOf(action.substring(1, action.length()))));
			}
		}
	}

	/**
	 * UNUSED - created just for understanding the table construction problem
	 * @param table
	 * @param rules
	 */
	public static void stringTableUse(String program, List<TableRow> table, List<Rule> rules) {
		Stack<String> acceptanceStack = new Stack<>();
		Stack<TableRow> orientationStack = new Stack<>();
		orientationStack.push(table.get(0));
		int i = 0;
		while (i < program.length()) {
			char charNow = program.charAt(i);
			TableRow row = orientationStack.peek();
			String action = row.get(String.valueOf(charNow));
			System.out.println("Token : " + charNow);
			System.out.println("Action : " + action);
			System.out.println(row);
			if (action.equals("a")) {
				System.out.println("AEEE POHA!");
				break;
			} else if (action.charAt(0) == 's') {
				acceptanceStack.push(String.valueOf(charNow));
				orientationStack.push(table.get(Integer.valueOf(action.substring(1, action.length()))));
				i++;
			} else if (action.charAt(0) == 'r') {
				Rule rule = rules.get(Integer.valueOf(action.substring(1, action.length())));
				int aux = 0;
				while (aux < rule.getRule().size()) {
					row = orientationStack.pop();
					aux++;
					acceptanceStack.pop();
				}
				acceptanceStack.push(rule.getNonTerminal().getName());
				row = orientationStack.peek();
				System.out.println(row);
				action = row.get(String.valueOf(acceptanceStack.peek()));
				orientationStack.push(table.get(Integer.valueOf(action.substring(1, action.length()))));
			}
		}
	}

	
	
}
