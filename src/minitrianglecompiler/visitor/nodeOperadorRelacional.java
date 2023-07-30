package minitrianglecompiler.visitor;

public class nodeOperadorRelacional extends nodeOperador {

  public nodeOperadorRelacional(String valor, byte tokenDeOperador) {
    super(valor, tokenDeOperador);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorRelacional(this);
  }
}
