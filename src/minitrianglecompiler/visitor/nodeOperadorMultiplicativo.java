package minitrianglecompiler.visitor;

public class nodeOperadorMultiplicativo extends nodeOperador {

  public nodeOperadorMultiplicativo(String valor, byte tokenDeOperador) {
    super(valor, tokenDeOperador);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorMultiplicativo(this);
  }
}