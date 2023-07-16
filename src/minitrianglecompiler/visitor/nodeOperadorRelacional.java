package minitrianglecompiler.visitor;

public class nodeOperadorRelacional extends nodeOperador {

  public nodeOperadorRelacional(String symbol) {
    super(symbol);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorRelacional(this);
  }
}
