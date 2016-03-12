package lexer;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Lucas Menezes, Mattyws Grawe, Vitor Finati
 *
 */
public class Lexer {
	
	public List<Token> generateTokens(String program) {
		int pos = 0;
		List<Token> tokens = new ArrayList<>();
		// loop trough the program
		while(pos < program.length()){
			String value = "";
			// loop until get an symbol
			while( !isTokenizeSymbol(program.charAt(pos)) ){
				value += program.charAt(pos);
				pos++;
			}
			// identifying the token, here will enter if the token is a text, not a symbol
			if(!value.isEmpty())
				tokens.add(Classifier.classify(value));
			// identifying the symbol, first test if it is an compare token
			if( isCompareSymbol(program.charAt(pos)) ){
				// se if have an equal after the char that we are
				if(program.charAt(pos+1) == '='){
					tokens.add(Classifier.classify(program.substring(pos, pos+2)));
					pos+=2;
				} else {
					tokens.add(Classifier.classify(String.valueOf(program.charAt(pos))));
					pos++;
				}
			
			} else if(program.charAt(pos) == '/'){
				// could it be an comment				
				if(program.charAt(pos+1) == '/'){
					String commentValue = "";
					while(program.charAt(pos) != ((char) 10) && program.charAt(pos) != ((char) 13)){
						commentValue += program.charAt(pos);
						pos++;
					}
					tokens.add(Classifier.classify(commentValue));
					pos++;
				}  else {
					tokens.add(Classifier.classify(""+program.charAt(pos)));
					pos++;
				}
			} else if(program.charAt(pos) == '\''){
				String charValue = "" + program.charAt(pos);
				pos++;
				while(program.charAt(pos) != '\''){
					charValue += program.charAt(pos);
					pos++;
				}
				charValue += program.charAt(pos);
				tokens.add(Classifier.classify(charValue));
				pos++;
			} else if(program.charAt(pos) == '\"') {
				String stringValue = "" + program.charAt(pos);
				pos++;
				while(program.charAt(pos) != '\"'){
					stringValue += program.charAt(pos);
					pos++;
				}
				stringValue += program.charAt(pos);
				tokens.add(Classifier.classify(stringValue));
				pos++;
			} else {
				// else identify the symbol
				tokens.add(Classifier.classify(String.valueOf(program.charAt(pos))));
				pos++;
			}			
		}
		for(Token t : tokens){
			System.out.print(t.getClass() + ": ");
			System.out.println(t.getToken());
		}
		return tokens;
	}

	private boolean isCompareSymbol(char c) {
		String symbols = "=<>";
		return symbols.indexOf(c) == -1 ? false : true;
	}

	private boolean isTokenizeSymbol(char c) {
		String symbols = "\"\',;(){}=<>!&|+-*/% " + ((char) 10) + ((char) 13) + ((char) 9);
		return symbols.indexOf(c) == -1 ? false : true;
	}

}
