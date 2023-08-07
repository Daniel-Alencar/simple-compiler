package compiler.visitor;
import java.util.ArrayList;

public class nodeDeclaracoes {
  public ArrayList<nodeDeclaracao> declaracoes;

  public void visit(Visitor visitor) {
    visitor.visit_nodeDeclaracoes(this);
  }
}
