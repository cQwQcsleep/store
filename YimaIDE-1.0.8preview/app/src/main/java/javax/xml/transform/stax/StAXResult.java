package javax.xml.transform.stax;

import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StAXResult implements Result {
    public static final String FEATURE = "http://javax.xml.transform.stax.StAXResult/feature";
    private String systemId;
    private XMLEventWriter xmlEventWriter;
    private XMLStreamWriter xmlStreamWriter;

    public StAXResult(XMLEventWriter xMLEventWriter) {
        this.xmlEventWriter = null;
        this.xmlStreamWriter = null;
        this.systemId = null;
        if (xMLEventWriter != null) {
            this.xmlEventWriter = xMLEventWriter;
        } else {
            w01.a("StAXResult(XMLEventWriter) with XMLEventWriter == null");
            throw null;
        }
    }

    @Override // javax.xml.transform.Result
    public String getSystemId() {
        return null;
    }

    public XMLEventWriter getXMLEventWriter() {
        return this.xmlEventWriter;
    }

    public XMLStreamWriter getXMLStreamWriter() {
        return this.xmlStreamWriter;
    }

    @Override // javax.xml.transform.Result
    public void setSystemId(String str) {
        throw new UnsupportedOperationException("StAXResult#setSystemId(systemId) cannot set the system identifier for a StAXResult");
    }

    public StAXResult(XMLStreamWriter xMLStreamWriter) {
        this.xmlEventWriter = null;
        this.xmlStreamWriter = null;
        this.systemId = null;
        if (xMLStreamWriter != null) {
            this.xmlStreamWriter = xMLStreamWriter;
        } else {
            w01.a("StAXResult(XMLStreamWriter) with XMLStreamWriter == null");
            throw null;
        }
    }
}
