package minitrianglecompiler;

import java.util.ArrayList;

import minitrianglecompiler.analise_de_contexto.Checker;
import minitrianglecompiler.analise_lexica.Scanner;
import minitrianglecompiler.analise_sintatica.Parser;

import minitrianglecompiler.visitor.*;

/**
 *
 * @author
 */
public class MiniTriangleCompiler {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner("/test/test2.txt");
		int counter = 0;
		ArrayList<Token> arrayOfTokens = new ArrayList<>();

		try {
			do {
				Token currentToken = scanner.scan();
				arrayOfTokens.add(counter, currentToken);

				System.out.println("TOKEN:");
				System.out.println("speeling:" + currentToken.spelling);
				System.out.println("kind:" + currentToken.kind);
				System.out.println("line:" + currentToken.line);
				System.out.println("column:" + currentToken.column);

				System.out.println();

				counter++;
			} while (scanner.isEOF() == false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Length of array: " + arrayOfTokens.size() + "\n");

		Parser sintaticParser = new Parser(arrayOfTokens);
		nodePrograma programaAST = sintaticParser.parse();

		System.out.println("");
		Printer printer = new Printer();
		printer.print(programaAST);

		Checker checker = new Checker();
		checker.check(programaAST);
		checker.identificationTable.printTable();
	}
}