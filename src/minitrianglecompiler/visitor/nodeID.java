package minitrianglecompiler.visitor;

public class nodeID {

  public nodeID(String valor) {
    this.valor = valor;
  }

  public String valor;

  public void visit(Visitor visitor) {
    visitor.visit_nodeID(this);
  }
}
