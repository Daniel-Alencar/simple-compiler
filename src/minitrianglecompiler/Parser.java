package minitrianglecompiler;

public class Parser {
  private TerminalSymbol currentTerminal;

  private void accept(TerminalSymbol expectedTerminal) {}

  private void parse_atribuicao();
  private void parse_comando();
  private void parse_comandoComposto();
  private void parse_condicional();
  private void parse_corpo();
  private void parse_declaracao();
  private void parse_declaracaoDeVariavel();
  private void parse_declaracoes();
  private void parse_expressao();
  private void parse_expressaoSimples();
  private void parse_fator();
  private void parse_iterativo();
  private void parse_listaDeComandos();
  private void parse_listaDeIds();
  private void parse_outros();
  private void parse_programa();
  private void parse_termo();
  private void parse_tipo();
  private void parse_variavel();

  // Regras da gramática LL1 como métodos
  private void parse_atribuicao() {
    parse_variavel();
    accept(":=");
    parse_expressao();
  }
}
