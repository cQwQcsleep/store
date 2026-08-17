package com.sun.org.apache.xml.internal.serializer.dom3;

import com.sun.org.apache.xml.internal.serializer.DOM3Serializer;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.serializer.utils.WrappedRuntimeException;
import java.io.IOException;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSSerializerFilter;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class DOM3SerializerImpl implements DOM3Serializer {
    private DOMErrorHandler fErrorHandler;
    private String fNewLine;
    private SerializationHandler fSerializationHandler;
    private LSSerializerFilter fSerializerFilter;

    public DOM3SerializerImpl(SerializationHandler serializationHandler) {
        this.fSerializationHandler = serializationHandler;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.DOM3Serializer
    public DOMErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.DOM3Serializer
    public LSSerializerFilter getNodeFilter() {
        return this.fSerializerFilter;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.DOM3Serializer
    public void serializeDOM3(Node node) throws IOException {
        try {
            new DOM3TreeWalker(this.fSerializationHandler, this.fErrorHandler, this.fSerializerFilter, this.fNewLine).traverse(node);
        } catch (SAXException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.DOM3Serializer
    public void setErrorHandler(DOMErrorHandler dOMErrorHandler) {
        this.fErrorHandler = dOMErrorHandler;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.DOM3Serializer
    public void setNewLine(String str) {
        this.fNewLine = str;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.DOM3Serializer
    public void setNodeFilter(LSSerializerFilter lSSerializerFilter) {
        this.fSerializerFilter = lSSerializerFilter;
    }

    public void setSerializationHandler(SerializationHandler serializationHandler) {
        this.fSerializationHandler = serializationHandler;
    }
}
