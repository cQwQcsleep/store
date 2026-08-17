package com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
final class SerializerFactoryImpl extends SerializerFactory {
    private String _method;

    public SerializerFactoryImpl(String str) {
        this._method = str;
        if (str.equals("xml") || this._method.equals("html") || this._method.equals("xhtml") || this._method.equals("text")) {
            return;
        }
        w01.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "MethodNotSupported", new Object[]{str}));
        throw null;
    }

    private Serializer getSerializer(OutputFormat outputFormat) {
        if (this._method.equals("xml")) {
            return new XMLSerializer(outputFormat);
        }
        if (this._method.equals("html")) {
            return new HTMLSerializer(outputFormat);
        }
        if (this._method.equals("xhtml")) {
            return new XHTMLSerializer(outputFormat);
        }
        if (this._method.equals("text")) {
            return new TextSerializer();
        }
        k2d.a(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "MethodNotSupported", new Object[]{this._method}));
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.SerializerFactory
    public String getSupportedMethod() {
        return this._method;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.SerializerFactory
    public Serializer makeSerializer(OutputFormat outputFormat) {
        Serializer serializer = getSerializer(outputFormat);
        serializer.setOutputFormat(outputFormat);
        return serializer;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.SerializerFactory
    public Serializer makeSerializer(Writer writer, OutputFormat outputFormat) {
        Serializer serializer = getSerializer(outputFormat);
        serializer.setOutputCharStream(writer);
        return serializer;
    }

    @Override // com.sun.org.apache.xml.internal.serialize.SerializerFactory
    public Serializer makeSerializer(OutputStream outputStream, OutputFormat outputFormat) throws UnsupportedEncodingException {
        Serializer serializer = getSerializer(outputFormat);
        serializer.setOutputByteStream(outputStream);
        return serializer;
    }
}
