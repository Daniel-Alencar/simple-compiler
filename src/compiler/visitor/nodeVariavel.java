package compiler.visitor;

import compiler.Token;
import compiler.analise_de_contexto.Type;

public class nodeVariavel extends nodeFator {
  public nodeID ID;
  public Type tipo;
  public Token token;

  public nodeVariavel(nodeID ID, Token token) {
    this.ID = ID;
    this.token = token;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeVariavel(this);
  }
}
