package com.sun.org.apache.xml.internal.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public interface Serializer {
    ContentHandler asContentHandler() throws IOException;

    DOMSerializer asDOMSerializer() throws IOException;

    DocumentHandler asDocumentHandler() throws IOException;

    void setOutputByteStream(OutputStream outputStream);

    void setOutputCharStream(Writer writer);

    void setOutputFormat(OutputFormat outputFormat);
}
