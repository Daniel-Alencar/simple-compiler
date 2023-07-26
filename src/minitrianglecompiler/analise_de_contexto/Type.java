package minitrianglecompiler.analise_de_contexto;

public class Type {
    private byte kind;

    public static final byte BOOL = 0, INT = 1, REAL = 2;

    public Type(byte kind) {
        this.kind = kind;
    }

    public boolean equals(Object other) {
        Type otherType = (Type) other;
        return (kind == otherType.kind);
    }

    public static Type evaluate(Type tipo1, Type tipo2) {
        return new Type(Type.BOOL);
    }

    public static Type evaluateString(String tipoString) {
        Type tipo = null;

        if(tipoString.equals("real")) {
            tipo = new Type(Type.REAL);

        } else if(tipoString.equals("boolean")) {
            tipo = new Type(Type.BOOL);
            
        } else if(tipoString.equals("integer")) {
            tipo = new Type(Type.INT);

        }
        return tipo;
    }
}
