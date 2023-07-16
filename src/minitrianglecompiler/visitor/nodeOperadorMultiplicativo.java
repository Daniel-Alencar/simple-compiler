package minitrianglecompiler.visitor;

public class nodeOperadorMultiplicativo extends nodeOperador {

  public nodeOperadorMultiplicativo(String symbol) {
    super(symbol);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorMultiplicativo(this);
  }
}
