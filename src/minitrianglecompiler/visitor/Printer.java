package minitrianglecompiler.visitor;

public class Printer implements Visitor {

  int i = 0;
  int tabulacao = 2;

  void indent() {
    for (int j = 0; j < i; j++)
      System.out.print("|");
  }

  public void print(nodePrograma programa) {
    System.out.println("---> Iniciando impressão da árvore");
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
      if(corpo.declaracoes != null) {
        corpo.declaracoes.visit(this);
      }
      if(corpo.comandoComposto != null) {
        i += tabulacao;
        this.indent();
        corpo.comandoComposto.visit(this);
        i -= tabulacao;
      }
    }
  }
  
  public void visit_nodeDeclaracao(nodeDeclaracao declaracao) {
    if(declaracao != null) {
      if(declaracao.declaracaoDeVariavel != null) {
        i += tabulacao;
        this.indent();
        declaracao.declaracaoDeVariavel.visit(this);
        i -= tabulacao;
      }
    }
  }
  public void visit_nodeDeclaracaoDeVariavel(nodeDeclaracaoDeVariavel declaracao) {
    if(declaracao != null) {
      if(declaracao.IDs != null) {
        for(int i = 0; i < declaracao.IDs.size(); i++) {
          declaracao.IDs.get(i).visit(this);
        }
      }
      if(declaracao.tipo != null) {
        declaracao.tipo.visit(this);
      }
    }
  }
  public void visit_nodeDeclaracoes(nodeDeclaracoes declaracoes) {
    if(declaracoes != null) {
      if(declaracoes.declaracoes != null) {
        for(int i = 0; i < declaracoes.declaracoes.size(); i++) {
          declaracoes.declaracoes.get(i).visit(this);
        }
      }
    }
  }
  public void visit_nodeExpressao(nodeExpressao expressao) {}
  public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressaoSimples) {}
  public void visit_nodeFator(nodeFator fator) {}
  
  public void visit_nodeID(nodeID ID) {
    if(ID != null) {
      i += tabulacao;
      this.indent();
      System.out.println(ID.valor);
      i -= tabulacao;
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
  public void visit_nodeTipo(nodeTipo tipo) {
    if(tipo != null) {
      if(tipo.tipoSimples != null) {
        tipo.tipoSimples.visit(this);
      }
    }
  }
  public void visit_nodeTipoSimples(nodeTipoSimples tipoSimples) {
    i += tabulacao;
    this.indent();
    System.out.println(tipoSimples.tipo);
    i -= tabulacao;
  }
  public void visit_nodeVariavel(nodeVariavel variavel) {}

}
