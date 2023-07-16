package minitrianglecompiler.visitor;

public class nodeLiteral extends nodeFator {

  public String symbol;

  public nodeLiteral(String symbol) {
    this.symbol = symbol;
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeLiteral(this);
  }
}
