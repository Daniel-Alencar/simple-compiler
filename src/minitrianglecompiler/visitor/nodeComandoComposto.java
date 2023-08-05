package minitrianglecompiler.visitor;
import java.util.ArrayList;

import minitrianglecompiler.Token;

public class nodeComandoComposto extends nodeComando {
  public ArrayList<nodeComando> comandos;

  public nodeComandoComposto(Token token) {
    super(token);
  }

  public void visit(Visitor visitor) {
    visitor.visit_nodeComandoComposto(this);
  }
}
