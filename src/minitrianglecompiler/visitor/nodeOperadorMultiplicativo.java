package minitrianglecompiler.visitor;

public class nodeOperadorMultiplicativo extends nodeOperador {
  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorMultiplicativo(this);
  }
}
