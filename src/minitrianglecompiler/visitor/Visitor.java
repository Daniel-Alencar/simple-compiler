package minitrianglecompiler.visitor;

public interface Visitor {
  public void visit_nodeComando(nodeComando comando);
  public void visit_nodeComandoAtribuicao(nodeComandoAtribuicao comando);
  public void visit_nodeComandoComposto(nodeComandoComposto comando);
  public void visit_nodeComandoCondicional(nodeComandoCondicional comando);
  public void visit_nodeComandoIterativo(nodeComandoIterativo comando);
  public void visit_nodeCorpo(nodeCorpo corpo);
  public void visit_nodeDeclaracao(nodeDeclaracao declaracao);
  public void visit_nodeDeclaracaoDeVariavel(nodeDeclaracaoDeVariavel declaracao);
  public void visit_nodeDeclaracoes(nodeDeclaracoes declaracoes);
  public void visit_nodeExpressao(nodeExpressao expressao);
  public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressao);
  public void visit_nodeFator(nodeFator fator);
  public void visit_nodeID(nodeID ID);
  public void visit_nodeLiteral(nodeLiteral literal);
  public void visit_nodeOperador(nodeOperador operador);
  public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador);
  public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador);
  public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador);
  public void visit_nodePrograma(nodePrograma programa);
  public void visit_nodeTermo(nodeTermo termo);
  public void visit_nodeTipo(nodeTipo tipo);
  public void visit_nodeTipoSimples(nodeTipoSimples tipoSimples);
  public void visit_nodeVariavel(nodeVariavel variavel);
}
