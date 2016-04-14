package bottomUpParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Mattyws Ferreira, Lucas Gabriel, Vitor Finati
 *
 */
public class TableGenerator {

	/**
	 * Generate the bottom-up table using the list of state.
	 * 
	 * @param stateList
	 *            the DFA states
	 * @param nonTerminals
	 *            the list of non-terminals
	 * @return the bottom-up table
	 */
	public List<TableRow> generateTable(List<State> stateList, List<NonTerminal> nonTerminals) {
		System.out.println("Generating table");
		// mouting table TABLEEEEAGHEHNAGEHNAGEHGAEHAGEAHEGJHAGJEHAGEGJHE
		ArrayList<TableRow> table = new ArrayList<>();
		for (State s : stateList) {
			TableRow row = new TableRow();
			row.setState(s);
			for (String token : s.getTransictions().keySet()) {
				if (Helper.isNonTerminal(token, nonTerminals)) {
					if (!row.getCols().keySet().contains(token))
						row.addCol(token, "g" + s.getTransictions().get(token).getId());
				} else {
					if (!row.getCols().keySet().contains(token))
						row.addCol(token, "s" + s.getTransictions().get(token).getId());
				}
			}
			for (Expression stateExpression : s.getExpressions()) {
				if (stateExpression.getPositionItem().equals("$")) {
					row.addCol(stateExpression.getPositionItem(), "a");
				} else {
					if (stateExpression.getPositionItem().isEmpty()) {
						row.addCol(stateExpression.getLookAheadSym(), "r" + stateExpression.getRule().getId());
					}
				}
			}

			table.add(row);
		}
		// System.out.println(table.size());
		// for (TableRow t : table) {
		// System.out.println(t);
		// }
		generateCsvFile("tableBottomUp.csv", table);
		return table;
	}

	private void generateCsvFile(String fileName, ArrayList<TableRow> table) {
		try {
			Set<String> terminals = new HashSet<>();

			for (TableRow row : table) {
				for (String col : row.getCols().keySet()) {
					terminals.add(col);
				}
			}

			Map<String, List<String>> csv = new HashMap<>();

			for (String terminal : terminals) {
				csv.put(terminal, new ArrayList<>());
			}

			String auxCol = "";
			for (TableRow row : table) {
				for (String col : csv.keySet()) {
					if (auxCol.isEmpty()) {
						auxCol = col;
					}
					if (row.getCols().keySet().contains(col)) {
						csv.get(col).add(row.getCols().get(col));
					} else {
						csv.get(col).add("");
					}
				}
			}
			String content = "";
			for (String col : csv.keySet()) {
				col = "\"" + col + "\"";
				content += ", " + col;
			}
			content += "\n";

			for (int i = 0; i < csv.get(auxCol).size(); i++) {
				content += i;
				for (String col : csv.keySet()) {
					if(csv.get(col).get(i).isEmpty()){
						content += ",";
					} else{ 
						content += ", \"" + csv.get(col).get(i)+"\"";
					}
				}
				content += "\n";
			}

			System.out.println(content);

			File file = new File(fileName);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
