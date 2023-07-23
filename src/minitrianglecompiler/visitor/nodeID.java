package minitrianglecompiler.visitor;

public class nodeID {
  public String valor;

  public void visit(Visitor visitor) {
    visitor.visit_nodeID(this);
  }
}