package compiler.visitor;

public class nodeOperadorAditivo extends nodeOperador {
  public nodeOperadorAditivo(String valor, byte tokenDeOperador) {
    super(valor, tokenDeOperador);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorAditivo(this);
  }
}
