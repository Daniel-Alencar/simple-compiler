package minitrianglecompiler.analise_sintatica;

import java.util.ArrayList;

import minitrianglecompiler.ShowError;
import minitrianglecompiler.Token;
import minitrianglecompiler.analise_de_contexto.Type;
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

    if (tokenId == currentTokenId) {
      currentIndex++;
      if (this.arrayOfTokens.size() > currentIndex) {
        currentTokenId = this.arrayOfTokens.get(currentIndex).kind;
      }
    } else {
      new ShowError(
        "Símbolo não aceito: " + arrayOfTokens.get(currentIndex).spelling + "\n"
        + "Linha: " + arrayOfTokens.get(currentIndex).line
        + " Coluna: " + arrayOfTokens.get(currentIndex).column
      );
    }
  }

  private void acceptIt() {

    currentIndex++;
    if (this.arrayOfTokens.size() > currentIndex) {
      currentTokenId = this.arrayOfTokens.get(currentIndex).kind;
    }
  }

  // Regras da gramática LL1 como métodos
  private nodeComandoAtribuicao parse_atribuicao() {
    nodeComandoAtribuicao comandoAtribuicao = new nodeComandoAtribuicao(this.arrayOfTokens.get(currentIndex));

    comandoAtribuicao.variavel = parse_variavel();
    accept(Token.BECOMES);
    comandoAtribuicao.expressao = parse_expressao();
    accept(Token.SEMICOLON);

    return comandoAtribuicao;
  }

  private nodeComando parse_comando() {
    nodeComando comando;

    switch (currentTokenId) {
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
        new ShowError(
          "Comando que começa com \"" + 
          arrayOfTokens.get(currentIndex).spelling + 
          "\" não identificado" 
          + "Linha: " + arrayOfTokens.get(currentIndex).line 
      + " Coluna: " + arrayOfTokens.get(currentIndex).column
        );
        comando = null;
    }
    return comando;
  }

  private nodeComandoComposto parse_comandoComposto() {
    nodeComandoComposto comandoComposto = new nodeComandoComposto(
      arrayOfTokens.get(currentIndex)
    );

    accept(Token.BEGIN);
    comandoComposto.comandos = parse_listaDeComandos();
    accept(Token.END);

    return comandoComposto;
  }

  private nodeComandoCondicional parse_condicional() {
    nodeComandoCondicional condicional = new nodeComandoCondicional(this.arrayOfTokens.get(currentIndex));

    accept(Token.IF);
    condicional.expressao = parse_expressao();
    accept(Token.THEN);
    condicional.comando1 = parse_comando();

    condicional.comando2 = null;

    if (currentTokenId == Token.ELSE) {
      acceptIt();
      condicional.comando2 = parse_comando();
    }
    return condicional;
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

    while (currentTokenId == Token.VAR) {
      declaracoes.declaracoes.add(parse_declaracao());
      accept(Token.SEMICOLON);
    }

    return declaracoes;
  }

  private nodeExpressao parse_expressao() {
    nodeExpressao expressao = new nodeExpressao(this.arrayOfTokens.get(currentIndex));

    expressao.expressaoSimples1 = parse_expressaoSimples();
    expressao.operadorRelacional = null;
    expressao.expressaoSimples2 = null;

    if (currentTokenId == Token.RELATIONALOPERATOR) {

      nodeOperadorRelacional operadorRelacional = new nodeOperadorRelacional(
        arrayOfTokens.get(currentIndex).spelling,
        Token.RELATIONALOPERATOR
      );
      expressao.operadorRelacional = operadorRelacional;

      acceptIt();

      expressao.expressaoSimples2 = parse_expressaoSimples();
    }

    return expressao;
  }

  private nodeExpressaoSimples parse_expressaoSimples() {
    nodeExpressaoSimples expressaoSimples = new nodeExpressaoSimples();
    expressaoSimples.termo = parse_termo();
    expressaoSimples.operadoresAditivos = new ArrayList<nodeOperadorAditivo>();
    expressaoSimples.termos = new ArrayList<nodeTermo>();

    while (currentTokenId == Token.ADITIONALOPERATOR) {
      nodeOperadorAditivo operadorAditivo = new nodeOperadorAditivo(
        arrayOfTokens.get(currentIndex).spelling,
        Token.ADITIONALOPERATOR
      );
      expressaoSimples.operadoresAditivos.add(operadorAditivo);

      acceptIt();

      expressaoSimples.termos.add(parse_termo());
    }

    return expressaoSimples;
  }

  private nodeFator parse_fator() {
    nodeFator fator = null;
    Type tipo;
    nodeLiteral aux2;
    
    switch (currentTokenId) {
      case Token.IDENTIFIER:
        nodeVariavel aux1 = new nodeVariavel(
          new nodeID(
            this.arrayOfTokens.get(currentIndex).spelling,
            this.arrayOfTokens.get(currentIndex)
          ),
          this.arrayOfTokens.get(currentIndex)
        );
        fator = aux1;

        acceptIt();
        break;

      case Token.FLOATLITERAL:
        tipo = new Type(Type.REAL);
        aux2 = new nodeLiteral(
          arrayOfTokens.get(currentIndex).spelling, tipo
        );
        fator = aux2;

        acceptIt();
        break;

      case Token.INTLITERAL:
        tipo = new Type(Type.INT);
        aux2 = new nodeLiteral(
          arrayOfTokens.get(currentIndex).spelling, tipo
        );
        fator = aux2;

        acceptIt();
        break;
        
      case Token.BOOLLITERAL:
        tipo = new Type(Type.BOOL);
        aux2 = new nodeLiteral(
          arrayOfTokens.get(currentIndex).spelling, tipo
        );
        fator = aux2;

        acceptIt();
        break;

      case Token.LPAREN:
        acceptIt();

        nodeExpressao expressao = new nodeExpressao(this.arrayOfTokens.get(currentIndex));
        expressao = parse_expressao();

        accept(Token.RPAREN);

        fator = expressao;
        break;

      default:
        new ShowError("Erro no fator: " + arrayOfTokens.get(currentIndex).spelling + "Não identificado" 
        + "Linha: " + arrayOfTokens.get(currentIndex).line  + "\""
      + " Coluna: " + arrayOfTokens.get(currentIndex).column);
    }
    return fator;
  }

  private nodeComandoIterativo parse_iterativo() {
    nodeComandoIterativo comandoIterativo = new nodeComandoIterativo(
      arrayOfTokens.get(currentIndex)
    );

    accept(Token.WHILE);
    comandoIterativo.expressao = parse_expressao();
    accept(Token.DO);
    comandoIterativo.comando = parse_comando();

    return comandoIterativo;
  }

  private ArrayList<nodeComando> parse_listaDeComandos() {
    ArrayList<nodeComando> comandos = new ArrayList<>();

    while (
      currentTokenId == Token.IDENTIFIER ||
      currentTokenId == Token.IF ||
      currentTokenId == Token.WHILE ||
      currentTokenId == Token.BEGIN
    ) {
      comandos.add(parse_comando());
    }

    return comandos;
  }

  private ArrayList<nodeID> parse_listaDeIds() {
    ArrayList<nodeID> IDs = new ArrayList<>();

    nodeID ID_aux1 = new nodeID(
      this.arrayOfTokens.get(currentIndex).spelling,
      this.arrayOfTokens.get(currentIndex)
    );
    ID_aux1.valor = arrayOfTokens.get(currentIndex).spelling;
    IDs.add(ID_aux1);
    
    accept(Token.IDENTIFIER);

    while (currentTokenId == Token.COMMA) {
      acceptIt();

      nodeID ID_aux2 = new nodeID(
        this.arrayOfTokens.get(currentIndex).spelling, 
        this.arrayOfTokens.get(currentIndex)
      );
      ID_aux2.valor = arrayOfTokens.get(currentIndex).spelling;
      IDs.add(ID_aux2);

      accept(Token.IDENTIFIER);
    }

    return IDs;
  }

  private void parse_outros() {
    switch (currentTokenId) {
      case Token.EXCLAMATION:
      case Token.ARROBA:
      case Token.HASHTAG:
      case Token.ELLIPSIS:
        acceptIt();
        break;
      default:
        new ShowError("Símbolo não aceito: " + arrayOfTokens.get(currentIndex).spelling
        + "Linha: " + arrayOfTokens.get(currentIndex).line + "\""
      + " Coluna: " + arrayOfTokens.get(currentIndex).column);
    }
  }

  private nodePrograma parse_programa() {
    nodePrograma programaAST = new nodePrograma();

    accept(Token.PROGRAM);
    programaAST.id = new nodeID(
      this.arrayOfTokens.get(currentIndex).spelling,
      this.arrayOfTokens.get(currentIndex)
    );
    programaAST.id.valor = arrayOfTokens.get(currentIndex).spelling;
    accept(Token.IDENTIFIER);

    accept(Token.SEMICOLON);
    programaAST.corpo = parse_corpo();
    accept(Token.PERIOD);

    return programaAST;
  }

  private nodeTermo parse_termo() {
    nodeTermo termo = new nodeTermo();
    termo.fatores = new ArrayList<nodeFator>();
    termo.operadoresMultiplicativos = new ArrayList<nodeOperadorMultiplicativo>();

    termo.fator = parse_fator();
    while (currentTokenId == Token.MULTIPLICATIONALOPERATOR) {
      nodeOperadorMultiplicativo operadorMultiplicativo = 
      new nodeOperadorMultiplicativo(
        arrayOfTokens.get(currentIndex).spelling,
        Token.MULTIPLICATIONALOPERATOR
      );
      termo.operadoresMultiplicativos.add(operadorMultiplicativo);

      acceptIt();

      termo.fatores.add(parse_fator());
    }
    return termo;
  }

  private nodeTipo parse_tipo() {
    nodeTipo tipo = new nodeTipo();

    String tipoString = arrayOfTokens.get(currentIndex).spelling;
    Type tipoType = Type.evaluateString(tipoString);
    tipo.tipoSimples = new nodeTipoSimples(tipoString, tipoType);

    accept(Token.TIPOSIMPLES);
    return tipo;
  }

  private nodeVariavel parse_variavel() {
    
    nodeID ID = new nodeID(
      this.arrayOfTokens.get(currentIndex).spelling,
      this.arrayOfTokens.get(currentIndex)
    );
    ID.valor = arrayOfTokens.get(currentIndex).spelling;

    nodeVariavel variavel = new nodeVariavel(
      ID, arrayOfTokens.get(currentIndex)
    );

    accept(Token.IDENTIFIER);

    return variavel;
  }

  public nodePrograma parse() {
    return parse_programa();
  }
}