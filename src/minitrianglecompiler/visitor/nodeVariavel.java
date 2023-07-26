package minitrianglecompiler.visitor;

import minitrianglecompiler.analise_de_contexto.Type;

public class nodeVariavel extends nodeFator {
  public nodeID ID;
  public Type tipo;

  public nodeVariavel() {}

  public void visit(Visitor visitor) {
    visitor.visit_nodeVariavel(this);
  }
}
