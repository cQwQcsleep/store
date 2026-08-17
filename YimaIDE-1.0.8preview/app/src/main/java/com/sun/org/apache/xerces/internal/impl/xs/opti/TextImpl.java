package com.sun.org.apache.xerces.internal.impl.xs.opti;

import defpackage.zi0;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TextImpl extends DefaultText {
    int fCol;
    String fData;
    int fRow;
    SchemaDOM fSchemaDOM;

    public TextImpl(StringBuffer stringBuffer, SchemaDOM schemaDOM, int i, int i2) {
        this.fData = null;
        this.fSchemaDOM = null;
        this.fData = stringBuffer.toString();
        this.fSchemaDOM = schemaDOM;
        this.fRow = i;
        this.fCol = i2;
        this.uri = null;
        this.localpart = null;
        this.prefix = null;
        this.rawname = null;
        this.nodeType = (short) 3;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultText, org.w3c.dom.CharacterData
    public String getData() throws DOMException {
        return this.fData;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultText, org.w3c.dom.CharacterData
    public int getLength() {
        String str = this.fData;
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Node getNextSibling() {
        int i = this.fCol;
        NodeImpl[] nodeImplArr = this.fSchemaDOM.relations[this.fRow];
        if (i == nodeImplArr.length - 1) {
            return null;
        }
        return nodeImplArr[i + 1];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Node getParentNode() {
        return this.fSchemaDOM.relations[this.fRow][0];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Node getPreviousSibling() {
        int i = this.fCol;
        if (i == 1) {
            return null;
        }
        return this.fSchemaDOM.relations[this.fRow][i - 1];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultText, org.w3c.dom.CharacterData
    public String substringData(int i, int i2) throws DOMException {
        String str = this.fData;
        if (str == null) {
            return null;
        }
        if (i2 < 0 || i < 0 || i > str.length()) {
            zi0.a(1, "parameter error");
            return null;
        }
        int i3 = i2 + i;
        int length = this.fData.length();
        String str2 = this.fData;
        return i3 >= length ? str2.substring(i) : str2.substring(i, i3);
    }
}
