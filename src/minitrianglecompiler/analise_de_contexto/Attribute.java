package minitrianglecompiler.analise_de_contexto;

public class Attribute {
  public String identifier;
  public byte type;

  public static final byte BOOL = 0, INT = 1, REAL = 2;

  public Attribute(String identifier) {
    this.identifier = identifier;
  }
}
