package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class JAXPNamespaceContextWrapper implements NamespaceContext {
    private final Vector<String> fAllPrefixes = new Vector<>();
    private int[] fContext = new int[8];
    private int fCurrentContext;
    private javax.xml.namespace.NamespaceContext fNamespaceContext;
    private List<String> fPrefixes;
    private SymbolTable fSymbolTable;

    public JAXPNamespaceContextWrapper(SymbolTable symbolTable) {
        setSymbolTable(symbolTable);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public boolean declarePrefix(String str, String str2) {
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public Enumeration<String> getAllPrefixes() {
        return Collections.enumeration(new TreeSet(this.fAllPrefixes));
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public String getDeclaredPrefixAt(int i) {
        return this.fPrefixes.get(i);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public int getDeclaredPrefixCount() {
        List<String> list = this.fPrefixes;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public List<String> getDeclaredPrefixes() {
        return this.fPrefixes;
    }

    public javax.xml.namespace.NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public String getPrefix(String str) {
        javax.xml.namespace.NamespaceContext namespaceContext = this.fNamespaceContext;
        if (namespaceContext == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        String prefix = namespaceContext.getPrefix(str);
        String str2 = prefix != null ? prefix : "";
        SymbolTable symbolTable = this.fSymbolTable;
        return symbolTable != null ? symbolTable.addSymbol(str2) : str2.intern();
    }

    public SymbolTable getSymbolTable() {
        return this.fSymbolTable;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public String getURI(String str) {
        String namespaceURI;
        javax.xml.namespace.NamespaceContext namespaceContext = this.fNamespaceContext;
        if (namespaceContext == null || (namespaceURI = namespaceContext.getNamespaceURI(str)) == null || "".equals(namespaceURI)) {
            return null;
        }
        SymbolTable symbolTable = this.fSymbolTable;
        return symbolTable != null ? symbolTable.addSymbol(namespaceURI) : namespaceURI.intern();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public void popContext() {
        Vector<String> vector = this.fAllPrefixes;
        int[] iArr = this.fContext;
        int i = this.fCurrentContext;
        this.fCurrentContext = i - 1;
        vector.setSize(iArr[i]);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public void pushContext() {
        int i = this.fCurrentContext + 1;
        int[] iArr = this.fContext;
        if (i == iArr.length) {
            int[] iArr2 = new int[iArr.length * 2];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.fContext = iArr2;
        }
        int[] iArr3 = this.fContext;
        int i2 = this.fCurrentContext + 1;
        this.fCurrentContext = i2;
        iArr3[i2] = this.fAllPrefixes.size();
        List<String> list = this.fPrefixes;
        if (list != null) {
            this.fAllPrefixes.addAll(list);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public void reset() {
        this.fCurrentContext = 0;
        this.fContext[0] = 0;
        this.fAllPrefixes.clear();
    }

    public void setDeclaredPrefixes(List<String> list) {
        this.fPrefixes = list;
    }

    public void setNamespaceContext(javax.xml.namespace.NamespaceContext namespaceContext) {
        this.fNamespaceContext = namespaceContext;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }
}
