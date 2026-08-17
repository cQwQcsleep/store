package com.sun.org.apache.xerces.internal.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class SynchronizedSymbolTable extends SymbolTable {
    protected SymbolTable fSymbolTable;

    public SynchronizedSymbolTable() {
        this.fSymbolTable = new SymbolTable();
    }

    @Override // com.sun.org.apache.xerces.internal.util.SymbolTable
    public String addSymbol(String str) {
        String strAddSymbol;
        synchronized (this.fSymbolTable) {
            strAddSymbol = this.fSymbolTable.addSymbol(str);
        }
        return strAddSymbol;
    }

    @Override // com.sun.org.apache.xerces.internal.util.SymbolTable
    public boolean containsSymbol(String str) {
        boolean zContainsSymbol;
        synchronized (this.fSymbolTable) {
            zContainsSymbol = this.fSymbolTable.containsSymbol(str);
        }
        return zContainsSymbol;
    }

    public SynchronizedSymbolTable(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    public SynchronizedSymbolTable(int i) {
        this.fSymbolTable = new SymbolTable(i);
    }

    @Override // com.sun.org.apache.xerces.internal.util.SymbolTable
    public String addSymbol(char[] cArr, int i, int i2) {
        String strAddSymbol;
        synchronized (this.fSymbolTable) {
            strAddSymbol = this.fSymbolTable.addSymbol(cArr, i, i2);
        }
        return strAddSymbol;
    }

    @Override // com.sun.org.apache.xerces.internal.util.SymbolTable
    public boolean containsSymbol(char[] cArr, int i, int i2) {
        boolean zContainsSymbol;
        synchronized (this.fSymbolTable) {
            zContainsSymbol = this.fSymbolTable.containsSymbol(cArr, i, i2);
        }
        return zContainsSymbol;
    }
}
