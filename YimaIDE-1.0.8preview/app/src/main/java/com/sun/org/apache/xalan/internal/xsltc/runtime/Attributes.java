package com.sun.org.apache.xalan.internal.xsltc.runtime;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import org.xml.sax.AttributeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Attributes implements AttributeList {
    private DOM _document;
    private int _element;

    public Attributes(DOM dom, int i) {
        this._element = i;
        this._document = dom;
    }

    @Override // org.xml.sax.AttributeList
    public int getLength() {
        return 0;
    }

    @Override // org.xml.sax.AttributeList
    public String getName(int i) {
        return null;
    }

    @Override // org.xml.sax.AttributeList
    public String getType(int i) {
        return null;
    }

    @Override // org.xml.sax.AttributeList
    public String getValue(int i) {
        return null;
    }

    @Override // org.xml.sax.AttributeList
    public String getType(String str) {
        return null;
    }

    @Override // org.xml.sax.AttributeList
    public String getValue(String str) {
        return null;
    }
}
