package javax.xml.transform.stream;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StreamResult implements Result {
    public static final String FEATURE = "http://javax.xml.transform.stream.StreamResult/feature";
    private OutputStream outputStream;
    private String systemId;
    private Writer writer;

    public StreamResult(File file) {
        setSystemId(file.toURI().toASCIIString());
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override // javax.xml.transform.Result
    public String getSystemId() {
        return this.systemId;
    }

    public Writer getWriter() {
        return this.writer;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setSystemId(File file) {
        this.systemId = file.toURI().toASCIIString();
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override // javax.xml.transform.Result
    public void setSystemId(String str) {
        this.systemId = str;
    }

    public StreamResult(OutputStream outputStream) {
        setOutputStream(outputStream);
    }

    public StreamResult(Writer writer) {
        setWriter(writer);
    }

    public StreamResult(String str) {
        this.systemId = str;
    }

    public StreamResult() {
    }
}
