package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Util {
    public static SAXException toSAXException(XNIException xNIException) {
        if (xNIException instanceof XMLParseException) {
            return toSAXParseException((XMLParseException) xNIException);
        }
        return xNIException.getException() instanceof SAXException ? (SAXException) xNIException.getException() : new SAXException(xNIException.getMessage(), xNIException.getException());
    }

    public static SAXParseException toSAXParseException(XMLParseException xMLParseException) {
        return xMLParseException.getException() instanceof SAXParseException ? (SAXParseException) xMLParseException.getException() : new SAXParseException(xMLParseException.getMessage(), xMLParseException.getPublicId(), xMLParseException.getExpandedSystemId(), xMLParseException.getLineNumber(), xMLParseException.getColumnNumber(), xMLParseException.getException());
    }

    public static final XMLInputSource toXMLInputSource(StreamSource streamSource) {
        if (streamSource.getReader() != null) {
            return new XMLInputSource(streamSource.getPublicId(), streamSource.getSystemId(), streamSource.getSystemId(), streamSource.getReader(), (String) null);
        }
        return streamSource.getInputStream() != null ? new XMLInputSource(streamSource.getPublicId(), streamSource.getSystemId(), streamSource.getSystemId(), streamSource.getInputStream(), (String) null) : new XMLInputSource(streamSource.getPublicId(), streamSource.getSystemId(), streamSource.getSystemId(), false);
    }
}
