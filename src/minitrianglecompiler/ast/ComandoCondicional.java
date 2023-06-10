package minitrianglecompiler.ast;

public class ComandoCondicional extends Comando {
  public Expressao expressao;
  public Comando comando1;
  public Comando comando2;

  public ComandoCondicional(Expressao expressao, Comando comando1, Comando comando2) {
    this.expressao = expressao;
    this.comando1 = comando1;
    this.comando2 = comando2;
  }
}
