package minitrianglecompiler.visitor;

public abstract class nodeOperador {
  public String symbol;

  public nodeOperador(String symbol) {
    this.symbol = symbol;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperador(this);
  }
}
