package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.InputStream;
import java.io.Reader;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class SAXInputSource extends XMLInputSource {
    private InputSource fInputSource;
    private XMLReader fXMLReader;

    public SAXInputSource(XMLReader xMLReader, InputSource inputSource) {
        super(inputSource != null ? inputSource.getPublicId() : null, inputSource != null ? inputSource.getSystemId() : null, null, false);
        if (inputSource != null) {
            setByteStream(inputSource.getByteStream());
            setCharacterStream(inputSource.getCharacterStream());
            setEncoding(inputSource.getEncoding());
        }
        this.fInputSource = inputSource;
        this.fXMLReader = xMLReader;
    }

    public InputSource getInputSource() {
        return this.fInputSource;
    }

    public XMLReader getXMLReader() {
        return this.fXMLReader;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource
    public void setByteStream(InputStream inputStream) {
        super.setByteStream(inputStream);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setByteStream(inputStream);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource
    public void setCharacterStream(Reader reader) {
        super.setCharacterStream(reader);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setCharacterStream(reader);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource
    public void setEncoding(String str) {
        super.setEncoding(str);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setEncoding(str);
    }

    public void setInputSource(InputSource inputSource) {
        if (inputSource != null) {
            setPublicId(inputSource.getPublicId());
            setSystemId(inputSource.getSystemId());
            setByteStream(inputSource.getByteStream());
            setCharacterStream(inputSource.getCharacterStream());
            setEncoding(inputSource.getEncoding());
        } else {
            setPublicId(null);
            setSystemId(null);
            setByteStream(null);
            setCharacterStream(null);
            setEncoding(null);
        }
        this.fInputSource = inputSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource
    public void setPublicId(String str) {
        super.setPublicId(str);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setPublicId(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource
    public void setSystemId(String str) {
        super.setSystemId(str);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setSystemId(str);
    }

    public void setXMLReader(XMLReader xMLReader) {
        this.fXMLReader = xMLReader;
    }

    public SAXInputSource(InputSource inputSource) {
        this(null, inputSource);
    }

    public SAXInputSource() {
        this(null);
    }
}
