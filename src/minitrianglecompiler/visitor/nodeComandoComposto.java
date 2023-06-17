package minitrianglecompiler.visitor;
import java.util.ArrayList;

public class nodeComandoComposto extends nodeComando {
  public ArrayList<nodeComando> comandos;

  public void visit(Visitor visitor) {
    visitor.visit_nodeComandoComposto(this);
  }
}
