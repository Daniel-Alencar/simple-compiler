package compiler.visitor;
import java.util.ArrayList;

public class nodeTermo {
  public nodeFator fator;

  public ArrayList<nodeOperadorMultiplicativo> operadoresMultiplicativos;
  public ArrayList<nodeFator> fatores;

  public void visit(Visitor visitor) {
    visitor.visit_nodeTermo(this);
  }
}
