package minitrianglecompiler.analise_de_contexto;

import minitrianglecompiler.visitor.nodeDeclaracaoDeVariavel;

public class Attribute {
  public String identifier;
  public byte type;

  public nodeDeclaracaoDeVariavel declaracaoDeVariavel;

  public static final byte BOOL = 0, INT = 1, REAL = 2;

  public Attribute(String identifier, nodeDeclaracaoDeVariavel declaracaoDeVariavel) {
    this.identifier = identifier;
    this.declaracaoDeVariavel = declaracaoDeVariavel;
  }
}
