package minitrianglecompiler.visitor;

public class nodeLiteral extends nodeFator {
  public void visit(Visitor visitor) {
    visitor.visit_nodeLiteral(this);
  }
}
