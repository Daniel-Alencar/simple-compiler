package minitrianglecompiler;

import java.util.ArrayList;

import minitrianglecompiler.analise_de_contexto.Checker;
import minitrianglecompiler.analise_lexica.Scanner;
import minitrianglecompiler.analise_sintatica.Parser;
import minitrianglecompiler.geracao_de_codigo.CodeGenerator;
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

				counter++;
			} while (scanner.isEOF() == false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		for(int i = 0; i < arrayOfTokens.size(); i++ ) {
			if(arrayOfTokens.get(i).kind == Token.ERROR) {
				Token token = arrayOfTokens.get(i);
				new ShowError(
					"Token \"" + token.spelling + "\" inválido\n" + 
					"Linha: " + token.line + " Coluna: " + token.column
				);
			}
		}

		Parser sintaticParser = new Parser(arrayOfTokens);
		nodePrograma programaAST = sintaticParser.parse();

		System.out.println("");
		Printer printer = new Printer();
		printer.print(programaAST);

		Checker checker = new Checker();
		checker.check(programaAST);

		CodeGenerator codeGenerator = new CodeGenerator();
		codeGenerator.printCode(programaAST);
	}
}