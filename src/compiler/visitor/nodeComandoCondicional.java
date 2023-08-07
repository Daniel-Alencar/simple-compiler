package compiler.visitor;

import compiler.Token;

public class nodeComandoCondicional extends nodeComando {
  public nodeExpressao expressao;
  public nodeComando comando1;
  public nodeComando comando2;

  public nodeComandoCondicional(Token token) {
    super(token);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeComandoCondicional(this);
  }
}
