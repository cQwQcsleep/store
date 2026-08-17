package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import java.io.InputStream;
import java.io.Reader;
import org.xml.sax.InputSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLInputSource {
    protected String fBaseSystemId;
    protected InputStream fByteStream;
    protected Reader fCharStream;
    protected String fEncoding;
    boolean fIsCreatedByResolver;
    protected String fPublicId;
    protected String fSystemId;

    public XMLInputSource(InputSource inputSource, boolean z) {
        this.fIsCreatedByResolver = false;
        this.fPublicId = inputSource.getPublicId();
        this.fSystemId = inputSource.getSystemId();
        this.fByteStream = inputSource.getByteStream();
        this.fCharStream = inputSource.getCharacterStream();
        this.fEncoding = inputSource.getEncoding();
        this.fIsCreatedByResolver = z;
    }

    public String getBaseSystemId() {
        return this.fBaseSystemId;
    }

    public InputStream getByteStream() {
        return this.fByteStream;
    }

    public Reader getCharacterStream() {
        return this.fCharStream;
    }

    public String getEncoding() {
        return this.fEncoding;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    public String getSystemId() {
        return this.fSystemId;
    }

    public boolean isCreatedByResolver() {
        return this.fIsCreatedByResolver;
    }

    public void setBaseSystemId(String str) {
        this.fBaseSystemId = str;
    }

    public void setByteStream(InputStream inputStream) {
        this.fByteStream = inputStream;
    }

    public void setCharacterStream(Reader reader) {
        this.fCharStream = reader;
    }

    public void setCreatedByResolver(boolean z) {
        this.fIsCreatedByResolver = z;
    }

    public void setEncoding(String str) {
        this.fEncoding = str;
    }

    public void setPublicId(String str) {
        this.fPublicId = str;
    }

    public void setSystemId(String str) {
        this.fSystemId = str;
    }

    public XMLInputSource(XMLResourceIdentifier xMLResourceIdentifier) {
        this.fIsCreatedByResolver = false;
        this.fPublicId = xMLResourceIdentifier.getPublicId();
        this.fSystemId = xMLResourceIdentifier.getLiteralSystemId();
        this.fBaseSystemId = xMLResourceIdentifier.getBaseSystemId();
    }

    public XMLInputSource(String str, String str2, String str3, boolean z) {
        this.fPublicId = str;
        this.fSystemId = str2;
        this.fBaseSystemId = str3;
        this.fIsCreatedByResolver = z;
    }

    public XMLInputSource(String str, String str2, String str3, InputStream inputStream, String str4) {
        this.fIsCreatedByResolver = false;
        this.fPublicId = str;
        this.fSystemId = str2;
        this.fBaseSystemId = str3;
        this.fByteStream = inputStream;
        this.fEncoding = str4;
    }

    public XMLInputSource(String str, String str2, String str3, Reader reader, String str4) {
        this.fIsCreatedByResolver = false;
        this.fPublicId = str;
        this.fSystemId = str2;
        this.fBaseSystemId = str3;
        this.fCharStream = reader;
        this.fEncoding = str4;
    }
}
