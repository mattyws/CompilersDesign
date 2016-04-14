package bottomUpParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

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

public class Main {

	

	public static void main(String[] args) {
		Parser p = new Parser();
		p.parse("./digitCount.lmv", "bottomUpGrammar");
	}
}
