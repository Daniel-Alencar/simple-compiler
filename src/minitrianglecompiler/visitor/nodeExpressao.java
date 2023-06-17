package minitrianglecompiler.visitor;

public class nodeExpressao extends nodeFator {
  public nodeExpressaoSimples expressaoSimples1;
  public nodeOperadorRelacional operadorRelacional;
  public nodeExpressaoSimples expressaoSimples2;

  public void visit(Visitor visitor) {
    visitor.visit_nodeExpressao(this);
  }
}
