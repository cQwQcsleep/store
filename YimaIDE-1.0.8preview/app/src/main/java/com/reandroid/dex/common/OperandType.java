package com.reandroid.dex.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class OperandType {
    private final String name;
    public static final OperandType NONE = new OperandType("NONE");
    public static final OperandType HEX = new OperandType("HEX");
    public static final OperandType DECIMAL = new OperandType("DECIMAL");
    public static final OperandType LABEL = new OperandType("LABEL");
    public static final OperandType KEY = new OperandType("KEY");
    public static final OperandType DUAL_KEY = new OperandType("DUAL_KEY");

    private OperandType(String str) {
        this.name = str;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return this.name;
    }
}
