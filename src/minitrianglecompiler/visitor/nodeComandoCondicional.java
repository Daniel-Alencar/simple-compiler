package minitrianglecompiler.visitor;

import minitrianglecompiler.ast.Comando;

public class nodeComandoCondicional extends Comando {
  public nodeExpressao expressao;
  public nodeComando comando1;
  public nodeComando comando2;
}
