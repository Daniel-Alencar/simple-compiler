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
    IDENTIFIER = 0,
    BOOLLITERAL = 1, INTLITERAL = 2, FLOATLITERAL = 3,

    BEGIN = 4, IF = 5, THEN = 6, ELSE = 7, VAR = 8, WHILE = 9, DO = 10, PROGRAM = 11, END = 12,
    BECOMES = 13, LPAREN = 14, RPAREN = 15, COLON = 16, SEMICOLON = 17,
    COMMA = 18, PERIOD = 19,

    EXCLAMATION = 20, ARROBA = 21, HASHTAG = 22, ELLIPSIS = 23,

    RELATIONALOPERATOR = 24, ADITIONALOPERATOR = 25, MULTIPLICATIONALOPERATOR = 26, TIPOSIMPLES = 27,
    EOT = 28, ERROR = 29;

  private final static String[] spellings = {
    "<identifier>",
    "<bool-literal>", "<int-literal>", "<float-literal>",

    "begin", "if", "then", "else", "var", "while", "do","program", "end",
    ":=", "(", ")", ":", ";",
    ",", ".",

    "!", "@", "#", "...",

    "<op-rel>", "<op-ad>", "<op-mul>", "<tipo-simples>",
    "<eot>", "<error>"
  };

  public Token(byte kind, String spelling, int line, int column) {
    this.kind = kind;
    this.spelling = spelling;
    this.line = line;
    this.column = column;

    // Se kind for reconhecido como IDENTIFIER,
    // devemos verificar se é uma das palavras reservadas.
    // Se for, o kind deve ser alterado apropriadamente. 
    if(kind == IDENTIFIER) {
      for(byte i = BEGIN; i <= END; i++) {
        if(spelling.equals(spellings[i])) {
          this.kind = i;
          break;
        }
      }
    }
  }
}