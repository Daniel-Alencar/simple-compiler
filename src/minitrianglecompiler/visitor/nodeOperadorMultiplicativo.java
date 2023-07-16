package minitrianglecompiler.visitor;

public class nodeOperadorMultiplicativo extends nodeOperador {

  public nodeOperadorMultiplicativo(String valor) {
    super(valor);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorMultiplicativo(this);
  }
}
