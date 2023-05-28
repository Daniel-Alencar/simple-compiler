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

    BEGIN = 4, IF = 5, THEN = 6, ELSE = 7, VAR = 8, WHILE = 9, DO = 10, END = 11,
    BECOMES = 12, LPAREN = 13, RPAREN = 14, COLON = 15, SEMICOLON = 16,
    COMMA = 17, PERIOD = 18,

    EXCLAMATION = 19, ARROBA = 20, HASHTAG = 21, ELLIPSIS = 22,

    RELATIONALOPERATOR = 23, ADITIONALOPERATOR = 24, MULTIPLICATIONALOPERATOR = 25, TIPOSIMPLES = 26,
    EOT = 27, ERROR = 28;

  private final static String[] spellings = {
    "<identifier>",
    "<bool-literal>", "<int-literal>", "<float-literal>",

    "begin", "if", "then", "else", "var", "while", "do", "end",
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