package compiler.visitor;

public class nodeDeclaracao {
  public nodeDeclaracaoDeVariavel declaracaoDeVariavel;

  public void visit(Visitor visitor) {
    visitor.visit_nodeDeclaracao(this);
  }
}
