package compiler.visitor;

import compiler.analise_de_contexto.Type;

public class nodeTipoSimples {
  public String tipo;
  public Type tipoType; 

  public nodeTipoSimples(String tipo, Type tipoType) {
    this.tipo = tipo;
    this.tipoType = tipoType;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeTipoSimples(this);
  }
}
