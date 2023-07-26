package minitrianglecompiler.visitor;

import minitrianglecompiler.analise_de_contexto.Type;

public class nodeExpressao extends nodeFator {
  public nodeExpressaoSimples expressaoSimples1;
  public nodeOperadorRelacional operadorRelacional;
  public nodeExpressaoSimples expressaoSimples2;

  public void visit(Visitor visitor) {
    visitor.visit_nodeExpressao(this);
  }

  public Type getType(Visitor visitor) {
    return visitor.getType_nodeExpressao(this);
  }
}
