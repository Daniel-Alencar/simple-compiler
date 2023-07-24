package minitrianglecompiler.analise_de_contexto;

import minitrianglecompiler.visitor.*;

public class Checker implements Visitor {

    private IdentificationTable identificationTable = new IdentificationTable();

    @Override
    public void visit_nodeComando(nodeComando comando) {
        if (comando != null)
            comando.visit(this);
    }

    @Override
    public void visit_nodeComandoAtribuicao(nodeComandoAtribuicao comando) {
        if (comando != null)
            identificationTable.enter(comando.variavel.ID.valor);
        comando.visit(this);
    }

    @Override
    public void visit_nodeComandoComposto(nodeComandoComposto comando) {
        if (comando != null) {
            comando.visit(this);
        }
    }

    @Override
    public void visit_nodeComandoCondicional(nodeComandoCondicional comando) {
        if (comando != null) {
            if (comando.expressao != null) {
                // verificar se a expressão é booleana
                if (comando.comando1 != null) {
                    comando.comando1.visit(this);
                }
                if (comando.comando2 != null) {
                    comando.comando2.visit(this);
                }
            }
        }
        // erro semantico caso contrário
    }

    @Override
    public void visit_nodeComandoIterativo(nodeComandoIterativo comando) {
        if (comando != null) {
            // verificar se a expressão é booleana
            if (comando.expressao != null) {
                if (comando.comando != null) {
                    comando.comando.visit(this);
                }
            }
        }
    }

    @Override
    public void visit_nodeCorpo(nodeCorpo corpo) {
        if (corpo != null) {
            corpo.visit(this);
        }
    }

    @Override
    public void visit_nodeDeclaracao(nodeDeclaracao declaracao) {
        if (declaracao != null) {
            declaracao.declaracaoDeVariavel.visit(this);
        }
    }

    @Override
    public void visit_nodeDeclaracaoDeVariavel(nodeDeclaracaoDeVariavel declaracao) {
        // checar se o tipo da declaração é valido
        identificationTable.openScope();
        for (int i = 0; i < declaracao.IDs.size(); i++) {
            if (identificationTable.retrieve(declaracao.IDs.get(i).valor) == null) {
                identificationTable.enter(declaracao.IDs.get(i).valor);
            } else {
                // erro variavel já declarada
            }
        }
        declaracao.visit(this);
        identificationTable.closeScope();
    }

    @Override
    public void visit_nodeDeclaracoes(nodeDeclaracoes declaracoes) {
        if (declaracoes != null) {
            for (int i = 0; i < declaracoes.declaracoes.size(); i++) {
                declaracoes.declaracoes.get(i).visit(this);
            }
        }
    }

    @Override
    public void visit_nodeExpressao(nodeExpressao expressao) {
        if (expressao != null) {
            if (expressao.expressaoSimples1 != null) {
                expressao.expressaoSimples1.visit(this);
            }
            if (expressao.expressaoSimples2 != null) {
                expressao.expressaoSimples1.visit(this);
            }
            if (expressao.operadorRelacional != null) {
                expressao.operadorRelacional.visit(this);
            }
        }
    }

    @Override
    public void visit_nodeExpressaoSimples(nodeExpressaoSimples expressao) {
        if (expressao != null) {
            if (expressao.termo != null) {
                expressao.termo.visit(this);
            }
            if (expressao.operadoresAditivos != null) {
                for (int i = 0; i < expressao.operadoresAditivos.size(); i++) {
                    if (expressao.operadoresAditivos.get(i) != null) {
                        expressao.operadoresAditivos.get(i).visit(this);
                        expressao.termos.get(i).visit(this);
                    }
                }
            }
        }
    }

    @Override
    public void visit_nodeFator(nodeFator fator) {
        if (fator instanceof nodeVariavel) {
            ((nodeVariavel) fator).visit(this);
        } else if (fator instanceof nodeLiteral) {
            ((nodeLiteral) fator).visit(this);
        } else if (fator instanceof nodeExpressao) {
            ((nodeExpressao) fator).visit(this);
        }
    }

    @Override
    public void visit_nodeID(nodeID ID) {
        if (ID != null) {
            ID.visit(this);
        }
    }

    @Override
    public void visit_nodeLiteral(nodeLiteral literal) {
        if (literal != null) {
            literal.visit(this);
        }
    }

    @Override
    public void visit_nodeOperador(nodeOperador operador) {
        if (operador != null) {
            operador.visit(this);
        }
    }

    @Override
    public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador) {
        if (operador != null) {
            operador.visit(this);
        }
    }

    @Override
    public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador) {
        if (operador != null) {
            operador.visit(this);
        }
    }

    @Override
    public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador) {
        if (operador != null) {
            operador.visit(this);
        }
    }

    @Override
    public void visit_nodePrograma(nodePrograma programa) {
        if (programa != null) {
            programa.visit(this);
        }
    }

    @Override
    public void visit_nodeTermo(nodeTermo termo) {
        if (termo != null) {
            termo.visit(this);
        }
    }

    @Override
    public void visit_nodeTipo(nodeTipo tipo) {
        if (tipo != null) {
            tipo.visit(this);
        }
    }

    @Override
    public void visit_nodeTipoSimples(nodeTipoSimples tipoSimples) {
        if (tipoSimples != null) {
            tipoSimples.visit(this);
        }
    }

    @Override
    public void visit_nodeVariavel(nodeVariavel variavel) {
        if (variavel != null && identificationTable.retrieve(variavel.ID.valor) != null) {
            variavel.visit(this);
        }
    }

}
