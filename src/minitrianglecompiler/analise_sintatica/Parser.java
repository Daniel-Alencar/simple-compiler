package minitrianglecompiler.analise_sintatica;
import java.util.ArrayList;

import minitrianglecompiler.Token;

public class Parser {
  private int currentTokenId;

  private int currentIndex;
  private ArrayList<Token> arrayOfTokens;

  public Parser(ArrayList<Token> arrayList) {
    this.currentIndex = 0;
    this.arrayOfTokens = arrayList;

    this.currentTokenId = this.arrayOfTokens.get(currentIndex).kind;
  }

  private void accept(int tokenId) {
    System.out.println("Current Token on Array: " + arrayOfTokens.get(currentIndex).spelling);
    
    if(tokenId == currentTokenId) {
      currentIndex++;
      if(this.arrayOfTokens.size() > currentIndex) {
        currentTokenId = this.arrayOfTokens.get(currentIndex).kind;
      }
    } else {
      showError("Not accepted\n");
    }
  }

  private void acceptIt() {
    System.out.println("Current Token on Array: " + arrayOfTokens.get(currentIndex).spelling);
    currentIndex++;
    if(this.arrayOfTokens.size() > currentIndex) {
      currentTokenId = this.arrayOfTokens.get(currentIndex).kind;
    }
  }

  private void showError(String message) {
    System.out.println(message);
  }

  private void showError() {
    System.out.println("Erro sintático");
  }

  // Regras da gramática LL1 como métodos
  private void parse_atribuicao() {
    parse_variavel();
    accept(Token.BECOMES);
    parse_expressao();
  }

  private void parse_comando() {
    switch(currentTokenId) {
      case Token.IDENTIFIER:
        parse_atribuicao();
        break;
      case Token.IF:
        parse_condicional();
        break;
      case Token.WHILE:
        parse_iterativo();
        break;
      case Token.BEGIN:
        parse_comandoComposto();
        break;
      default:
        showError("parse comando");
    }
  }

  private void parse_comandoComposto() {
    accept(Token.BEGIN);
    parse_listaDeComandos();
    accept(Token.END);
  }

  private void parse_condicional() {
    accept(Token.IF);
    parse_expressao();
    accept(Token.THEN);
    parse_comando();

    if(currentTokenId == Token.ELSE) {
      acceptIt();
      parse_comando();
    }
  }

  private void parse_corpo() {
    parse_declaracoes();
    parse_comandoComposto();
  }

  private void parse_declaracao() {
    parse_declaracaoDeVariavel();
  }

  private void parse_declaracaoDeVariavel() {
    accept(Token.VAR);
    parse_listaDeIds();
    accept(Token.COLON);
    parse_tipo();
  }

  private void parse_declaracoes() {
    while(currentTokenId == Token.VAR) {
      parse_declaracao();
      accept(Token.SEMICOLON);
    }
  }

  private void parse_expressao() {
    parse_expressaoSimples();
    if(currentTokenId == Token.RELATIONALOPERATOR) {
      accept(Token.RELATIONALOPERATOR);
      parse_expressao();
    }
  }

  private void parse_expressaoSimples() {
    parse_termo();
    while(currentTokenId == Token.ADITIONALOPERATOR) {
      accept(Token.ADITIONALOPERATOR);
      parse_termo();
    }
  }

  private void parse_fator() {
    switch(currentTokenId) {
      case Token.IDENTIFIER:
        accept(Token.IDENTIFIER);
        break;
      case Token.FLOATLITERAL:
      case Token.INTLITERAL:
      case Token.BOOLLITERAL:
        acceptIt();
        break;
      case Token.LPAREN:
        acceptIt();
        parse_expressao();
        accept(Token.RPAREN);
        break;

      default:
        showError("parse fator");
    }
  }

  private void parse_iterativo() {
    accept(Token.WHILE);
    parse_expressao();
    accept(Token.DO);
    parse_comando();
  }

  private void parse_listaDeComandos() {
    while(currentTokenId == Token.IDENTIFIER) {
      parse_comando();
      accept(Token.SEMICOLON);
    }
  }

  private void parse_listaDeIds() {
    accept(Token.IDENTIFIER);
    while(currentTokenId == Token.COMMA) {
      acceptIt();
      accept(Token.IDENTIFIER);
    }
  }

  private void parse_outros() {
    switch(currentTokenId) {
      case Token.EXCLAMATION:
      case Token.ARROBA:
      case Token.HASHTAG:
      case Token.ELLIPSIS:
        acceptIt();
      
      default:
        showError("parse outros");
    }
  }

  private void parse_programa() {
    accept(Token.PROGRAM);
    accept(Token.IDENTIFIER);
    accept(Token.SEMICOLON);
    parse_corpo();
    accept(Token.PERIOD);
  }

  private void parse_termo() {
    parse_fator();
    while(currentTokenId == Token.MULTIPLICATIONALOPERATOR) {
      acceptIt();
      parse_fator();
    }
  }

  private void parse_tipo() {
    accept(Token.TIPOSIMPLES);
  }

  private void parse_variavel() {
    accept(Token.IDENTIFIER);
  }

  public void parse() {
    parse_programa();
  }
}
