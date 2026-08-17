package org.xml.sax;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class InputSource {
    private InputStream byteStream;
    private Reader characterStream;
    private String encoding;
    private String publicId;
    private String systemId;

    public InputSource(String str) {
        setSystemId(str);
    }

    private boolean isStreamEmpty() {
        try {
            InputStream inputStream = this.byteStream;
            if (inputStream != null) {
                inputStream.reset();
                if (this.byteStream.available() > 0) {
                    return false;
                }
            }
            Reader reader = this.characterStream;
            if (reader == null) {
                return true;
            }
            reader.reset();
            int i = this.characterStream.read();
            this.characterStream.reset();
            return i == -1;
        } catch (IOException unused) {
            return false;
        }
    }

    public InputStream getByteStream() {
        return this.byteStream;
    }

    public Reader getCharacterStream() {
        return this.characterStream;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public String getSystemId() {
        return this.systemId;
    }

    public boolean isEmpty() {
        return this.publicId == null && this.systemId == null && isStreamEmpty();
    }

    public void setByteStream(InputStream inputStream) {
        this.byteStream = inputStream;
    }

    public void setCharacterStream(Reader reader) {
        this.characterStream = reader;
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public void setPublicId(String str) {
        this.publicId = str;
    }

    public void setSystemId(String str) {
        this.systemId = str;
    }

    public InputSource() {
    }

    public InputSource(InputStream inputStream) {
        setByteStream(inputStream);
    }

    public InputSource(Reader reader) {
        setCharacterStream(reader);
    }
}
