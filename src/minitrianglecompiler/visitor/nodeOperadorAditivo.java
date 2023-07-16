package minitrianglecompiler.visitor;

public class nodeOperadorAditivo extends nodeOperador {

  public nodeOperadorAditivo(String symbol) {
    super(symbol);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorAditivo(this);
  }
}
