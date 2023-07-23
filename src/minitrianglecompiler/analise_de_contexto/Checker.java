package minitrianglecompiler.analise_de_contexto;

import minitrianglecompiler.visitor.Visitor;
import minitrianglecompiler.visitor.nodeComando;
import minitrianglecompiler.visitor.nodeComandoAtribuicao;
import minitrianglecompiler.visitor.nodeComandoComposto;
import minitrianglecompiler.visitor.nodeComandoCondicional;
import minitrianglecompiler.visitor.nodeComandoIterativo;
import minitrianglecompiler.visitor.nodeCorpo;
import minitrianglecompiler.visitor.nodeDeclaracao;
import minitrianglecompiler.visitor.nodeDeclaracaoDeVariavel;
import minitrianglecompiler.visitor.nodeDeclaracoes;
import minitrianglecompiler.visitor.nodeExpressao;
import minitrianglecompiler.visitor.nodeExpressaoSimples;
import minitrianglecompiler.visitor.nodeFator;
import minitrianglecompiler.visitor.nodeID;
import minitrianglecompiler.visitor.nodeLiteral;
import minitrianglecompiler.visitor.nodeOperador;
import minitrianglecompiler.visitor.nodeOperadorAditivo;
import minitrianglecompiler.visitor.nodeOperadorMultiplicativo;
import minitrianglecompiler.visitor.nodeOperadorRelacional;
import minitrianglecompiler.visitor.nodePrograma;
import minitrianglecompiler.visitor.nodeTermo;
import minitrianglecompiler.visitor.nodeTipo;
import minitrianglecompiler.visitor.nodeTipoSimples;
import minitrianglecompiler.visitor.nodeVariavel;

public class Checker implements Visitor {

    private IdentificationTable identificationTable = new IdentificationTable();

    @Override
    public void visit_nodeComando(nodeComando comando) {
        identificationTable.openScope();
        comando.visit(this);
    }

    @Override
    public void visit_nodeComandoAtribuicao(nodeComandoAtribuicao comando) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeComandoAtribuicao'");
    }

    @Override
    public void visit_nodeComandoComposto(nodeComandoComposto comando) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeComandoComposto'");
    }

    @Override
    public void visit_nodeComandoCondicional(nodeComandoCondicional comando) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeComandoCondicional'");
    }

    @Override
    public void visit_nodeComandoIterativo(nodeComandoIterativo comando) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeComandoIterativo'");
    }

    @Override
    public void visit_nodeCorpo(nodeCorpo corpo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeCorpo'");
    }

    @Override
    public void visit_nodeDeclaracao(nodeDeclaracao declaracao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeDeclaracao'");
    }

    @Override
    public void visit_nodeDeclaracaoDeVariavel(nodeDeclaracaoDeVariavel declaracao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeDeclaracaoDeVariavel'");
    }

    @Override
    public void visit_nodeDeclaracoes(nodeDeclaracoes declaracoes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeDeclaracoes'");
    }

    @Override
    public void visit_nodeExpressao(nodeExpressao expressao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeExpressao'");
    }

    @Override
    public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeExpressaoSimples'");
    }

    @Override
    public void visit_nodeFator(nodeFator fator) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeFator'");
    }

    @Override
    public void visit_nodeID(nodeID ID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeID'");
    }

    @Override
    public void visit_nodeLiteral(nodeLiteral literal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeLiteral'");
    }

    @Override
    public void visit_nodeOperador(nodeOperador operador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeOperador'");
    }

    @Override
    public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeOperadorAditivo'");
    }

    @Override
    public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeOperadorMultiplicativo'");
    }

    @Override
    public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeOperadorRelacional'");
    }

    @Override
    public void visit_nodePrograma(nodePrograma programa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodePrograma'");
    }

    @Override
    public void visit_nodeTermo(nodeTermo termo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeTermo'");
    }

    @Override
    public void visit_nodeTipo(nodeTipo tipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeTipo'");
    }

    @Override
    public void visit_nodeTipoSimples(nodeTipoSimples tipoSimples) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeTipoSimples'");
    }

    @Override
    public void visit_nodeVariavel(nodeVariavel variavel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit_nodeVariavel'");
    }

}
