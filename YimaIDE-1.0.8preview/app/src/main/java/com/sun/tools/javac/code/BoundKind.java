package com.sun.tools.javac.code;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum BoundKind {
    EXTENDS("? extends "),
    SUPER("? super "),
    UNBOUND("?");

    private final String name;

    BoundKind(String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
