package minitrianglecompiler.analise_de_contexto;

import minitrianglecompiler.Token;
import minitrianglecompiler.visitor.nodeOperador;

public class Type {
    public byte kind;

    public static final byte BOOL = 0, INT = 1, REAL = 2;

    public Type(byte kind) {
        this.kind = kind;
    }

    public boolean equals(Object other) {
        Type otherType = (Type) other;

        return (this.kind == otherType.kind);
    }

    public static Type evaluate(Type tipo1, Type tipo2, nodeOperador operador) {
        byte kind1 = tipo1.kind;
        byte kind2 = tipo2.kind;

        // Regras
        // (real, boolean e integer)
        // (relacional(==), multiplicativo(*) e aditivo(+))

        // integer + real = real
        // integer * real = real
        // integer == real = bool

        // integer + integer = integer
        // integer * integer = real
        // integer == integer = bool

        // real + real = real
        // real * real = real
        // real == real = bool

        // OBS.: O BOOLEANO SÓ SE RELACIONA COM UM BOLEANO E UM OPERADOR RELACIONAL
        if (kind1 == kind2 && operador.operador == Token.RELATIONALOPERATOR) {
            return new Type(Type.BOOL);
        }
        // INTEIRO ou REAL
        if (kind1 != Type.BOOL && kind2 != Type.BOOL) {
            // CASO SEJA UMA OPERAÇÃO RELACIONAL
            if (operador.operador == Token.RELATIONALOPERATOR) {
                return new Type(Type.BOOL);
                // CASO SEJA UM INTEIRO E UM REAL
            } else if (kind1 != kind2) {
                return new Type(Type.REAL);
                // CASO AMBOS OS TIPOS SEJAM IGUAIS
            } else if (kind1 == kind2) {
                return new Type(kind1);

            }
        }
        return null;

    }

    public static Type evaluateString(String tipoString) {
        Type tipo = null;

        if (tipoString.equals("real")) {
            tipo = new Type(Type.REAL);

        } else if (tipoString.equals("boolean")) {
            tipo = new Type(Type.BOOL);

        } else if (tipoString.equals("integer")) {
            tipo = new Type(Type.INT);

        }
        return tipo;
    }
}
