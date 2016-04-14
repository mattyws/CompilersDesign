package bergCompiler;

import java.util.*;
import java.io.*;

/**
 * Test the LL parser for MiniJava
 * 
 * @author (sdb)
 * @version (Jan 2011)
 */
public class Driver {
	public static void main() throws IOException {

		File inFile = new File("program.txt");

		Scanner scanner = new Scanner(inFile);

		Parser parser = new Parser(scanner);

		Program prog = parser.Program();

		System.out.println("Compilation complete");

	}

}
