package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSSerializerFilter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DOM3Serializer {
    DOMErrorHandler getErrorHandler();

    LSSerializerFilter getNodeFilter();

    void serializeDOM3(Node node) throws IOException;

    void setErrorHandler(DOMErrorHandler dOMErrorHandler);

    void setNewLine(String str);

    void setNodeFilter(LSSerializerFilter lSSerializerFilter);
}
