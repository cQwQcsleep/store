package com.sun.org.apache.xerces.internal.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ShadowedSymbolTable extends SymbolTable {
    protected SymbolTable fSymbolTable;

    public ShadowedSymbolTable(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    @Override // com.sun.org.apache.xerces.internal.util.SymbolTable
    public String addSymbol(String str) {
        return this.fSymbolTable.containsSymbol(str) ? this.fSymbolTable.addSymbol(str) : super.addSymbol(str);
    }

    @Override // com.sun.org.apache.xerces.internal.util.SymbolTable
    public int hash(String str) {
        return this.fSymbolTable.hash(str);
    }

    @Override // com.sun.org.apache.xerces.internal.util.SymbolTable
    public int hash(char[] cArr, int i, int i2) {
        return this.fSymbolTable.hash(cArr, i, i2);
    }

    @Override // com.sun.org.apache.xerces.internal.util.SymbolTable
    public String addSymbol(char[] cArr, int i, int i2) {
        if (this.fSymbolTable.containsSymbol(cArr, i, i2)) {
            return this.fSymbolTable.addSymbol(cArr, i, i2);
        }
        return super.addSymbol(cArr, i, i2);
    }
}
