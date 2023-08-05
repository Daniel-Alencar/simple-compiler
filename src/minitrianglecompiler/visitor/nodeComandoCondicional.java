package minitrianglecompiler.visitor;

import minitrianglecompiler.Token;

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
