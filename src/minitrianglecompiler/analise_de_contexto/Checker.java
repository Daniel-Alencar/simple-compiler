package minitrianglecompiler.analise_de_contexto;

import minitrianglecompiler.ShowError;
import minitrianglecompiler.visitor.*;

public class Checker implements Visitor {

    public IdentificationTable identificationTable = new IdentificationTable();

    public void check(nodePrograma programa) {
        System.out.println("");
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
            if (atributo != null) {
                comando.declaracaoDeVariavel = atributo.declaracaoDeVariavel;
            } else {
                // Erro variável não declarada
                new ShowError(
                    "A variável "+ "\"" + 
                comando.variavel.ID.valor + "\"" + " não foi declarada\n" 
                + "Linha: " +  comando.variavel.token.line + "\""
                + " Coluna: " + comando.variavel.token.column
                );
                
            }

            Type tipoExpressao = getType_nodeExpressao(comando.expressao);

            if (comando.variavel != null) {
                if (!tipoExpressao.equals(atributo.tipo)) {
                    new ShowError("Tipo de atribuição inválida: "
                        + comando.token.spelling + "... \n"
                        + "Linha: " + comando.token.line + " Coluna: " + comando.token.column 
                    );
                }
            } else {
                new ShowError("A variável desse comando está com valor NULL\n");
            }
        }
    }

    @Override
    public void visit_nodeComandoComposto(nodeComandoComposto comando) {
        if (comando != null) {
            if (comando.comandos != null) {
                for (int i = 0; i < comando.comandos.size(); i++) {
                    comando.comandos.get(i).visit(this);
                }
            }
        }
    }

    @Override
    public void visit_nodeComandoCondicional(nodeComandoCondicional comando) {
        if (comando != null) {
            // Verificar se a expressão é booleana
            if (
                comando.expressao != null && 
                this.getType_nodeExpressao(comando.expressao).kind == Type.BOOL
            ) {
                if (comando.comando1 != null) {
                    comando.comando1.visit(this);
                }
                if (comando.comando2 != null) {
                    comando.comando2.visit(this);
                }
            } else {
                new ShowError("A expressão do comando condicional deveria ser do tipo BOOLEAN\n" +
                "Linha: " + comando.expressao.token.line + " Coluna: " + comando.expressao.token.column);
            }
        }
    }

   
    public void visit_nodeComandoIterativo(nodeComandoIterativo comando) {
        if (comando != null) {
            // Verificar se a expressão é booleana
            if (comando.expressao != null) {
                Type tipo = this.getType_nodeExpressao(comando.expressao);

                if(tipo.kind == Type.BOOL) {
                    if (comando.comando != null) {
                        comando.comando.visit(this);
                    }
                } else {
                    new ShowError("A expressão deveria ser do tipo BOOLEAN\n"+ 
                        "Linha: " + comando.expressao.token.line + " Coluna: " +
                        comando.expressao.token.column
                    );
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
        // Não precisa verificar se o Tipo é válido,
        // o analisador léxico já cuida disso
        // Talvez visitar o nodeTipo
        for (int i = 0; i < declaracao.IDs.size(); i++) {
            if (identificationTable.retrieve(declaracao.IDs.get(i).valor) == null) {

                identificationTable.enter(
                        declaracao.IDs.get(i).valor,
                        declaracao.tipo.tipoSimples.tipoType,
                        declaracao);
                declaracao.IDs.get(i).visit(this);
            } else {
                // Erro variavel já declarada
                new ShowError(
                    "Variável \"" + declaracao.IDs.get(i).token.spelling + "\" já declarada\n" + 
                    "Linha: " + declaracao.IDs.get(i).token.line + " Coluna: " 
                    + declaracao.IDs.get(i).token.column
                
                );
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
    public void visit_nodeExpressao(nodeExpressao expressao) {

        if (expressao != null) {
            if (expressao.expressaoSimples1 != null) {
                expressao.expressaoSimples1.visit(this);
            }
            if (expressao.operadorRelacional != null) {
                expressao.operadorRelacional.visit(this);
            }
            if (expressao.expressaoSimples2 != null) {
                expressao.expressaoSimples2.visit(this);
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
        if (ID != null && identificationTable.retrieve(ID.valor) != null) {
            return;
        }
    }

    @Override
    public void visit_nodeLiteral(nodeLiteral literal) {
        if (literal != null) {
        }
        return;
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
        // Erro variável não declarada
    }

    public Type getType_nodeExpressao(nodeExpressao expressao) {
        Type typeExpresaoSimples1, typeExpresaoSimples2;
        Type type = null;

        if (expressao != null) {
            if (expressao.expressaoSimples1 != null) {
                typeExpresaoSimples1 = getType_nodeExpressaoSimples(expressao.expressaoSimples1);
                type = typeExpresaoSimples1;

                if (expressao.expressaoSimples2 != null) {
                    typeExpresaoSimples2 = getType_nodeExpressaoSimples(expressao.expressaoSimples2);

                    type = Type.evaluate(
                        typeExpresaoSimples1, typeExpresaoSimples2, 
                        expressao.operadorRelacional
                    );
                }
                return type;
            }
        }
        return type;
    }

    public Type getType_nodeExpressaoSimples(nodeExpressaoSimples expressaoSimples) {
        Type type = null;
        if (expressaoSimples != null) {

            if (expressaoSimples.termo != null && expressaoSimples.operadoresAditivos.isEmpty()) {
                type = getType_nodeTermo(expressaoSimples.termo);
            } else {
                Type tipoResultado = null;
                Type tipoTermoAnterior = getType_nodeTermo(expressaoSimples.termo);

                for (int i = 0; i < expressaoSimples.termos.size(); i++) {
                    nodeTermo termo = expressaoSimples.termos.get(i);
                    Type tipoTermo = getType_nodeTermo(termo);

                    nodeOperador operador = expressaoSimples.operadoresAditivos.get(i);

                    tipoTermoAnterior = Type.evaluate(tipoTermoAnterior, tipoTermo, operador);
                }
                tipoResultado = tipoTermoAnterior;
                type = tipoResultado;
            }  
        }
        
        return type;
    }

    public Type getType_nodeFator(nodeFator fator) {
        Type type = null;
        if (fator instanceof nodeVariavel) {
            type = getType_nodeVariavel((nodeVariavel) fator);
        } else if (fator instanceof nodeLiteral) {
            type = getType_nodeLiteral((nodeLiteral) fator);
        } else if (fator instanceof nodeExpressao) {
            type = getType_nodeExpressao((nodeExpressao) fator);
        }

        return type;
    }

    public Type getType_nodeLiteral(nodeLiteral literal) {
        Type type = null;
        if (literal != null) {
            type = literal.tipo;
        }
        return type;
    }

    public Type getType_nodeTermo(nodeTermo termo) {
        Type type = null;
        if (termo != null) {

            if (termo.fator != null && termo.fatores.isEmpty()) {
                type = getType_nodeFator(termo.fator);
            } else {
                Type tipoResultado = null;
                Type tipoFatorAnterior = getType_nodeFator(termo.fator);

                for (int i = 0; i < termo.fatores.size(); i++) {
                    nodeFator fator = termo.fatores.get(i);

                    Type tipoFator = getType_nodeFator(fator);
                    nodeOperador operador = termo.operadoresMultiplicativos.get(i);

                    tipoFatorAnterior = Type.evaluate(tipoFatorAnterior, tipoFator, operador);
                }
                tipoResultado = tipoFatorAnterior;
                type = tipoResultado;
            }
        }
        return type;
    }

    public Type getType_nodeVariavel(nodeVariavel variavel) {
        Type type = null;

        if (variavel != null && variavel.ID != null) {
            Attribute atributo = identificationTable.retrieve(variavel.ID.valor);
            if (atributo != null) {
                type = atributo.tipo;
            } else {
                new ShowError(
                    "Variável \"" + variavel.token.spelling + "\" + não declarada\n" + 
                    "Linha: " + variavel.token.line + " Coluna: " 
                    + variavel.token.column
                
                );
            }
        }
        return type;
    }
}
