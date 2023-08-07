package compiler.visitor;

public class nodePrograma {
  public nodeID id;
  public nodeCorpo corpo;

  public void visit(Visitor visitor) {
    visitor.visit_nodePrograma(this);
  }
}