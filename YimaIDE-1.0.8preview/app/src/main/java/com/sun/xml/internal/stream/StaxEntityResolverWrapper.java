package com.sun.xml.internal.stream;

import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import defpackage.knd;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.catalog.CatalogException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StaxEntityResolverWrapper {
    XMLResolver fStaxResolver;

    public StaxEntityResolverWrapper(XMLResolver xMLResolver) {
        this.fStaxResolver = xMLResolver;
    }

    public XMLResolver getStaxEntityResolver() {
        return this.fStaxResolver;
    }

    public StaxXMLInputSource getStaxInputSource(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof InputStream) {
            return new StaxXMLInputSource(new XMLInputSource((String) null, (String) null, (String) null, (InputStream) obj, (String) null), true);
        }
        if (obj instanceof XMLStreamReader) {
            return new StaxXMLInputSource((XMLStreamReader) obj, true);
        }
        if (obj instanceof XMLEventReader) {
            return new StaxXMLInputSource((XMLEventReader) obj, true);
        }
        return null;
    }

    public StaxXMLInputSource resolveEntity(XMLResourceIdentifier xMLResourceIdentifier) throws IOException, XNIException {
        try {
            return getStaxInputSource(this.fStaxResolver.resolveEntity(xMLResourceIdentifier.getPublicId(), xMLResourceIdentifier.getLiteralSystemId(), xMLResourceIdentifier.getBaseSystemId(), null));
        } catch (XMLStreamException | CatalogException e) {
            knd.a(e);
            return null;
        }
    }

    public void setStaxEntityResolver(XMLResolver xMLResolver) {
        this.fStaxResolver = xMLResolver;
    }
}
