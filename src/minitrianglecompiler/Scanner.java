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

	private boolean isLetter(char caracter) {
		return true;
	}

	private boolean isDigit(char caracter) {
		return true;
	}

	private boolean isOperator(char caracter) {
		return true;
	}

	private boolean isGraphicCaracter(char caracter) {
		return true;
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
