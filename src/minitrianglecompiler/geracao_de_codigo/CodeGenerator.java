package minitrianglecompiler.geracao_de_codigo;

import minitrianglecompiler.analise_de_contexto.Type;
import minitrianglecompiler.visitor.*;

public class CodeGenerator implements Visitor {
  public int currentTargetCounter;
  
  public CodeGenerator() {
    this.currentTargetCounter = 0;
  }
  
  public void template_comandoCondicional(nodeComandoCondicional comandoCondicional) {
    this.evaluate(comandoCondicional.expressao);
    this.printNextTarget();
    this.printNextTarget();
  }

  public void evaluate(nodeExpressao expressao) {
    
  }

  public void printNextTarget() {
    System.out.println("E_" + this.currentTargetCounter + ":");
  }

  public String convertOperadorToString(String operador) {
    if(operador.equals("+")) {
      return "ADD";
    } else if(operador.equals("-")) {
      return "SUB";
    } else if(operador.equals("or")) {
      return "OR";
    } else if(operador.equals("*")) {
      return "MULT";
    } else if(operador.equals("/")) {
      return "DIV";
    } else if(operador.equals("and")) {
      return "AND";
    } else if(operador.equals(">")) {
      return "GT";
    } else if(operador.equals("<")) {
      return "LT";
    } else if(operador.equals(">=")) {
      return "GET";
    } else if(operador.equals("<=")) {
      return "LET";
    } else if(operador.equals("=")) {
      return "EQ";
    } else if(operador.equals("<>")) {
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
    if(comando != null) {
      if(comando.expressao != null) {
        comando.expressao.visit(this);
      }
      if(comando.variavel != null) {
        System.out.print("STORE ");
        comando.variavel.visit(this);
      }
    }
  }

  @Override
  public void visit_nodeComandoComposto(nodeComandoComposto comando) {
    if(comando != null) {
      for(int i = 0; i < comando.comandos.size(); i++) {
        System.out.println();
        System.out.println("// Comando " + i);
        comando.comandos.get(i).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeComandoCondicional(nodeComandoCondicional comando) {
    
  }

  @Override
  public void visit_nodeComandoIterativo(nodeComandoIterativo comando) {
    
  }

  @Override
  public void visit_nodeCorpo(nodeCorpo corpo) {
    if(corpo != null) {
      if(corpo.declaracoes != null) {
        corpo.declaracoes.visit(this);
      }
      if(corpo.comandoComposto != null) {
        corpo.comandoComposto.visit(this);
      }
    }
  }

  @Override
  public void visit_nodeDeclaracao(nodeDeclaracao declaracao) {
    if(declaracao != null) {
      if(declaracao.declaracaoDeVariavel != null) {
        declaracao.declaracaoDeVariavel.visit(this);
      }
    }
  }

  @Override
  public void visit_nodeDeclaracaoDeVariavel(nodeDeclaracaoDeVariavel declaracao) {
    if(declaracao != null) {
      for(int i = 0; i < declaracao.IDs.size(); i++) {
        System.out.println("PUSH " + declaracao.IDs.get(i).valor);
      }
    }
  }

  @Override
  public void visit_nodeDeclaracoes(nodeDeclaracoes declaracoes) {
    if(declaracoes != null) {
      for(int i = 0; i < declaracoes.declaracoes.size(); i++) {
        declaracoes.declaracoes.get(i).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeExpressao(nodeExpressao expressao) {
    if(expressao != null) {
      if(expressao.expressaoSimples1 != null) {
        expressao.expressaoSimples1.visit(this);
      }
      if(expressao.expressaoSimples2 != null) {
        expressao.expressaoSimples2.visit(this);
      }
      if(expressao.operadorRelacional != null) {
        expressao.operadorRelacional.visit(this);
      }
    }
  }

  @Override
  public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressao) {
    if(expressao != null) {
      if(expressao.termo != null) {
        expressao.termo.visit(this);
      }
      for(int i = 0; i < expressao.operadoresAditivos.size(); i++) {
        expressao.termos.get(i).visit(this);
        expressao.operadoresAditivos.get(i).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeFator(nodeFator fator) {
    if(fator != null) {
      if (fator instanceof nodeVariavel) {
        System.out.print("LOAD ");
        ((nodeVariavel) fator).visit(this);
        
      } else if (fator instanceof nodeLiteral) {
        System.out.print("LOADL ");
        ((nodeLiteral) fator).visit(this);
        
      } else if (fator instanceof nodeExpressao) {
        ((nodeExpressao) fator).visit(this);
      }
    }
  }

  @Override
  public void visit_nodeID(nodeID ID) {
    
  }

  @Override
  public void visit_nodeLiteral(nodeLiteral literal) {
    if(literal != null) {
      System.out.println("LOADL " + literal.valor);
    }
  }

  @Override
  public void visit_nodeOperador(nodeOperador operador) {
    if(operador != null) {
      System.out.println("CALL " + convertOperadorToString(operador.valor));
    }
  }

  @Override
  public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador) {
    if(operador != null) {
      this.visit_nodeOperador(operador);
    }
  }

  @Override
  public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador) {
    if(operador != null) {
      this.visit_nodeOperador(operador);
    }
  }

  @Override
  public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador) {
    if(operador != null) {
      this.visit_nodeOperador(operador);
    }
  }

  @Override
  public void visit_nodePrograma(nodePrograma programa) {
    if(programa != null) {
      programa.corpo.visit(this);
    }
  }

  @Override
  public void visit_nodeTermo(nodeTermo termo) {
    if(termo != null) {
      if(termo.fator != null) {
        termo.fator.visit(this);
      }
      for(int i = 0; i < termo.operadoresMultiplicativos.size(); i++) {
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
    if(variavel != null) {
      if(variavel.ID != null) {
        System.out.println(variavel.ID.valor);
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
