package minitrianglecompiler.visitor;
import java.util.ArrayList;

public class nodeExpressaoSimples {
  public nodeTermo termo;

  public ArrayList<nodeOperadorAditivo> operadoresAditivos;
  public ArrayList<nodeTermo> termos;

  public void visit(Visitor visitor) {
    visitor.visit_nodeExpressaoSimples(this);
  }
}
