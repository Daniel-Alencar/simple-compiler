package minitrianglecompiler.visitor;

import minitrianglecompiler.Token;

public abstract class nodeComando {
  public Token token;

  public nodeComando(Token token) {
    this.token = token;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeComando(this);
  }
}
