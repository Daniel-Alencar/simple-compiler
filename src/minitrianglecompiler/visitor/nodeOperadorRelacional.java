package minitrianglecompiler.visitor;

public class nodeOperadorRelacional extends nodeOperador {

  public nodeOperadorRelacional(String valor) {
    super(valor);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorRelacional(this);
  }
}
