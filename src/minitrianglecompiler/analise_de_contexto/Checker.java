package minitrianglecompiler.analise_de_contexto;

import java.io.EOFException;

import minitrianglecompiler.visitor.*;

public class Checker implements Visitor {

    public IdentificationTable identificationTable = new IdentificationTable();

    public void check(nodePrograma programa) {
        System.out.println("---> Iniciando a análise da árvore");
        programa.visit(this);
    }

    @Override
    public void visit_nodeComando(nodeComando comando) {
        if (comando != null)
            comando.visit(this);
    }

    @Override
    public void visit_nodeComandoAtribuicao(nodeComandoAtribuicao comando) {
        if (comando != null) {
            Attribute atributo = identificationTable.retrieve(comando.variavel.ID.valor);
            if(atributo != null) {
                comando.declaracaoDeVariavel = atributo.declaracaoDeVariavel;
            } else {
                // Variável não declarada
                System.out.println("A variável " + comando.variavel.ID.valor + " não foi declarada!");
            }
            comando.expressao.visit(this);
        }
    }

    @Override
    public void visit_nodeComandoComposto(nodeComandoComposto comando) {
        if (comando != null) {
            if(comando.comandos != null) {
                for(int i= 0; i < comando.comandos.size(); i++) {
                    comando.comandos.get(i).visit(this);
                }
            }  
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
                if (comando.expressao != null) {
                    comando.expressao.visit(this);
                }
            }
        }
    }

    @Override
    public void visit_nodeCorpo(nodeCorpo corpo) {
        if (corpo != null) {
            corpo.declaracoes.visit(this);
            corpo.comandoComposto.visit(this);
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
        // Não precisa verificar se o Tipo é válido, o analisador léxico já cuida disso
        // Talvez visitar o nodeTipo
        for (int i = 0; i < declaracao.IDs.size(); i++) {
            if (identificationTable.retrieve(declaracao.IDs.get(i).valor) == null) {
                
                identificationTable.enter(declaracao.IDs.get(i).valor, declaracao);
                declaracao.IDs.get(i).visit(this);
            } else {
                // Erro variavel já declarada
                System.out.println("Variável " + declaracao.IDs.get(i).valor + " já declarada!");
            }
        }
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
    public Type visit_nodeExpressao(nodeExpressao expressao) {
        if (expressao != null) {
            if (expressao.expressaoSimples1 != null) {
                expressao.expressaoSimples1.visit(this);
            }
            if (expressao.operadorRelacional != null) {
                expressao.operadorRelacional.visit(this);
            }
            if (expressao.expressaoSimples2 != null) {
                expressao.expressaoSimples1.visit(this);
            }
        }

        return new Type(Type.BOOL);
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
    public Type visit_nodeFator(nodeFator fator) {
        if (fator instanceof nodeVariavel) {
            ((nodeVariavel) fator).visit(this);
        } else if (fator instanceof nodeLiteral) {
            ((nodeLiteral) fator).visit(this);
        } else if (fator instanceof nodeExpressao) {
            ((nodeExpressao) fator).visit(this);
        }
        return new Type(Type.BOOL);
    }

    @Override
    public void visit_nodeID(nodeID ID) {
        if (ID != null && identificationTable.retrieve(ID.valor) != null) {
            return;
        }
    }

    @Override
    public Type visit_nodeLiteral(nodeLiteral literal) {
        if (literal != null) {
            return literal.tipo;
        }
        return null;
    }

    @Override
    public void visit_nodeOperador(nodeOperador operador) {
        if (operador != null) {
            return;
        }
    }

    @Override
    public void visit_nodeOperadorAditivo(nodeOperadorAditivo operador) {
        if (operador != null) {
            return;
        }
    }

    @Override
    public void visit_nodeOperadorMultiplicativo(nodeOperadorMultiplicativo operador) {
        if (operador != null) {
            return;
        }
    }

    @Override
    public void visit_nodeOperadorRelacional(nodeOperadorRelacional operador) {
        if (operador != null) {
            return;
        }
    }

    @Override
    public void visit_nodePrograma(nodePrograma programa) {
        if (programa != null) {
            programa.corpo.visit(this);
        }
    }

    @Override
    public void visit_nodeTermo(nodeTermo termo) {
        if (termo != null) {
            termo.fator.visit(this);
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
        if (variavel != null) {
            variavel.ID.visit(this);
        }
        // Erro de variável não declarada
    }

}
