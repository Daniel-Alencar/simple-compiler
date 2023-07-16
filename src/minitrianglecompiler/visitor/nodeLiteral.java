package minitrianglecompiler.visitor;

public class nodeLiteral extends nodeFator {
  String valor;

  public nodeLiteral(String valor) {
    this.valor = valor;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeLiteral(this);
  }
}
