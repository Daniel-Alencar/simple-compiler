package compiler.visitor;

public abstract class nodeOperador {
  public byte operador;
  public String valor;

  public nodeOperador(String valor, byte tokenDeOperador) {
    this.valor = valor;
    this.operador = tokenDeOperador;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeOperador(this);
  }
}