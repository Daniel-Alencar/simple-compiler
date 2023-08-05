package minitrianglecompiler.visitor;

import minitrianglecompiler.Token;

public class nodeID {
  public String valor;
  public Token token;

  public nodeID(String valor, Token token) {
    this.valor = valor;
    this.token = token;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeID(this);
  }
}