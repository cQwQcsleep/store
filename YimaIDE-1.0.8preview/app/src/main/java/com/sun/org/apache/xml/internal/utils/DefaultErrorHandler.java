package com.sun.org.apache.xml.internal.utils;

import com.sun.org.apache.xml.internal.res.XMLMessages;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DefaultErrorHandler implements ErrorHandler, ErrorListener {
    PrintWriter m_pw;
    boolean m_throwExceptionOnError;

    public DefaultErrorHandler(boolean z) {
        this.m_throwExceptionOnError = true;
        this.m_pw = new PrintWriter((OutputStream) System.err, true);
        this.m_throwExceptionOnError = z;
    }

    public static void ensureLocationSet(TransformerException transformerException) {
        SourceLocator locator;
        Throwable exception = transformerException;
        SourceLocator sAXSourceLocator = null;
        do {
            if (exception instanceof SAXParseException) {
                sAXSourceLocator = new SAXSourceLocator((SAXParseException) exception);
            } else if ((exception instanceof TransformerException) && (locator = ((TransformerException) exception).getLocator()) != null) {
                sAXSourceLocator = locator;
            }
            if (exception instanceof TransformerException) {
                exception = ((TransformerException) exception).getCause();
            } else {
                exception = exception instanceof SAXException ? ((SAXException) exception).getException() : null;
            }
        } while (exception != null);
        transformerException.setLocator(sAXSourceLocator);
    }

    public static void printLocation(PrintWriter printWriter, Throwable th) {
        SourceLocator locator;
        String systemId;
        SourceLocator sAXSourceLocator = null;
        do {
            if (th instanceof SAXParseException) {
                sAXSourceLocator = new SAXSourceLocator((SAXParseException) th);
            } else if ((th instanceof TransformerException) && (locator = ((TransformerException) th).getLocator()) != null) {
                sAXSourceLocator = locator;
            }
            if (th instanceof TransformerException) {
                th = ((TransformerException) th).getCause();
            } else if (th instanceof WrappedRuntimeException) {
                th = ((WrappedRuntimeException) th).getException();
            } else {
                th = th instanceof SAXException ? ((SAXException) th).getException() : null;
            }
        } while (th != null);
        if (sAXSourceLocator == null) {
            printWriter.print("(" + XMLMessages.createXMLMessage("ER_LOCATION_UNKNOWN", null) + ")");
            return;
        }
        if (sAXSourceLocator.getPublicId() != null) {
            systemId = sAXSourceLocator.getPublicId();
        } else {
            systemId = sAXSourceLocator.getSystemId() != null ? sAXSourceLocator.getSystemId() : XMLMessages.createXMLMessage("ER_SYSTEMID_UNKNOWN", null);
        }
        printWriter.print(systemId + "; " + XMLMessages.createXMLMessage("line", null) + sAXSourceLocator.getLineNumber() + "; " + XMLMessages.createXMLMessage("column", null) + sAXSourceLocator.getColumnNumber() + "; ");
    }

    @Override // javax.xml.transform.ErrorListener
    public void error(TransformerException transformerException) throws TransformerException {
        if (this.m_throwExceptionOnError) {
            throw transformerException;
        }
        printLocation(this.m_pw, transformerException);
        this.m_pw.println(transformerException.getMessage());
    }

    @Override // javax.xml.transform.ErrorListener
    public void fatalError(TransformerException transformerException) throws TransformerException {
        if (this.m_throwExceptionOnError) {
            throw transformerException;
        }
        printLocation(this.m_pw, transformerException);
        this.m_pw.println(transformerException.getMessage());
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
        printLocation(this.m_pw, sAXParseException);
        this.m_pw.println("Parser warning: " + sAXParseException.getMessage());
    }

    public DefaultErrorHandler(PrintStream printStream) {
        this.m_throwExceptionOnError = true;
        this.m_pw = new PrintWriter((OutputStream) printStream, true);
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        throw sAXParseException;
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        throw sAXParseException;
    }

    public DefaultErrorHandler() {
        this(true);
    }

    public DefaultErrorHandler(PrintWriter printWriter) {
        this.m_throwExceptionOnError = true;
        this.m_pw = printWriter;
    }

    @Override // javax.xml.transform.ErrorListener
    public void warning(TransformerException transformerException) throws TransformerException {
        printLocation(this.m_pw, transformerException);
        this.m_pw.println(transformerException.getMessage());
    }

    public static void printLocation(PrintStream printStream, SAXParseException sAXParseException) {
        printLocation(new PrintWriter(printStream), sAXParseException);
    }

    public static void printLocation(PrintStream printStream, TransformerException transformerException) {
        printLocation(new PrintWriter(printStream), transformerException);
    }
}
