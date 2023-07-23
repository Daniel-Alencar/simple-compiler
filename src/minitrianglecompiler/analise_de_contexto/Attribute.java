package minitrianglecompiler.analise_de_contexto;

public class Attribute {
  public String name;
  public int scopeLevel;

  public Attribute(String name, int scopeLevel) {
    this.name = name;
    this.scopeLevel = scopeLevel;
  }
}
