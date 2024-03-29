package compiler.geracao_de_codigo;

import java.util.HashMap;
import java.util.Map;

import compiler.analise_de_contexto.Type;
import compiler.visitor.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeGenerator implements Visitor {
  public int currentTargetCounter;
  private Map<String, String> variavelEnderecoMap = new HashMap<>();
  private Map<String, String> variavelTipoMap = new HashMap<>();
  private int currentAddressCounter = 0;
  private int tabulacao = 2;

  private BufferedWriter bufferedWriter;

  public CodeGenerator() {
    this.currentTargetCounter = 0;
  }

  public void openFile(String Filename) {
    try {
      // Cria um objeto FileWriter para o arquivo especificado
      FileWriter fileWriter = new FileWriter(Filename);
      // Cria um objeto BufferedWriter para escrever no arquivo
      this.bufferedWriter = new BufferedWriter(fileWriter);
    } catch (IOException e) {
      System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
    }
  }

  public void closeFile() {
    try {
      bufferedWriter.close();
    } catch (IOException e) {
      System.err.println("Ocorreu um erro ao fechar o arquivo: " + e.getMessage());
    }
  }

  public void writeCodeLine(String line) {
    try {
      for(int i = 0; i < tabulacao; i++) {
        this.bufferedWriter.write(" ");
      }
      this.bufferedWriter.write(line);
      this.bufferedWriter.newLine();

    } catch (IOException e) {
      System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
    }
  }

  public void printCodeLine(String line) {
    for(int i = 0; i < tabulacao; i++) {
      System.out.print(" ");
    }
    System.out.println(line);
  }

  public void generateCodeLine(String line) {
    printCodeLine(line);
    writeCodeLine(line);
  }

  public void generateEmptyLine() {
    System.out.println();
    try {
      this.bufferedWriter.newLine();
    } catch (IOException e) {
      System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
    }
  }

  public void generateLabel(String label) {
    System.out.println(label);
    try {
      this.bufferedWriter.write(label);
      this.bufferedWriter.newLine();

    } catch (IOException e) {
      System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
    }
  }

  private String getTipoVariavel(String variavel) {
    return variavelEnderecoMap.get(variavel);
  }

  private void freeMemory() {
    generateEmptyLine();

    for (String variavelNome : variavelEnderecoMap.keySet()) {
      int tamanhoVariavel = 0;
      String tipo = variavelTipoMap.get(variavelNome);

      if (tipo.equals("" + Type.BOOL)) {
        tamanhoVariavel = 1;
      } else if (tipo.equals("" + Type.INT)) {
        tamanhoVariavel = 2;
      } else {
        tamanhoVariavel = 4;
      }
      generateCodeLine("POP " + tamanhoVariavel);
    }
  }

  private String getEnderecoVariavel(String variavel) {
    return variavelEnderecoMap.get(variavel);
  }

  public String getNextLabel() {

    this.currentTargetCounter++;

    return "E_" + this.currentTargetCounter;
  }

  public String convertOperadorToString(String operador) {
    if (operador.equals("+")) {
      return "ADD";
    } else if (operador.equals("-")) {
      return "SUB";
    } else if (operador.equals("or")) {
      return "OR";
    } else if (operador.equals("*")) {
      return "MULT";
    } else if (operador.equals("/")) {
      return "DIV";
    } else if (operador.equals("and")) {
      return "AND";
    } else if (operador.equals(">")) {
      return "GT";
    } else if (operador.equals("<")) {
      return "LT";
    } else if (operador.equals(">=")) {
      return "GET";
    } else if (operador.equals("<=")) {
      return "LET";
    } else if (operador.equals("=")) {
      return "EQ";
    } else if (operador.equals("<>")) {
      return "NEQ";
    }
    return "(UNKNOWN)";
  }

  // Analisando a árvore
  public void printCode(nodePrograma programa) {
    System.out.println("---> Geração de código:");
    programa.visit(this);
  }

  @Override
  public void visit_nodeComando(nodeComando comando) {
    if (comando != null) {
      if (comando instanceof nodeComandoAtribuicao) {
        ((nodeComandoAtribuicao) comando).visit(this);
      } else if (comando instanceof nodeComandoCondicional) {
        ((nodeComandoCondicional) comando).visit(this);
      } else if (comando instanceof nodeComandoIterativo) {
        ((nodeComandoIterativo) comando).visit(this);
      } else if (comando instanceof nodeComandoComposto) {
        ((nodeComandoComposto) comando).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeComandoAtribuicao(nodeComandoAtribuicao comando) {
    if (comando != null) {
      if (comando.expressao != null) {
        comando.expressao.visit(this);
      }
      if (comando.variavel != null) {
        // comando.variavel.visit(this);
        String variavelNome = ((nodeVariavel) comando.variavel).ID.valor;
        String endereco = getEnderecoVariavel(variavelNome);
        generateCodeLine("STORE " + endereco + "[SB]");
      }
    }
  }

  @Override
  public void visit_nodeComandoComposto(nodeComandoComposto comando) {
    if (comando != null) {
      for (int i = 0; i < comando.comandos.size(); i++) {
        generateEmptyLine();
        comando.comandos.get(i).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeComandoCondicional(nodeComandoCondicional comandoCondicional) {
    String ifLabel = getNextLabel();
    String elseLabel = getNextLabel();
    String endLabel = getNextLabel();

    generateLabel(ifLabel + ":");
    comandoCondicional.expressao.visit(this);
    generateCodeLine("JUMPIF (0) " + elseLabel);
    
    comandoCondicional.comando1.visit(this);
    generateCodeLine("JUMP " + endLabel);

    generateLabel(elseLabel + ":");
    if (comandoCondicional.comando2 != null) {
      comandoCondicional.comando2.visit(this);
    }

    generateLabel(endLabel + ":");
  }

  @Override
  public void visit_nodeComandoIterativo(nodeComandoIterativo comando) {
    String loopLabel = this.getNextLabel();
    String endLabel = this.getNextLabel();

    generateLabel(loopLabel + ":");
    comando.expressao.visit(this);
    // JUMPIF
    generateCodeLine("JUMPIF (0) " + endLabel);
    comando.comando.visit(this);

    // LOOP
    generateCodeLine("JUMP " + loopLabel);

    generateLabel(endLabel + ":");

  }

  @Override
  public void visit_nodeCorpo(nodeCorpo corpo) {
    if (corpo != null) {
      if (corpo.declaracoes != null) {
        corpo.declaracoes.visit(this);
      }
      if (corpo.comandoComposto != null) {
        corpo.comandoComposto.visit(this);
      }
    }
  }

  @Override
  public void visit_nodeDeclaracao(nodeDeclaracao declaracao) {
    if (declaracao != null) {
      if (declaracao.declaracaoDeVariavel != null) {
        declaracao.declaracaoDeVariavel.visit(this);
      }
    }
  }

  @Override
  public void visit_nodeDeclaracaoDeVariavel(nodeDeclaracaoDeVariavel declaracao) {

    // CONSIDERANDO INT = 2; BOOL = 1; REAL = 4;
    byte tipoVariavel = declaracao.tipo.tipoSimples.tipoType.kind;
    for (int i = 0; i < declaracao.IDs.size(); i++) {
      String variavelNome = declaracao.IDs.get(i).valor;
      int tamanhoVariavel = 0;
      if (tipoVariavel == Type.BOOL) {
        tamanhoVariavel = 1;
      } else if (tipoVariavel == Type.INT) {
        tamanhoVariavel = 2;
      } else if (tipoVariavel == Type.REAL) {
        tamanhoVariavel = 4;
      }
      generateCodeLine("PUSH " + tamanhoVariavel);
      variavelTipoMap.put(variavelNome, "" + tipoVariavel);
      variavelEnderecoMap.put(variavelNome, "" + this.currentAddressCounter);
      this.currentAddressCounter += tamanhoVariavel;

    }
  }

  @Override
  public void visit_nodeDeclaracoes(nodeDeclaracoes declaracoes) {
    if (declaracoes != null) {
      for (int i = 0; i < declaracoes.declaracoes.size(); i++) {
        declaracoes.declaracoes.get(i).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeExpressao(nodeExpressao expressao) {

    if (expressao != null) {
      if (expressao.expressaoSimples1 != null) {
        expressao.expressaoSimples1.visit(this);
      }
      if (expressao.expressaoSimples2 != null) {
        expressao.expressaoSimples2.visit(this);
      }
      if (expressao.operadorRelacional != null) {
        expressao.operadorRelacional.visit(this);
      }
    }
  }

  @Override
  public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressao) {
    if (expressao != null) {
      if (expressao.termo != null) {
        expressao.termo.visit(this);
      }
      for (int i = 0; i < expressao.operadoresAditivos.size(); i++) {
        expressao.termos.get(i).visit(this);
        expressao.operadoresAditivos.get(i).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeFator(nodeFator fator) {

  }

  @Override
  public void visit_nodeID(nodeID ID) {

  }

  @Override
  public void visit_nodeLiteral(nodeLiteral literal) {
    if (literal != null) {
      generateCodeLine("LOADL " + literal.valor);
    }
  }

  @Override
  public void visit_nodeOperador(nodeOperador operador) {
    if (operador != null) {
      generateCodeLine("CALL " + convertOperadorToString(operador.valor));
    }
  }

  @Override
  public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador) {
    if (operador != null) {
      this.visit_nodeOperador(operador);
    }
  }

  @Override
  public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador) {
    if (operador != null) {
      this.visit_nodeOperador(operador);
    }
  }

  @Override
  public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador) {
    if (operador != null) {
      this.visit_nodeOperador(operador);
    }
  }

  @Override
  public void visit_nodePrograma(nodePrograma programa) {
    if (programa != null) {
      programa.corpo.visit(this);
      freeMemory();
      generateCodeLine("HALT");
    }
  }

  @Override
  public void visit_nodeTermo(nodeTermo termo) {
    if (termo != null) {
      if (termo.fator != null) {
        termo.fator.visit(this);
      }
      for (int i = 0; i < termo.operadoresMultiplicativos.size(); i++) {
        termo.fatores.get(i).visit(this);
        termo.operadoresMultiplicativos.get(i).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeTipo(nodeTipo tipo) {

  }

  @Override
  public void visit_nodeTipoSimples(nodeTipoSimples tipoSimples) {

  }

  @Override
  public void visit_nodeVariavel(nodeVariavel variavel) {
    if (variavel != null) {
      if (variavel.ID != null) {
        String endereco = getEnderecoVariavel(variavel.ID.valor);
        generateCodeLine("LOAD " + endereco + "[SB]");
      }
    }
  }

  @Override
  public Type getType_nodeExpressao(nodeExpressao expressao) {
    return null;
  }

  @Override
  public Type getType_nodeExpressaoSimples(nodeExpressaoSimples expressao) {
    return null;
  }

  @Override
  public Type getType_nodeFator(nodeFator fator) {
    return null;
  }

  @Override
  public Type getType_nodeLiteral(nodeLiteral literal) {
    return null;
  }

  @Override
  public Type getType_nodeTermo(nodeTermo termo) {
    return null;
  }

  @Override
  public Type getType_nodeVariavel(nodeVariavel variavel) {
    return null;
  }
}
