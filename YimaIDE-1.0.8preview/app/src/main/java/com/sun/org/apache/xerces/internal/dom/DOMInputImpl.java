package com.sun.org.apache.xerces.internal.dom;

import java.io.InputStream;
import java.io.Reader;
import org.w3c.dom.ls.LSInput;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMInputImpl implements LSInput {
    protected String fBaseSystemId;
    protected InputStream fByteStream;
    protected boolean fCertifiedText;
    protected Reader fCharStream;
    protected String fData;
    protected String fEncoding;
    protected String fPublicId;
    protected String fSystemId;

    public DOMInputImpl() {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fCertifiedText = false;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getBaseURI() {
        return this.fBaseSystemId;
    }

    @Override // org.w3c.dom.ls.LSInput
    public InputStream getByteStream() {
        return this.fByteStream;
    }

    @Override // org.w3c.dom.ls.LSInput
    public boolean getCertifiedText() {
        return this.fCertifiedText;
    }

    @Override // org.w3c.dom.ls.LSInput
    public Reader getCharacterStream() {
        return this.fCharStream;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getEncoding() {
        return this.fEncoding;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getPublicId() {
        return this.fPublicId;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getStringData() {
        return this.fData;
    }

    @Override // org.w3c.dom.ls.LSInput
    public String getSystemId() {
        return this.fSystemId;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setBaseURI(String str) {
        this.fBaseSystemId = str;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setByteStream(InputStream inputStream) {
        this.fByteStream = inputStream;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setCertifiedText(boolean z) {
        this.fCertifiedText = z;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setCharacterStream(Reader reader) {
        this.fCharStream = reader;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setEncoding(String str) {
        this.fEncoding = str;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setPublicId(String str) {
        this.fPublicId = str;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setStringData(String str) {
        this.fData = str;
    }

    @Override // org.w3c.dom.ls.LSInput
    public void setSystemId(String str) {
        this.fSystemId = str;
    }

    public DOMInputImpl(String str, String str2, String str3) {
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fCertifiedText = false;
        this.fPublicId = str;
        this.fSystemId = str2;
        this.fBaseSystemId = str3;
    }

    public DOMInputImpl(String str, String str2, String str3, InputStream inputStream, String str4) {
        this.fCharStream = null;
        this.fData = null;
        this.fCertifiedText = false;
        this.fPublicId = str;
        this.fSystemId = str2;
        this.fBaseSystemId = str3;
        this.fByteStream = inputStream;
        this.fEncoding = str4;
    }

    public DOMInputImpl(String str, String str2, String str3, Reader reader, String str4) {
        this.fByteStream = null;
        this.fData = null;
        this.fCertifiedText = false;
        this.fPublicId = str;
        this.fSystemId = str2;
        this.fBaseSystemId = str3;
        this.fCharStream = reader;
        this.fEncoding = str4;
    }

    public DOMInputImpl(String str, String str2, String str3, String str4, String str5) {
        this.fByteStream = null;
        this.fCharStream = null;
        this.fCertifiedText = false;
        this.fPublicId = str;
        this.fSystemId = str2;
        this.fBaseSystemId = str3;
        this.fData = str4;
        this.fEncoding = str5;
    }
}
