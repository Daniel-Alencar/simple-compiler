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
        
        private int currentLine;
        private int currentColumn;

	public Scanner(String pathToFile) {
                this.currentLine = 1;
                this.currentColumn = 1;
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

	public void getNextCharacter() {
		int character;
                
                if (currentChar == '\n') {
                    currentLine++;
                    currentColumn = 1;
                } else if (currentChar == '\t') {
                  currentColumn += 4; // Incrementar a coluna por 4, por exemplo
                 } else {
                  currentColumn++;
                }
                
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
            try {
              String currentDirectory = System.getProperty("user.dir");
              String filePath = currentDirectory + pathToFile;

              FileReader fileReader = new FileReader(filePath);
              int character;

              while ((character = fileReader.read()) != -1) {
                this.currentChar = (char) character;
                System.out.println("Caractere: " + this.currentChar);
              }

              fileReader.close();
            } catch (IOException e) {
              System.out.println(e.getMessage());
            }
        }


	private void take(char expectedChar) {
		if(currentChar == expectedChar) {
			currentSpelling.append(currentChar);
			// Next source character
			getNextCharacter();

		} else {
			// Talvez seja feito outra coisa nesse bloco
			// System.out.println("Erro léxico!");
			//return Token.Error;
		}
	}

	private void takeIt() {
		currentSpelling.append(currentChar);
		// Next source character
		getNextCharacter();
	}

	protected boolean isDigit(char character) {
		if(character >= '0' && character <= '9') {
			return true;
		}
		return false;
	}

	protected boolean isLetter(char character) {
		if(
				(character >= 'a' && character <= 'z') ||
				(character >= 'A' && character <= 'Z')
			) {
			return true;
		}
		return false;
	}

	protected boolean isOperator(char character) {
		switch(character) {
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

	protected boolean isGraphicCharacter(char character) {

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
			if(character >= minimumValues[i] && character <= maximumValues[i]) {
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

				while(isGraphicCharacter(currentChar)) {
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
		return new Token(currentKind, currentSpelling.toString(), currentLine, currentColumn);
	}

}
