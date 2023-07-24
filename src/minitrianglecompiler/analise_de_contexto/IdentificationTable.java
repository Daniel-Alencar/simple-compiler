package minitrianglecompiler.analise_de_contexto;

import java.util.ArrayList;

public class IdentificationTable {
  ArrayList<Attribute> data;
  int currentLevel;

  public IdentificationTable() {
    this.data = new ArrayList<Attribute>();
    this.currentLevel = 0;
  }

  public Attribute retrieve(String id) {
    for (int i = 0; i < data.size(); i++) {
      if (this.data.get(i).identifier.equals(id)) {
        return data.get(i);
      }
    }
    return null;
  }

  public void enter(String id) {
    this.data.add(new Attribute(id, currentLevel));
  }

  public void enter(String id, Attribute attribute) {
    attribute.identifier = id;
    this.data.add(attribute);
  }

  public void openScope() {
    this.currentLevel++;
  }

  public void closeScope() {
    this.currentLevel--;
  }
}
