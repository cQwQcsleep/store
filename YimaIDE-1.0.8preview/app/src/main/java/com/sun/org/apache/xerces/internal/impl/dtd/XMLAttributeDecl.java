package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.xni.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLAttributeDecl {
    public boolean optional;
    public final QName name = new QName();
    public final XMLSimpleType simpleType = new XMLSimpleType();

    public void clear() {
        this.name.clear();
        this.simpleType.clear();
        this.optional = false;
    }

    public void setValues(QName qName, XMLSimpleType xMLSimpleType, boolean z) {
        this.name.setValues(qName);
        this.simpleType.setValues(xMLSimpleType);
        this.optional = z;
    }
}
