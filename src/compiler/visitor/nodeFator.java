package compiler.visitor;

public abstract class nodeFator {
  public void visit(Visitor visitor) {
    visitor.visit_nodeFator(this);
  }
}
