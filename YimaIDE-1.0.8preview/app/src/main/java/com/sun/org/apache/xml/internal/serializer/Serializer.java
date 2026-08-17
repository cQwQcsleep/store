package com.sun.org.apache.xml.internal.serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Properties;
import org.xml.sax.ContentHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Serializer {
    ContentHandler asContentHandler() throws IOException;

    Object asDOM3Serializer() throws IOException;

    DOMSerializer asDOMSerializer() throws IOException;

    Properties getOutputFormat();

    OutputStream getOutputStream();

    Writer getWriter();

    boolean reset();

    void setOutputFormat(Properties properties);

    void setOutputStream(OutputStream outputStream);

    void setWriter(Writer writer);
}
