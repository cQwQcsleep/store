package com.sun.xml.internal.stream;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.PropertyManager;
import com.sun.xml.internal.stream.writers.XMLDOMWriterImpl;
import com.sun.xml.internal.stream.writers.XMLEventWriterImpl;
import com.sun.xml.internal.stream.writers.XMLStreamWriterImpl;
import defpackage.b9g;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stream.StreamResult;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLOutputFactoryImpl extends XMLOutputFactory {
    private static final boolean DEBUG = false;
    private boolean fPropertyChanged;
    private PropertyManager fPropertyManager = new PropertyManager(2);
    private XMLStreamWriterImpl fStreamWriter = null;
    boolean fReuseInstance = false;

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(Result result) throws XMLStreamException {
        if (result instanceof StAXResult) {
            StAXResult stAXResult = (StAXResult) result;
            if (stAXResult.getXMLEventWriter() != null) {
                return stAXResult.getXMLEventWriter();
            }
        }
        return new XMLEventWriterImpl(createXMLStreamWriter(result));
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(Result result) throws XMLStreamException {
        if (result instanceof StreamResult) {
            return createXMLStreamWriter((StreamResult) result, (String) null);
        }
        if (result instanceof DOMResult) {
            return new XMLDOMWriterImpl((DOMResult) result);
        }
        if (!(result instanceof StAXResult)) {
            if (result.getSystemId() != null) {
                return createXMLStreamWriter(new StreamResult(result.getSystemId()));
            }
            b9g.a("Result of type ", result, " is not supported. Supported result types are: DOMResult, StAXResult and StreamResult.");
            return null;
        }
        StAXResult stAXResult = (StAXResult) result;
        if (stAXResult.getXMLStreamWriter() != null) {
            return stAXResult.getXMLStreamWriter();
        }
        b9g.a("Result of type ", result, " is not supported");
        return null;
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public Object getProperty(String str) throws IllegalArgumentException {
        if (str == null) {
            w01.a("Property not supported");
            return null;
        }
        if (this.fPropertyManager.containsProperty(str)) {
            return this.fPropertyManager.getProperty(str);
        }
        w01.a("Property not supported");
        return null;
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public boolean isPropertySupported(String str) {
        if (str == null) {
            return false;
        }
        return this.fPropertyManager.containsProperty(str);
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public void setProperty(String str, Object obj) throws IllegalArgumentException {
        if (str == null || obj == null || !this.fPropertyManager.containsProperty(str)) {
            kg9.a("Property ", str, "is not supported");
            return;
        }
        if (str == Constants.REUSE_INSTANCE || str.equals(Constants.REUSE_INSTANCE)) {
            boolean zBooleanValue = ((Boolean) obj).booleanValue();
            this.fReuseInstance = zBooleanValue;
            if (zBooleanValue) {
                kg9.a("Property ", str, " is not supported: XMLStreamWriters are not Thread safe");
                return;
            }
        } else {
            this.fPropertyChanged = true;
        }
        this.fPropertyManager.setProperty(str, obj);
    }

    public StreamResult toStreamResult(OutputStream outputStream, Writer writer, String str) {
        StreamResult streamResult = new StreamResult();
        streamResult.setOutputStream(outputStream);
        streamResult.setWriter(writer);
        streamResult.setSystemId(str);
        return streamResult;
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(OutputStream outputStream, String str) throws XMLStreamException {
        return new XMLEventWriterImpl(createXMLStreamWriter(outputStream, str));
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(OutputStream outputStream) throws XMLStreamException {
        return createXMLEventWriter(outputStream, null);
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLEventWriter createXMLEventWriter(Writer writer) throws XMLStreamException {
        return new XMLEventWriterImpl(createXMLStreamWriter(writer));
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(Writer writer) throws XMLStreamException {
        return createXMLStreamWriter(toStreamResult(null, writer, null), (String) null);
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream) throws XMLStreamException {
        return createXMLStreamWriter(outputStream, (String) null);
    }

    @Override // javax.xml.stream.XMLOutputFactory
    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream, String str) throws XMLStreamException {
        return createXMLStreamWriter(toStreamResult(outputStream, null, null), str);
    }

    public XMLStreamWriter createXMLStreamWriter(StreamResult streamResult, String str) throws XMLStreamException {
        XMLStreamWriterImpl xMLStreamWriterImpl;
        try {
            if (this.fReuseInstance && (xMLStreamWriterImpl = this.fStreamWriter) != null && xMLStreamWriterImpl.canReuse() && !this.fPropertyChanged) {
                this.fStreamWriter.reset();
                this.fStreamWriter.setOutput(streamResult, str);
                return this.fStreamWriter;
            }
            XMLStreamWriterImpl xMLStreamWriterImpl2 = new XMLStreamWriterImpl(streamResult, str, new PropertyManager(this.fPropertyManager));
            this.fStreamWriter = xMLStreamWriterImpl2;
            return xMLStreamWriterImpl2;
        } catch (IOException e) {
            az3.a(e);
            return null;
        }
    }
}
