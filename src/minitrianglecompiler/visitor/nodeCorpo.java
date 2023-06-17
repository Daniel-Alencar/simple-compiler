package minitrianglecompiler.visitor;

public class nodeCorpo {
  public nodeDeclaracoes declaracoes;
  public nodeComandoComposto comandoComposto;

  public void visit(Visitor visitor) {
    visitor.visit_nodeCorpo(this);
  }
}
