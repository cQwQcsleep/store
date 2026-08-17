package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SimpleLocator implements XMLLocator {
    int charOffset;
    int column;
    String esid;
    int line;
    String lsid;

    public SimpleLocator(String str, String str2, int i, int i2, int i3) {
        this.line = i;
        this.column = i2;
        this.lsid = str;
        this.esid = str2;
        this.charOffset = i3;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getBaseSystemId() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getCharacterOffset() {
        return this.charOffset;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getColumnNumber() {
        return this.column;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getEncoding() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getExpandedSystemId() {
        return this.esid;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getLineNumber() {
        return this.line;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getLiteralSystemId() {
        return this.lsid;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getPublicId() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getXMLVersion() {
        return null;
    }

    public void setBaseSystemId(String str) {
    }

    public void setCharacterOffset(int i) {
        this.charOffset = i;
    }

    public void setColumnNumber(int i) {
        this.column = i;
    }

    public void setExpandedSystemId(String str) {
        this.esid = str;
    }

    public void setLineNumber(int i) {
        this.line = i;
    }

    public void setLiteralSystemId(String str) {
        this.lsid = str;
    }

    public void setPublicId(String str) {
    }

    public void setValues(String str, String str2, int i, int i2, int i3) {
        this.line = i;
        this.column = i2;
        this.lsid = str;
        this.esid = str2;
        this.charOffset = i3;
    }

    public void setValues(String str, String str2, int i, int i2) {
        setValues(str, str2, i, i2, -1);
    }

    public SimpleLocator(String str, String str2, int i, int i2) {
        this(str, str2, i, i2, -1);
    }

    public SimpleLocator() {
    }
}
