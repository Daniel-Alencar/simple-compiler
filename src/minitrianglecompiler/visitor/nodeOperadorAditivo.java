package minitrianglecompiler.visitor;

public class nodeOperadorAditivo extends nodeOperador {
  public nodeOperadorAditivo(String valor) {
    super(valor);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorAditivo(this);
  }
}
