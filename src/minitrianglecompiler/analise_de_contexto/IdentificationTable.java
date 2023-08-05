package minitrianglecompiler.analise_de_contexto;

import java.util.ArrayList;

import minitrianglecompiler.visitor.nodeDeclaracaoDeVariavel;

public class IdentificationTable {
  ArrayList<Attribute> data;

  public IdentificationTable() {
    this.data = new ArrayList<Attribute>();
  }

  public Attribute retrieve(String id) {
    for (int i = 0; i < data.size(); i++) {
      if (this.data.get(i).identifier.equals(id)) {
        return data.get(i);
      }
    }
    return null;
  }

  public void enter(String id, Type tipo, nodeDeclaracaoDeVariavel declaracaoDeVariavel) {
    this.data.add(new Attribute(id, tipo, declaracaoDeVariavel));
  }

  public void printTable() {
    System.out.println();
    System.out.println("---> Tabela de Identificação de símbolos:");
    for(int i = 0; i < data.size(); i++) {
      System.out.println(
        "-" + 
        data.get(i).identifier + " : " +
        data.get(i).tipo.kind
      );
    }
  }
}
