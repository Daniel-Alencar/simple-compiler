package compiler.visitor;
import java.util.ArrayList;

import compiler.analise_de_contexto.Type;

public class nodeExpressaoSimples {
  public nodeTermo termo;

  public ArrayList<nodeOperadorAditivo> operadoresAditivos;
  public ArrayList<nodeTermo> termos;

  public void visit(Visitor visitor) {
    visitor.visit_nodeExpressaoSimples(this);
  }

  public Type getType(Visitor visitor) {
    return visitor.getType_nodeExpressaoSimples(this);
  }
}
