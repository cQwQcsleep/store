package com.sun.org.apache.xml.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLStringFactoryDefault extends XMLStringFactory {
    private static final XMLStringDefault EMPTY_STR = new XMLStringDefault("");

    @Override // com.sun.org.apache.xml.internal.utils.XMLStringFactory
    public XMLString emptystr() {
        return EMPTY_STR;
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLStringFactory
    public XMLString newstr(char[] cArr, int i, int i2) {
        return new XMLStringDefault(new String(cArr, i, i2));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLStringFactory
    public XMLString newstr(FastStringBuffer fastStringBuffer, int i, int i2) {
        return new XMLStringDefault(fastStringBuffer.getString(i, i2));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLStringFactory
    public XMLString newstr(String str) {
        return new XMLStringDefault(str);
    }
}
