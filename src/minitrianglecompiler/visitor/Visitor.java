package minitrianglecompiler.visitor;

public interface Visitor {
  public void visit_nodePrograma(nodePrograma programa);
  public void visit_nodeID(nodeID ID);
  public void visit_nodeCorpo(nodeCorpo corpo);
}
