package com.reandroid.xml.base;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Node extends XmlSerializable, XmlReader {
    default int getColumnNumber() {
        return 0;
    }

    int getLineNumber();

    Node getParentNode();

    default void setColumnNumber(int i) {
    }

    void setLineNumber(int i);

    default String toXmlString(boolean z) {
        try {
            return XmlSerializable.toXmlString(this, z);
        } catch (IOException unused) {
            return null;
        }
    }

    default String toXmlString() {
        return toXmlString(true);
    }
}
