package com.reandroid.arsc.coder;

import com.reandroid.arsc.value.ValueType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ComplexUnit {
    private final int flag;
    private final String symbol;

    public ComplexUnit(int i, String str) {
        this.flag = i;
        this.symbol = str;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int getFlag() {
        return this.flag;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public abstract ValueType getValueType();

    public boolean hasPostfix(String str) {
        return str.endsWith(this.symbol);
    }

    public int hashCode() {
        return this.symbol.hashCode();
    }

    public boolean isFraction() {
        return false;
    }

    public String toString() {
        return getSymbol();
    }
}
