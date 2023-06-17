package minitrianglecompiler.visitor;

public class nodeVariavel extends nodeFator {
  public nodeID ID;

  public void visit(Visitor visitor) {
    visitor.visit_nodeVariavel(this);
  }
}
