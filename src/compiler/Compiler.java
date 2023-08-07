package compiler;

import java.util.ArrayList;

import compiler.analise_de_contexto.Checker;
import compiler.analise_lexica.Scanner;
import compiler.analise_sintatica.Parser;
import compiler.geracao_de_codigo.CodeGenerator;
import compiler.visitor.*;

/**
 *
 * @author
 */
public class Compiler {

	public static String fileName = "/test/code0.txt";
	public static int steps = 5;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(fileName);
		int counter = 0;
		ArrayList<Token> arrayOfTokens = new ArrayList<>();
		nodePrograma programaAST = null;

		if(steps >= 1) {
			// Análise léxica
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
						"Token \"" + token.spelling + "\" inválido" + "\n" + 
						"Linha: " + token.line + " Coluna: " + token.column
					);
				}
			}
		}

		if(steps >= 2) {
			// Análise Sintática
			Parser sintaticParser = new Parser(arrayOfTokens);
			programaAST = sintaticParser.parse();
		}

		if(steps >= 3) {
			// Printagem da AST (Árvore sintática abstrata)	
			System.out.println("");
			Printer printer = new Printer();
			printer.print(programaAST);
		}

		if(steps >= 4) {
			// Análise de contexto
			Checker checker = new Checker();
			checker.check(programaAST);
		}

		if(steps >= 5) {
			// Geração de código
			CodeGenerator codeGenerator = new CodeGenerator();
			codeGenerator.openFile("Object-code.txt");
			codeGenerator.printCode(programaAST);
			codeGenerator.closeFile();
		}
	}
}