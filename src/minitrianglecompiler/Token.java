package minitrianglecompiler;

/**
 *
 * @author
 */
public class Token {
  
  public byte kind;
  public String spelling;
  public int line;
  public int column;

  public final static byte 
    IDENTIFIER = 0, INTLITERAL = 1, OPERATOR = 2,
    BEGIN = 3, CONST = 4, DO = 5, ELSE = 6, END = 7,
    IF = 8, IN = 9, LET = 10, THEN = 11, VAR = 12,
    WHILE = 13, SEMICOLON = 14, COLON = 15,
    BECOMES = 16, IS = 17, LPAREN = 18,
    RPAREN = 19, EOT = 20, ERROR = 21;

  private final static String[] spellings = {
    "<identifier>", "<integer-literal>", "<operator>",
    "begin", "const", "do", "else", "end", "if", "in", "let", "then",
    "var", "while", ";", ":", ":=", "~", "(", ")", "<eot>",
    "<error>"
  };

  public Token(byte kind, String spelling, int line, int column) {
    this.kind = kind;
    this.spelling = spelling;
    this.line = line;
    this.column = column;

    // If kind is IDENTIFIER and spelling matches one 
    // of the keybords, change the token's kind accordingly
    if(kind == IDENTIFIER) {
      
      for(int i = BEGIN; i <= WHILE; i++) {
        if(spelling.equals(spellings[i])) {
          this.kind = (byte)i;
          break;
        }
      }
    }
  }
}
