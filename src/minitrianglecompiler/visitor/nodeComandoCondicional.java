package minitrianglecompiler.visitor;

public class nodeComandoCondicional extends nodeComando {
  public nodeExpressao expressao;
  public nodeComando comando1;
  public nodeComando comando2;

  public void visit(Visitor visitor) {
    visitor.visit_nodeComandoCondicional(this);
  }
}
