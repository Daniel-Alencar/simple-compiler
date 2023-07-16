package minitrianglecompiler.visitor;

public abstract class nodeOperador {
  public int operador;

  String valor;

  public nodeOperador(String valor) {
    this.valor = valor;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperador(this);
  }
}
