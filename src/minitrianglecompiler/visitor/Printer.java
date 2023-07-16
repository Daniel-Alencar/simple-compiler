package minitrianglecompiler.visitor;

import minitrianglecompiler.Token;

public class Printer implements Visitor {

  int indentacao = 0;
  int tabulacao = 2;

  String stringDeindentacao = "| ";

  void indent() {
    for (int j = 0; j < indentacao; j++)
      System.out.print(stringDeindentacao);
  }

  public void print(nodePrograma programa) {
    System.out.println("---> Iniciando impressão da árvore");
    programa.visit(this);
  }

  // Implementações de Printagem
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

  public void visit_nodeComandoAtribuicao(nodeComandoAtribuicao comando) {
    if (comando != null) {
      if (comando.variavel != null) {
        comando.variavel.ID.visit(this);
      }
      if (comando.expressao != null) {
        comando.expressao.visit(this);
      }
    }
  }

  public void visit_nodeComandoComposto(nodeComandoComposto comando) {
    if (comando != null) {
      if (comando.comandos != null) {
        for (int i = 0; i < comando.comandos.size(); i++) {
          comando.comandos.get(i).visit(this);
        }
      }
    }
  }

  public void visit_nodeComandoCondicional(nodeComandoCondicional comando) {
  }

  public void visit_nodeComandoIterativo(nodeComandoIterativo comando) {
  }

  public void visit_nodeCorpo(nodeCorpo corpo) {
    if (corpo != null) {
      if (corpo.declaracoes != null) {
        this.indent();
        System.out.println("Declaracoes:");
        indentacao += tabulacao;
        corpo.declaracoes.visit(this);
        indentacao -= tabulacao;
      }
      if (corpo.comandoComposto != null) {
        this.indent();
        System.out.println("Comando composto:");
        indentacao += tabulacao;
        corpo.comandoComposto.visit(this);
        indentacao -= tabulacao;
      }
    }
  }

  public void visit_nodeDeclaracao(nodeDeclaracao declaracao) {
    if (declaracao != null) {
      if (declaracao.declaracaoDeVariavel != null) {
        this.indent();
        System.out.println("Declaracao:");
        declaracao.declaracaoDeVariavel.visit(this);
      }
    }
  }

  public void visit_nodeDeclaracaoDeVariavel(nodeDeclaracaoDeVariavel declaracao) {
    if (declaracao != null) {
      this.indent();
      System.out.println("Declaracao de Variavel:");
      indentacao += tabulacao;
      if (declaracao.IDs != null) {
        for (int i = 0; i < declaracao.IDs.size(); i++) {
          declaracao.IDs.get(i).visit(this);
        }
      }
      if (declaracao.tipo != null) {
        this.indent();
        System.out.println("Tipo:");
        declaracao.tipo.visit(this);
      }
      indentacao -= tabulacao;
    }
  }

  public void visit_nodeDeclaracoes(nodeDeclaracoes declaracoes) {
    if (declaracoes != null) {
      if (declaracoes.declaracoes != null) {
        indentacao += tabulacao;
        this.indent();
        System.out.println("Declarações:");
        for (int i = 0; i < declaracoes.declaracoes.size(); i++) {
          this.indent();
          System.out.println("Declaração " + (i + 1) + ":");
          declaracoes.declaracoes.get(i).visit(this);
        }
        indentacao -= tabulacao;
      }
    }
  }

  public void visit_nodeExpressao(nodeExpressao expressao) {
    if (expressao != null) {
      if (expressao.expressaoSimples1 != null) {
        this.indent();
        System.out.println("Expressão Simples 1:");
        expressao.expressaoSimples1.visit(this);
      }
      if (expressao.operadorRelacional != null) {
        this.indent();
        System.out.println("Operador Relacional:");
        expressao.operadorRelacional.visit(this);
      }
      if (expressao.expressaoSimples2 != null) {
        this.indent();
        System.out.println("Expressão Simples 2:");
        expressao.expressaoSimples2.visit(this);
      }
    }
  }

  public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressaoSimples) {
    if (expressaoSimples != null) {
      if (expressaoSimples.termo != null) {
        this.indent();
        System.out.println("Termo:");
        expressaoSimples.termo.visit(this);
      }
      if (expressaoSimples.operadoresAditivos != null && expressaoSimples.termos != null) {
        for (int i = 0; i < expressaoSimples.operadoresAditivos.size(); i++) {
          this.indent();
          System.out.println("Operador Aditivo:");
          expressaoSimples.operadoresAditivos.get(i).visit(this);

          this.indent();
          System.out.println("Termo:");
          expressaoSimples.termos.get(i).visit(this);
        }
      }
    }
  }

  public void visit_nodeFator(nodeFator fator) {
    if (fator != null) {
      if (fator instanceof nodeVariavel) {
        this.indent();
        System.out.println("Variável:");
        ((nodeVariavel) fator).visit(this);
      } else if (fator instanceof nodeLiteral) {
        this.indent();
        System.out.println("Literal:");
        ((nodeLiteral) fator).visit(this);
      } else if (fator instanceof nodeExpressao) {
        this.indent();
        System.out.println("Expressão:");
        ((nodeExpressao) fator).visit(this);
      }
    }
  }

  public void visit_nodeID(nodeID ID) {
    if (ID != null) {
      this.indent();
      System.out.println(ID.valor);
    }
  }

  public void visit_nodeLiteral(nodeLiteral literal) {
    if (literal != null) {
      this.indent();
      System.out.println(literal.valor);
    }
  }

  public void visit_nodeOperador(nodeOperador operador) {
    if (operador != null) {
      indentacao += tabulacao;
      this.indent();
      System.out.println(operador.valor);
      indentacao -= tabulacao;
    }
  }

  public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador) {
    if (operador != null) {
      indentacao += tabulacao;
      this.indent();
      System.out.println(operador.valor);
      indentacao -= tabulacao;
    }
  }

  public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador) {
    if (operador != null) {
      indentacao += tabulacao;
      this.indent();
      System.out.println(operador.valor);
      indentacao -= tabulacao;
    }
  }

  public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador) {
    if (operador != null) {
      indentacao += tabulacao;
      this.indent();
      System.out.println(operador.valor);
      indentacao -= tabulacao;
    }
  }

  public void visit_nodePrograma(nodePrograma programa) {
    if (programa != null) {
      this.indent();
      System.out.println("Programa:");

      if (programa.id != null) {
        this.indent();
        System.out.print("ID: ");
        programa.id.visit(this);
      }
      indentacao += tabulacao;

      if (programa.corpo != null) {
        this.indent();
        System.out.println("Corpo:");
        programa.corpo.visit(this);
      }

      indentacao -= tabulacao;
    }
  }

  public void visit_nodeTermo(nodeTermo termo) {
    indentacao += tabulacao;
    if (termo != null) {
      if (termo.fator != null) {
        termo.fator.visit(this);
      }
      if (termo.operadoresMultiplicativos != null && termo.fatores != null) {
        for (int i = 0; i < termo.operadoresMultiplicativos.size(); i++) {
          termo.operadoresMultiplicativos.get(i).visit(this);
          termo.fatores.get(i).visit(this);
        }
      }
    }
    indentacao -= tabulacao;
  }

  public void visit_nodeTipo(nodeTipo tipo) {
    if (tipo != null) {
      if (tipo.tipoSimples != null) {
        tipo.tipoSimples.visit(this);
      }
    }
  }

  public void visit_nodeTipoSimples(nodeTipoSimples tipoSimples) {
    indentacao += tabulacao;
    this.indent();
    System.out.println(tipoSimples.tipo);
    indentacao -= tabulacao;
  }

  public void visit_nodeVariavel(nodeVariavel variavel) {
    if (variavel != null) {
      variavel.ID.visit(this);
    }
  }

}
