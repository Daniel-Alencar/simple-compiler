package compiler.visitor;

import compiler.Token;

public class nodeComandoAtribuicao extends nodeComando {
  public nodeVariavel variavel;
  public nodeExpressao expressao;

  public nodeDeclaracaoDeVariavel declaracaoDeVariavel;

  public nodeComandoAtribuicao(Token token) {
    super(token);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeComandoAtribuicao(this);
  }
}
