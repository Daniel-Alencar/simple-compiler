package minitrianglecompiler.visitor;

import minitrianglecompiler.analise_de_contexto.Type;

public class nodeLiteral extends nodeFator {
  public String valor;
  public Type tipo;

  public nodeLiteral(String valor, Type tipo) {
    this.valor = valor;
    this.tipo = tipo;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeLiteral(this);
  }
}
