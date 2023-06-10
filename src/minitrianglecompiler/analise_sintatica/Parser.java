package minitrianglecompiler.analise_sintatica;
import java.util.ArrayList;

import minitrianglecompiler.Token;
import minitrianglecompiler.visitor.*;

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
  private nodeComandoAtribuicao parse_atribuicao() {
    nodeComandoAtribuicao comandoAtribuicao = new nodeComandoAtribuicao();

    comandoAtribuicao.variavel = parse_variavel();
    accept(Token.BECOMES);
    comandoAtribuicao.expressao = parse_expressao();

    return comandoAtribuicao;
  }

  private nodeComando parse_comando() {
    nodeComando comando;

    switch(currentTokenId) {
      case Token.IDENTIFIER:
        comando = parse_atribuicao();
        break;
      case Token.IF:
        comando = parse_condicional();
        break;
      case Token.WHILE:
        comando = parse_iterativo();
        break;
      case Token.BEGIN:
        comando = parse_comandoComposto();
        break;
      default:
        comando = null;
        showError("parse comando");
    }
    return comando;
  }

  private nodeComandoComposto parse_comandoComposto() {
    nodeComandoComposto comandoComposto = new nodeComandoComposto();

    accept(Token.BEGIN);
    comandoComposto.comandos = parse_listaDeComandos();
    accept(Token.END);

    return comandoComposto;
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

  private nodeCorpo parse_corpo() {
    nodeCorpo corpo = new nodeCorpo();

    corpo.declaracoes = parse_declaracoes();
    corpo.comandoComposto = parse_comandoComposto();

    return corpo;
  }

  private nodeDeclaracao parse_declaracao() {
    nodeDeclaracao declaracao = new nodeDeclaracao();
    declaracao.declaracaoDeVariavel = parse_declaracaoDeVariavel();

    return declaracao;
  }

  private nodeDeclaracaoDeVariavel parse_declaracaoDeVariavel() {
    nodeDeclaracaoDeVariavel declaracaoDeVariavel = new nodeDeclaracaoDeVariavel();

    accept(Token.VAR);
    declaracaoDeVariavel.IDs = parse_listaDeIds();
    accept(Token.COLON);
    declaracaoDeVariavel.tipo = parse_tipo();

    return declaracaoDeVariavel;
  }

  private nodeDeclaracoes parse_declaracoes() {
    nodeDeclaracoes declaracoes = new nodeDeclaracoes();
    declaracoes.declaracoes = new ArrayList<>();
    
    while(currentTokenId == Token.VAR) {
      declaracoes.declaracoes.add(parse_declaracao());
      accept(Token.SEMICOLON);
    }

    return declaracoes;
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

  private ArrayList<nodeComando> parse_listaDeComandos() {
    ArrayList<nodeComando> comandos = new ArrayList<>();

    while(currentTokenId == Token.IDENTIFIER) {
      comandos.add(parse_comando());
      accept(Token.SEMICOLON);
    }
    
    return comandos;
  }

  private ArrayList<nodeID> parse_listaDeIds() {
    ArrayList<nodeID> IDs = new ArrayList<>();

    accept(Token.IDENTIFIER);
    while(currentTokenId == Token.COMMA) {
      acceptIt();
      accept(Token.IDENTIFIER);

      IDs.add(new nodeID());
    }

    return IDs;
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

  private nodePrograma parse_programa() {
    nodePrograma programaAST = new nodePrograma();

    accept(Token.PROGRAM);
    accept(Token.IDENTIFIER);
    accept(Token.SEMICOLON);
    programaAST.corpo = parse_corpo();
    accept(Token.PERIOD);

    return programaAST;
  }

  private void parse_termo() {
    parse_fator();
    while(currentTokenId == Token.MULTIPLICATIONALOPERATOR) {
      acceptIt();
      parse_fator();
    }
  }

  private nodeTipo parse_tipo() {
    nodeTipo tipo = new nodeTipo();
    accept(Token.TIPOSIMPLES);

    return tipo;
  }

  private nodeVariavel parse_variavel() {
    nodeVariavel variavel = new nodeVariavel();
    variavel.ID = new nodeID();

    accept(Token.IDENTIFIER);

    return variavel;
  }

  public void parse() {
    parse_programa();
  }
}
