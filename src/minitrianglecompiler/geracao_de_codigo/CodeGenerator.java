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

  // Analisando a árvore
  public void printCode(nodePrograma programa) {
    System.out.println("---> Geração de código:");
    programa.visit(this);
  }

  @Override
  public void visit_nodeComando(nodeComando comando) {
    
  }

  @Override
  public void visit_nodeComandoAtribuicao(nodeComandoAtribuicao comando) {
    
  }

  @Override
  public void visit_nodeComandoComposto(nodeComandoComposto comando) {
    
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
    
  }

  @Override
  public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressao) {
    
  }

  @Override
  public void visit_nodeFator(nodeFator fator) {
    
  }

  @Override
  public void visit_nodeID(nodeID ID) {
    
  }

  @Override
  public void visit_nodeLiteral(nodeLiteral literal) {
    
  }

  @Override
  public void visit_nodeOperador(nodeOperador operador) {
    
  }

  @Override
  public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador) {
    
  }

  @Override
  public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador) {
    
  }

  @Override
  public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador) {
    
  }

  @Override
  public void visit_nodePrograma(nodePrograma programa) {
    if(programa != null) {
      programa.corpo.visit(this);
    }
  }

  @Override
  public void visit_nodeTermo(nodeTermo termo) {
    
  }

  @Override
  public void visit_nodeTipo(nodeTipo tipo) {
    
  }

  @Override
  public void visit_nodeTipoSimples(nodeTipoSimples tipoSimples) {
    
  }

  @Override
  public void visit_nodeVariavel(nodeVariavel variavel) {
    
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
