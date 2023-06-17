package minitrianglecompiler.visitor;

public abstract class nodeOperador {
  public int operador;

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperador(this);
  }
}
