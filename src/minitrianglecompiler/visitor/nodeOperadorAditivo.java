package minitrianglecompiler.visitor;

public class nodeOperadorAditivo extends nodeOperador {
  public void visit(Visitor visitor) {
    visitor.visit_nodeOperadorAditivo(this);
  }
}
