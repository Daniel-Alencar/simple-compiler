package minitrianglecompiler.visitor;

import minitrianglecompiler.Token;

public class nodeComandoIterativo extends nodeComando {
  public nodeExpressao expressao;
  public nodeComando comando;

  public nodeComandoIterativo(Token token) {
    super(token);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeComandoIterativo(this);
  }
}
