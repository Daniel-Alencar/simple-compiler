package minitrianglecompiler.visitor;

public class nodeOperadorRelacional extends nodeOperador {
  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorRelacional(this);
  }
}
