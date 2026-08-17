package com.sun.org.apache.xml.internal.serialize;

import java.io.OutputStream;
import java.io.Writer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class XHTMLSerializer extends HTMLSerializer {
    public XHTMLSerializer(Writer writer, OutputFormat outputFormat) {
        super(true, outputFormat == null ? new OutputFormat("xhtml", null, false) : outputFormat);
        setOutputCharStream(writer);
    }

    @Override // com.sun.org.apache.xml.internal.serialize.HTMLSerializer, com.sun.org.apache.xml.internal.serialize.BaseMarkupSerializer, com.sun.org.apache.xml.internal.serialize.Serializer
    public void setOutputFormat(OutputFormat outputFormat) {
        if (outputFormat == null) {
            outputFormat = new OutputFormat("xhtml", null, false);
        }
        super.setOutputFormat(outputFormat);
    }

    public XHTMLSerializer(OutputFormat outputFormat) {
        super(true, outputFormat == null ? new OutputFormat("xhtml", null, false) : outputFormat);
    }

    public XHTMLSerializer() {
        super(true, new OutputFormat("xhtml", null, false));
    }

    public XHTMLSerializer(OutputStream outputStream, OutputFormat outputFormat) {
        super(true, outputFormat == null ? new OutputFormat("xhtml", null, false) : outputFormat);
        setOutputByteStream(outputStream);
    }
}
