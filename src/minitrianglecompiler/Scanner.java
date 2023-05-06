package minitrianglecompiler;

/**
 *
 * @author
 */
public class Scanner {

	// First source character
	private char currentChar = ' ';

	private byte currentKind;
	private StringBuffer currentSpelling;

	private void take(char expectedChar) {
		if(currentChar == expectedChar) {
			if(currentChar == expectedChar) {
				currentSpelling.append(currentChar);

				// Next source character
				currentChar = ' ';

			} else {
				// Report a lexical error
			}
		}
	}

	private void takeIt() {
		currentSpelling.append(currentChar);
		// Next source character
		currentChar = ' ';
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
