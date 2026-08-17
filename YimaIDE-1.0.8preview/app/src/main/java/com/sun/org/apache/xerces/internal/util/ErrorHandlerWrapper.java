package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ErrorHandlerWrapper implements XMLErrorHandler {
    protected ErrorHandler fErrorHandler;

    public ErrorHandlerWrapper(ErrorHandler errorHandler) {
        setErrorHandler(errorHandler);
    }

    public static SAXParseException createSAXParseException(XMLParseException xMLParseException) {
        return new SAXParseException(xMLParseException.getMessage(), xMLParseException.getPublicId(), xMLParseException.getExpandedSystemId(), xMLParseException.getLineNumber(), xMLParseException.getColumnNumber(), xMLParseException.getException());
    }

    public static XMLParseException createXMLParseException(SAXParseException sAXParseException) {
        final String publicId = sAXParseException.getPublicId();
        final String systemId = sAXParseException.getSystemId();
        final int lineNumber = sAXParseException.getLineNumber();
        final int columnNumber = sAXParseException.getColumnNumber();
        return new XMLParseException(new XMLLocator() { // from class: com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.1
            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public String getBaseSystemId() {
                return null;
            }

            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public int getCharacterOffset() {
                return -1;
            }

            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public int getColumnNumber() {
                return columnNumber;
            }

            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public String getEncoding() {
                return null;
            }

            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public String getExpandedSystemId() {
                return systemId;
            }

            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public int getLineNumber() {
                return lineNumber;
            }

            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public String getLiteralSystemId() {
                return null;
            }

            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public String getPublicId() {
                return publicId;
            }

            @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
            public String getXMLVersion() {
                return null;
            }
        }, sAXParseException.getMessage(), sAXParseException);
    }

    public static XNIException createXNIException(SAXException sAXException) {
        return new XNIException(sAXException.getMessage(), sAXException);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void error(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        if (this.fErrorHandler != null) {
            try {
                this.fErrorHandler.error(createSAXParseException(xMLParseException));
            } catch (SAXParseException e) {
                throw createXMLParseException(e);
            } catch (SAXException e2) {
                throw createXNIException(e2);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void fatalError(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        if (this.fErrorHandler != null) {
            try {
                this.fErrorHandler.fatalError(createSAXParseException(xMLParseException));
            } catch (SAXParseException e) {
                throw createXMLParseException(e);
            } catch (SAXException e2) {
                throw createXNIException(e2);
            }
        }
    }

    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.fErrorHandler = errorHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void warning(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        if (this.fErrorHandler != null) {
            try {
                this.fErrorHandler.warning(createSAXParseException(xMLParseException));
            } catch (SAXParseException e) {
                throw createXMLParseException(e);
            } catch (SAXException e2) {
                throw createXNIException(e2);
            }
        }
    }

    public ErrorHandlerWrapper() {
    }
}
