package com.sun.org.apache.xalan.internal.xsltc.compiler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class QName {
    private int _hashCode;
    private final String _localname;
    private String _namespace;
    private String _prefix;
    private String _stringRep;

    public QName(String str, String str2, String str3) {
        this._namespace = str;
        this._prefix = str2;
        this._localname = str3;
        if (str != null && !str.equals("")) {
            str3 = str + ':' + str3;
        }
        this._stringRep = str3;
        this._hashCode = str3.hashCode() + 19;
    }

    public void clearNamespace() {
        this._namespace = "";
    }

    public String dump() {
        return "QName: " + this._namespace + "(" + this._prefix + "):" + this._localname;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof QName) && this._stringRep.equals(((QName) obj).getStringRep());
        }
        return true;
    }

    public String getLocalPart() {
        return this._localname;
    }

    public String getNamespace() {
        return this._namespace;
    }

    public String getPrefix() {
        return this._prefix;
    }

    public String getStringRep() {
        return this._stringRep;
    }

    public int hashCode() {
        return this._hashCode;
    }

    public String toString() {
        return this._stringRep;
    }
}
