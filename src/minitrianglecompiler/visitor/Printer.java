package minitrianglecompiler.visitor;

public class Printer implements Visitor {

  int i = 0;
  int tabulacao = 2;

  void indent() {
    for (int j = 0; j < i; j++)
      System.out.print("|");
  }

  public void print(nodePrograma programa) {
    System.out.println("---> Iniciando impressao da arvore");
    programa.visit(this);
  }

  // Implementações de Printagem
  public void visit_nodeComando(nodeComando comando) {}
  public void visit_nodeComandoAtribuicao(nodeComandoAtribuicao comando) {}
  public void visit_nodeComandoComposto(nodeComandoComposto comando) {}
  public void visit_nodeComandoCondicional(nodeComandoCondicional comando) {}
  public void visit_nodeComandoIterativo(nodeComandoIterativo comando) {}
  
  public void visit_nodeCorpo(nodeCorpo corpo) {
    if(corpo != null) {
      i += tabulacao;
      this.indent();
      if(corpo.declaracoes != null) {
        System.out.println("Declarações");
      }
      this.indent();
      if(corpo.comandoComposto != null) {
        System.out.println("Comando composto");
      }
    }
  }
  
  public void visit_nodeDeclaracao(nodeDeclaracao declaracao) {}
  public void visit_nodeDeclaracaoDeVariavel(nodeDeclaracaoDeVariavel declaracao) {}
  public void visit_nodeDeclaracoes(nodeDeclaracoes declaracoes) {}
  public void visit_nodeExpressao(nodeExpressao expressao) {}
  public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressaoSimples) {}
  public void visit_nodeFator(nodeFator fator) {}
  
  public void visit_nodeID(nodeID ID) {
    if(ID != null) {
      System.out.println("ID");
    }
  }

  public void visit_nodeLiteral(nodeLiteral literal) {}
  public void visit_nodeOperador(nodeOperador operador) {}
  public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador) {}
  public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador) {}
  public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador) {}
  
  public void visit_nodePrograma(nodePrograma programa) {
    if(programa != null) {
      if(programa.id != null) {
        programa.id.visit(this);
      }
      if(programa.corpo != null) {
        programa.corpo.visit(this);
      }
    }
  }
  
  public void visit_nodeTermo(nodeTermo termo) {}
  public void visit_nodeTipo(nodeTipo tipo) {}
  public void visit_nodeTipoSimples(nodeTipoSimples tipoSimples) {}
  public void visit_nodeVariavel(nodeVariavel variavel) {}

}
