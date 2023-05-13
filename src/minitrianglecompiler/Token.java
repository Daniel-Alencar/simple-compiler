package minitrianglecompiler;

/**
 *
 * @author
 */
public class Token {
  
  public byte kind;
  public String spelling;

  public final static byte 
    IDENTIFIER = 0,
    BOOLLITERAL = 1, INTLITERAL = 2, FLOATLITERAL = 3,

    BEGIN = 4, IF = 5, THEN = 6, ELSE = 7, VAR = 8, WHILE = 9, DO = 10, END = 11,
    BECOMES = 12, LPAREN = 13, RPAREN = 14, COLON = 15, SEMICOLON = 16,
    COMMA = 17, PERIOD = 18,

    EXCLAMATION = 19, ARROBA = 20, HASHTAG = 21, ELLIPSIS = 22,

    OPERATOR = 23, TIPOSIMPLES = 24,
    EOT = 25, ERROR = 26;

  private final static String[] spellings = {
    "<identifier>",
    "<bool-literal>", "<int-literal>", "<float-literal>",

    "begin", "if", "then", "else", "var", "while", "do", "end",
    ":=", "(", ")", ":", ";",
    ",", ".",

    "!", "@", "#", "...",

    "<operator>", "<tipo-simples>",
    "<eot>", "<error>"
  };

  public Token(byte kind, String spelling) {
    this.kind = kind;
    this.spelling = spelling;

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
