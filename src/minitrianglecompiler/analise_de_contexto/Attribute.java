package minitrianglecompiler.analise_de_contexto;

import minitrianglecompiler.visitor.nodeDeclaracaoDeVariavel;

public class Attribute {
  public String identifier;
  public Type tipo;
  public nodeDeclaracaoDeVariavel declaracaoDeVariavel;

  public Attribute(String identifier, Type tipo, nodeDeclaracaoDeVariavel declaracaoDeVariavel) {
    this.identifier = identifier;
    this.tipo = tipo;
    this.declaracaoDeVariavel = declaracaoDeVariavel;
  }
}
