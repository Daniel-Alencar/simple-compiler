package minitrianglecompiler.analise_de_contexto;

import minitrianglecompiler.visitor.nodeDeclaracaoDeVariavel;

public class Attribute {
  public String identifier;
  public Type tipo;
  public nodeDeclaracaoDeVariavel declaracaoDeVariavel;
  
  // Não sendo usado até o momento
  public byte type;
  public static final byte BOOL = 0, INT = 1, REAL = 2;

  public Attribute(String identifier, Type tipo, nodeDeclaracaoDeVariavel declaracaoDeVariavel) {
    this.identifier = identifier;
    this.tipo = tipo;
    this.declaracaoDeVariavel = declaracaoDeVariavel;
  }
}
