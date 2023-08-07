package compiler.visitor;

import compiler.Token;
import compiler.analise_de_contexto.Type;

public class nodeExpressao extends nodeFator {
  public nodeExpressaoSimples expressaoSimples1;
  public nodeOperadorRelacional operadorRelacional;
  public nodeExpressaoSimples expressaoSimples2;
  public Token token;

  public nodeExpressao(Token token) {
    this.token = token;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeExpressao(this);
  }

  public Type getType(Visitor visitor) {
    return visitor.getType_nodeExpressao(this);
  }
}
