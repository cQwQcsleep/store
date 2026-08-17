package com.sun.org.apache.xml.internal.dtm.ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ExtendedType {
    private int hash;
    private String localName;
    private String namespace;
    private int nodetype;

    public ExtendedType(int i, String str, String str2) {
        this.nodetype = i;
        this.namespace = str;
        this.localName = str2;
        this.hash = i + str.hashCode() + str2.hashCode();
    }

    public boolean equals(ExtendedType extendedType) {
        try {
            return extendedType.nodetype == this.nodetype && extendedType.localName.equals(this.localName) && extendedType.namespace.equals(this.namespace);
        } catch (NullPointerException unused) {
        }
    }

    public String getLocalName() {
        return this.localName;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public int getNodeType() {
        return this.nodetype;
    }

    public int hashCode() {
        return this.hash;
    }

    public void redefine(int i, String str, String str2) {
        this.nodetype = i;
        this.namespace = str;
        this.localName = str2;
        this.hash = i + str.hashCode() + str2.hashCode();
    }

    public void redefine(int i, String str, String str2, int i2) {
        this.nodetype = i;
        this.namespace = str;
        this.localName = str2;
        this.hash = i2;
    }

    public ExtendedType(int i, String str, String str2, int i2) {
        this.nodetype = i;
        this.namespace = str;
        this.localName = str2;
        this.hash = i2;
    }
}
