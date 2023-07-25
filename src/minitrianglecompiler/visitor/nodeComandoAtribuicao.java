package minitrianglecompiler.visitor;

public class nodeComandoAtribuicao extends nodeComando {
  public nodeVariavel variavel;
  public nodeExpressao expressao;

  public nodeDeclaracaoDeVariavel declaracaoDeVariavel;

  public void visit(Visitor visitor) {
    visitor.visit_nodeComandoAtribuicao(this);
  }
}
