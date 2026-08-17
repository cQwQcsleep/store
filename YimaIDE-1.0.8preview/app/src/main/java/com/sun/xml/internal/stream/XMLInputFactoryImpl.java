package com.sun.xml.internal.stream;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.PropertyManager;
import com.sun.org.apache.xerces.internal.impl.XMLStreamFilterImpl;
import com.sun.org.apache.xerces.internal.impl.XMLStreamReaderImpl;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.InputStream;
import java.io.Reader;
import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLInputFactoryImpl extends XMLInputFactory {
    private static final boolean DEBUG = false;
    private PropertyManager fPropertyManager = new PropertyManager(1);
    private XMLStreamReaderImpl fTempReader = null;
    boolean fPropertyChanged = false;
    boolean fReuseInstance = false;

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createFilteredReader(XMLStreamReader xMLStreamReader, StreamFilter streamFilter) throws XMLStreamException {
        if (xMLStreamReader == null || streamFilter == null) {
            return null;
        }
        return new XMLStreamFilterImpl(xMLStreamReader, streamFilter);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(InputStream inputStream) throws XMLStreamException {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(inputStream));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(Source source) throws XMLStreamException {
        return new XMLStreamReaderImpl(jaxpSourcetoXMLInputSource(source), new PropertyManager(this.fPropertyManager));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventAllocator getEventAllocator() {
        return (XMLEventAllocator) getProperty(XMLInputFactory.ALLOCATOR);
    }

    @Override // javax.xml.stream.XMLInputFactory
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

    @Override // javax.xml.stream.XMLInputFactory
    public XMLReporter getXMLReporter() {
        return (XMLReporter) this.fPropertyManager.getProperty(XMLInputFactory.REPORTER);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLResolver getXMLResolver() {
        return (XMLResolver) this.fPropertyManager.getProperty(XMLInputFactory.RESOLVER);
    }

    public XMLStreamReader getXMLStreamReaderImpl(XMLInputSource xMLInputSource) throws XMLStreamException {
        XMLStreamReaderImpl xMLStreamReaderImpl = this.fTempReader;
        if (xMLStreamReaderImpl == null) {
            this.fPropertyChanged = false;
            XMLStreamReaderImpl xMLStreamReaderImpl2 = new XMLStreamReaderImpl(xMLInputSource, new PropertyManager(this.fPropertyManager));
            this.fTempReader = xMLStreamReaderImpl2;
            return xMLStreamReaderImpl2;
        }
        if (this.fReuseInstance && xMLStreamReaderImpl.canReuse() && !this.fPropertyChanged) {
            this.fTempReader.reset();
            this.fTempReader.setInputSource(xMLInputSource);
            this.fPropertyChanged = false;
            return this.fTempReader;
        }
        this.fPropertyChanged = false;
        XMLStreamReaderImpl xMLStreamReaderImpl3 = new XMLStreamReaderImpl(xMLInputSource, new PropertyManager(this.fPropertyManager));
        this.fTempReader = xMLStreamReaderImpl3;
        return xMLStreamReaderImpl3;
    }

    public void initEventReader() {
        this.fPropertyChanged = true;
    }

    @Override // javax.xml.stream.XMLInputFactory
    public boolean isPropertySupported(String str) {
        if (str == null) {
            return false;
        }
        return this.fPropertyManager.containsProperty(str);
    }

    public XMLInputSource jaxpSourcetoXMLInputSource(Source source) {
        if (!(source instanceof StreamSource)) {
            c41.a("Cannot create XMLStreamReader or XMLEventReader from a ".concat(source.getClass().getName()));
            return null;
        }
        StreamSource streamSource = (StreamSource) source;
        String systemId = streamSource.getSystemId();
        String publicId = streamSource.getPublicId();
        InputStream inputStream = streamSource.getInputStream();
        Reader reader = streamSource.getReader();
        if (inputStream != null) {
            return new XMLInputSource(publicId, systemId, (String) null, inputStream, (String) null);
        }
        return reader != null ? new XMLInputSource(publicId, systemId, (String) null, reader, (String) null) : new XMLInputSource(publicId, systemId, null, false);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public void setEventAllocator(XMLEventAllocator xMLEventAllocator) {
        this.fPropertyManager.setProperty(XMLInputFactory.ALLOCATOR, xMLEventAllocator);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public void setProperty(String str, Object obj) throws IllegalArgumentException {
        if (str == null || obj == null || !this.fPropertyManager.containsProperty(str)) {
            kg9.a("Property ", str, " is not supported");
            return;
        }
        if (str == Constants.REUSE_INSTANCE || str.equals(Constants.REUSE_INSTANCE)) {
            this.fReuseInstance = ((Boolean) obj).booleanValue();
        } else {
            this.fPropertyChanged = true;
        }
        this.fPropertyManager.setProperty(str, obj);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public void setXMLReporter(XMLReporter xMLReporter) {
        this.fPropertyManager.setProperty(XMLInputFactory.REPORTER, xMLReporter);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public void setXMLResolver(XMLResolver xMLResolver) {
        this.fPropertyManager.setProperty(XMLInputFactory.RESOLVER, xMLResolver);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createFilteredReader(XMLEventReader xMLEventReader, EventFilter eventFilter) throws XMLStreamException {
        return new EventFilterSupport(xMLEventReader, eventFilter);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(Reader reader) throws XMLStreamException {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(reader));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(Source source) throws XMLStreamException {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(source));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(String str, InputStream inputStream) throws XMLStreamException {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(str, inputStream));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(Reader reader) throws XMLStreamException {
        return getXMLStreamReaderImpl(new XMLInputSource((String) null, (String) null, (String) null, reader, (String) null));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(InputStream inputStream, String str) throws XMLStreamException {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(inputStream, str));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(String str, Reader reader) throws XMLStreamException {
        return getXMLStreamReaderImpl(new XMLInputSource((String) null, str, (String) null, reader, (String) null));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(String str, Reader reader) throws XMLStreamException {
        initEventReader();
        return new XMLEventReaderImpl(createXMLStreamReader(str, reader));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(InputStream inputStream) throws XMLStreamException {
        return getXMLStreamReaderImpl(new XMLInputSource((String) null, (String) null, (String) null, inputStream, (String) null));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLEventReader createXMLEventReader(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return new XMLEventReaderImpl(xMLStreamReader);
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(String str, InputStream inputStream) throws XMLStreamException {
        return getXMLStreamReaderImpl(new XMLInputSource((String) null, str, (String) null, inputStream, (String) null));
    }

    @Override // javax.xml.stream.XMLInputFactory
    public XMLStreamReader createXMLStreamReader(InputStream inputStream, String str) throws XMLStreamException {
        return getXMLStreamReaderImpl(new XMLInputSource((String) null, (String) null, (String) null, inputStream, str));
    }
}
