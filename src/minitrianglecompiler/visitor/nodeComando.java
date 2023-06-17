package minitrianglecompiler.visitor;

public abstract class nodeComando {
  public void visit(Visitor visitor) {
    visitor.visit_nodeComando(this);
  }
}
