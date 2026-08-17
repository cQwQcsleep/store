package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLString;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLStringBuffer extends XMLString {
    public XMLStringBuffer(String str) {
        this(str.length());
        append(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLString
    public void clear() {
        this.offset = 0;
        this.length = 0;
    }

    public XMLStringBuffer(int i) {
        this.ch = new char[i];
    }

    public XMLStringBuffer(char c) {
        this(1);
        append(c);
    }

    public XMLStringBuffer() {
        this(32);
    }

    public XMLStringBuffer(char[] cArr, int i, int i2) {
        this(i2);
        append(cArr, i, i2);
    }

    public XMLStringBuffer(XMLString xMLString) {
        this(xMLString.length);
        append(xMLString);
    }
}
