package minitrianglecompiler;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author
 */
public class Scanner {

	// First source character
	private char currentChar;
	private FileReader fileReader;

	private byte currentKind;
	private StringBuffer currentSpelling;

	public Scanner(String pathToFile) {
		try {
			String currentDirectory = System.getProperty("user.dir");

			FileReader fileReader = new FileReader(
				currentDirectory + pathToFile
			);
			int character;
			// Lê o arquivo caractere por caractere
			if((character = fileReader.read()) != -1) {
				this.currentChar = (char)character;
				System.out.println("Caractere: " + this.currentChar);
			}
			this.fileReader = fileReader;

		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void getNextCaracter() {
		int character;

		try {
			if((character = fileReader.read()) != -1) {
				this.currentChar = (char)character;
				System.out.println("Caractere: " + this.currentChar);
			}
		}	catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void readFile(String pathToFile) {
		// Cria um objeto FileReader que representa o arquivo que queremos ler
		try {
			String currentDirectory = System.getProperty("user.dir");
      System.out.println("O diretório atual é: " + currentDirectory);

			FileReader fileReader = new FileReader(
				currentDirectory + pathToFile
			);
			int character;
			// Lê o arquivo caractere por caractere
			while ((character = fileReader.read()) != -1) {
				System.out.print((char) character);
			}
			System.out.println();

			fileReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void take(char expectedChar) {
		if(currentChar == expectedChar) {
			currentSpelling.append(currentChar);
			// Next source character
			getNextCaracter();

		} else {
			// Talvez seja feito outra coisa nesse bloco
			System.out.println("Erro léxico!");
		}
	}

	private void takeIt() {
		currentSpelling.append(currentChar);
		// Next source character
		getNextCaracter();
	}

	protected boolean isDigit(char caracter) {
		if(caracter >= '0' && caracter <= '9') {
			return true;
		}
		return false;
	}

	protected boolean isLetter(char caracter) {
		if(
				(caracter >= 'a' && caracter <= 'z') ||
				(caracter >= 'A' && caracter <= 'Z')
			) {
			return true;
		}
		return false;
	}

	protected boolean isOperator(char caracter) {
		switch(caracter) {
			case '+':
			case '-':
			case '*':
			case '/':
			case '<':
			case '>':
			case '=':
			case '\\':
				return true;
			default:
				return false;
		}
	}

	protected boolean isGraphicCaracter(char caracter) {

		/*
		32 a 47: inclui espaço em branco, ponto, vírgula, ponto e vírgula, etc.
		58 a 64: inclui dois pontos, ponto de interrogação, arroba, etc.
		91 a 96: inclui colchetes, contrabarra inversa, acento grave, etc.
		123 a 126: inclui chaves, til, etc.
		128 a 159: inclui setas, blocos de desenho e símbolos matemáticos.
		*/
		int minimumValues[] = { 32, 58, 91, 123, 128 };
		int maximumValues[] = { 47, 64, 96, 126, 159 };
		
		for(int i = 0; i < 5; i++) {
			if(caracter >= minimumValues[i] && caracter <= maximumValues[i]) {
				return true;
			}
		}
		return false;
	}

	private byte scanToken() {

		if(isLetter(currentChar)) {
			takeIt();
			while(isLetter(currentChar) || isDigit(currentChar)) {
				takeIt();
			}
			return Token.IDENTIFIER;

		}
		
		if(isDigit(currentChar)) {
			takeIt();
			while(isDigit(currentChar)) {
				takeIt();
			}
			return Token.INTLITERAL;
		}

		if(isOperator(currentChar)) {
			takeIt();
			return Token.OPERATOR;
		}

		if(currentChar == ';') {
			takeIt();
			return Token.SEMICOLON;
		}

		if(currentChar == ':') {
			takeIt();
			if(currentChar == '=') {
				takeIt();
				return Token.BECOMES;
			} else {
				return Token.COLON;
			}
		}

		if(currentChar == '~') {
			takeIt();
			return Token.IS;
		}

		if(currentChar == '(') {
			takeIt();
			return Token.LPAREN;
		}

		if(currentChar == ')') {
			takeIt();
			return Token.RPAREN;
		}

		if(currentChar == '\000') {
			return Token.EOT;
		}

		return Token.ERROR;
	}

	private void scanSeparator() {
		switch(currentChar) {
			case '!':
				takeIt();

				while(isGraphicCaracter(currentChar)) {
					takeIt();
				}
				take('\n');
				break;

			case ' ':
			case '\n':
				takeIt();
				break;
		}
	}

	public Token scan() {
		while(currentChar == '!' || currentChar == ' ' || currentChar == '\n') {
			scanSeparator();
		}
		currentSpelling = new StringBuffer("");
		currentKind = scanToken();

		return new Token(currentKind, currentSpelling.toString());
	}

}
