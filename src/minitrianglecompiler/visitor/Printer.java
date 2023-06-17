package minitrianglecompiler.visitor;

public class Printer implements Visitor {

  int i = 0;
  int tabulacao = 2;

  void indent() {
    for (int j = 0; j < i; j++)
      System.out.print("|");
  }

  public void visit_nodePrograma(nodePrograma programa) {
    if(programa != null) {
      if(programa.id != null) {
        programa.id.visit(this);
      }
      if(programa.corpo != null) {
        programa.corpo.visit(this);
      }
    }
  }

  public void visit_nodeID(nodeID ID) {
    if(ID != null) {
      System.out.println("ID");
    }
  }

  public void visit_nodeCorpo(nodeCorpo corpo) {
    if(corpo != null) {
      i += tabulacao;
      this.indent();
      if(corpo.declaracoes != null) {
        System.out.println("Declarações");
      }
      this.indent();
      if(corpo.comandoComposto != null) {
        System.out.println("Comando composto");
      }
    }
  }

  public void print(nodePrograma programa) {
    System.out.println("---> Iniciando impressao da arvore");
    programa.visit(this);
  }
}
