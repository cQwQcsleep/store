package com.sun.org.apache.xerces.internal.dom;

import java.io.OutputStream;
import java.io.Writer;
import org.w3c.dom.ls.LSOutput;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMOutputImpl implements LSOutput {
    protected Writer fCharStream = null;
    protected OutputStream fByteStream = null;
    protected String fSystemId = null;
    protected String fEncoding = null;

    @Override // org.w3c.dom.ls.LSOutput
    public OutputStream getByteStream() {
        return this.fByteStream;
    }

    @Override // org.w3c.dom.ls.LSOutput
    public Writer getCharacterStream() {
        return this.fCharStream;
    }

    @Override // org.w3c.dom.ls.LSOutput
    public String getEncoding() {
        return this.fEncoding;
    }

    @Override // org.w3c.dom.ls.LSOutput
    public String getSystemId() {
        return this.fSystemId;
    }

    @Override // org.w3c.dom.ls.LSOutput
    public void setByteStream(OutputStream outputStream) {
        this.fByteStream = outputStream;
    }

    @Override // org.w3c.dom.ls.LSOutput
    public void setCharacterStream(Writer writer) {
        this.fCharStream = writer;
    }

    @Override // org.w3c.dom.ls.LSOutput
    public void setEncoding(String str) {
        this.fEncoding = str;
    }

    @Override // org.w3c.dom.ls.LSOutput
    public void setSystemId(String str) {
        this.fSystemId = str;
    }
}
