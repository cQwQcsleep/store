package javax.xml.transform.stream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import javax.xml.transform.Source;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StreamSource implements Source {
    public static final String FEATURE = "http://javax.xml.transform.stream.StreamSource/feature";
    private InputStream inputStream;
    private String publicId;
    private Reader reader;
    private String systemId;

    public StreamSource(File file) {
        setSystemId(file.toURI().toASCIIString());
    }

    private boolean isStreamEmpty() {
        try {
            InputStream inputStream = this.inputStream;
            if (inputStream != null) {
                inputStream.reset();
                if (this.inputStream.available() > 0) {
                    return false;
                }
            }
            Reader reader = this.reader;
            if (reader == null) {
                return true;
            }
            reader.reset();
            int i = this.reader.read();
            this.reader.reset();
            return i == -1;
        } catch (IOException unused) {
            return false;
        }
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public String getPublicId() {
        return this.publicId;
    }

    public Reader getReader() {
        return this.reader;
    }

    @Override // javax.xml.transform.Source
    public String getSystemId() {
        return this.systemId;
    }

    @Override // javax.xml.transform.Source
    public boolean isEmpty() {
        return this.publicId == null && this.systemId == null && isStreamEmpty();
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setPublicId(String str) {
        this.publicId = str;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setSystemId(File file) {
        this.systemId = file.toURI().toASCIIString();
    }

    @Override // javax.xml.transform.Source
    public void setSystemId(String str) {
        this.systemId = str;
    }

    public StreamSource(InputStream inputStream) {
        setInputStream(inputStream);
    }

    public StreamSource(InputStream inputStream, String str) {
        setInputStream(inputStream);
        setSystemId(str);
    }

    public StreamSource(Reader reader) {
        setReader(reader);
    }

    public StreamSource(Reader reader, String str) {
        setReader(reader);
        setSystemId(str);
    }

    public StreamSource(String str) {
        this.systemId = str;
    }

    public StreamSource() {
    }
}
