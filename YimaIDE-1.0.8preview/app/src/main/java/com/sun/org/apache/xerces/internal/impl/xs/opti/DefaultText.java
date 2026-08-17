package com.sun.org.apache.xerces.internal.impl.xs.opti;

import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DefaultText extends NodeImpl implements Text {
    @Override // org.w3c.dom.CharacterData
    public void appendData(String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.CharacterData
    public void deleteData(int i, int i2) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.CharacterData
    public String getData() throws DOMException {
        return null;
    }

    @Override // org.w3c.dom.CharacterData
    public int getLength() {
        return 0;
    }

    @Override // org.w3c.dom.Text
    public String getWholeText() {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.CharacterData
    public void insertData(int i, String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Text
    public boolean isElementContentWhitespace() {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.CharacterData
    public void replaceData(int i, int i2, String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Text
    public Text replaceWholeText(String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.CharacterData
    public void setData(String str) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.Text
    public Text splitText(int i) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    @Override // org.w3c.dom.CharacterData
    public String substringData(int i, int i2) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }
}
