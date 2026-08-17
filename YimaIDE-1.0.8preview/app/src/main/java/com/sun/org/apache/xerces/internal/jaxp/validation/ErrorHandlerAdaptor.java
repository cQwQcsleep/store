package com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ErrorHandlerAdaptor implements XMLErrorHandler {
    private boolean hadError = false;

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void error(String str, String str2, XMLParseException xMLParseException) {
        try {
            this.hadError = true;
            getErrorHandler().error(Util.toSAXParseException(xMLParseException));
        } catch (SAXException e) {
            throw new WrappedSAXException(e);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void fatalError(String str, String str2, XMLParseException xMLParseException) {
        try {
            this.hadError = true;
            getErrorHandler().fatalError(Util.toSAXParseException(xMLParseException));
        } catch (SAXException e) {
            throw new WrappedSAXException(e);
        }
    }

    public abstract ErrorHandler getErrorHandler();

    public boolean hadError() {
        return this.hadError;
    }

    public void reset() {
        this.hadError = false;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void warning(String str, String str2, XMLParseException xMLParseException) {
        try {
            getErrorHandler().warning(Util.toSAXParseException(xMLParseException));
        } catch (SAXException e) {
            throw new WrappedSAXException(e);
        }
    }
}
