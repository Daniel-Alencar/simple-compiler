package minitrianglecompiler.visitor;

public class nodeComandoIterativo extends nodeComando {
  public nodeExpressao expressao;
  public nodeComando comando;

  public void visit(Visitor visitor) {
    visitor.visit_nodeComandoIterativo(this);
  }
}
