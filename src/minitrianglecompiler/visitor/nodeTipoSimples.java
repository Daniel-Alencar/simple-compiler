package minitrianglecompiler.visitor;

public class nodeTipoSimples {
  public String tipo;

  public void visit(Visitor visitor) {
    visitor.visit_nodeTipoSimples(this);
  }
}
