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
}
