package minitrianglecompiler.visitor;

public class nodeTipo {
  public nodeTipoSimples tipoSimples;

  public void visit(Visitor visitor) {
    visitor.visit_nodeTipo(this);
  }
}
