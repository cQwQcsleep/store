package com.sun.org.apache.xml.internal.utils;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ListingErrorHandler implements ErrorHandler, ErrorListener {
    protected PrintWriter m_pw;
    protected boolean throwOnWarning = false;
    protected boolean throwOnError = true;
    protected boolean throwOnFatalError = true;

    public ListingErrorHandler(PrintWriter printWriter) {
        this.m_pw = null;
        if (printWriter != null) {
            this.m_pw = printWriter;
        } else {
            x0e.a(XMLMessages.createXMLMessage("ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER", null));
            throw null;
        }
    }

    public static String getSourceLine(String str, int i) throws Exception {
        URL url;
        InputStream inputStream;
        BufferedReader bufferedReader;
        Throwable th;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            int iIndexOf = str.indexOf(58);
            int iIndexOf2 = str.indexOf(47);
            if (iIndexOf != -1 && iIndexOf2 != -1 && iIndexOf < iIndexOf2) {
                throw e;
            }
            url = new URL(SystemIDResolver.getAbsoluteURI(str));
        }
        String line = null;
        try {
            inputStream = url.openConnection().getInputStream();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                for (int i2 = 1; i2 <= i; i2++) {
                    try {
                        line = bufferedReader.readLine();
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader.close();
                        inputStream.close();
                        throw th;
                    }
                }
                bufferedReader.close();
                inputStream.close();
                return line;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                th = th;
                bufferedReader.close();
                inputStream.close();
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            bufferedReader = null;
        }
    }

    public static void logExceptionLocation(PrintWriter printWriter, Throwable th) {
        SourceLocator locator;
        String systemId;
        if (printWriter == null) {
            printWriter = new PrintWriter((OutputStream) System.err, true);
        }
        Throwable exception = th;
        SourceLocator sAXSourceLocator = null;
        do {
            if (exception instanceof SAXParseException) {
                sAXSourceLocator = new SAXSourceLocator((SAXParseException) exception);
            } else if ((exception instanceof TransformerException) && (locator = ((TransformerException) exception).getLocator()) != null) {
                sAXSourceLocator = locator;
            }
            if (exception instanceof TransformerException) {
                exception = ((TransformerException) exception).getCause();
            } else if (exception instanceof WrappedRuntimeException) {
                exception = ((WrappedRuntimeException) exception).getException();
            } else {
                exception = exception instanceof SAXException ? ((SAXException) exception).getException() : null;
            }
        } while (exception != null);
        String message = PsiKeyword.NULL;
        if (sAXSourceLocator == null) {
            printWriter.print("SystemId-Unknown:locator-unavailable: ");
            printWriter.println("exception:" + th.getMessage());
            StringBuilder sb = new StringBuilder("root-cause:");
            if (exception != null) {
                message = exception.getMessage();
            }
            sb.append(message);
            printWriter.println(sb.toString());
            return;
        }
        if (sAXSourceLocator.getPublicId() != sAXSourceLocator.getPublicId()) {
            systemId = sAXSourceLocator.getPublicId();
        } else {
            systemId = sAXSourceLocator.getSystemId() != null ? sAXSourceLocator.getSystemId() : "SystemId-Unknown";
        }
        printWriter.print(systemId + ":Line=" + sAXSourceLocator.getLineNumber() + ";Column=" + sAXSourceLocator.getColumnNumber() + ": ");
        StringBuilder sb2 = new StringBuilder("exception:");
        sb2.append(th.getMessage());
        printWriter.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder("root-cause:");
        if (exception != null) {
            message = exception.getMessage();
        }
        sb3.append(message);
        printWriter.println(sb3.toString());
        logSourceLine(printWriter, sAXSourceLocator);
    }

    public static void logSourceLine(PrintWriter printWriter, SourceLocator sourceLocator) {
        if (sourceLocator == null) {
            return;
        }
        if (printWriter == null) {
            printWriter = new PrintWriter((OutputStream) System.err, true);
        }
        String systemId = sourceLocator.getSystemId();
        if (systemId == null) {
            printWriter.println("line: (No systemId; cannot read file)");
            printWriter.println();
            return;
        }
        try {
            int lineNumber = sourceLocator.getLineNumber();
            int columnNumber = sourceLocator.getColumnNumber();
            printWriter.println("line: " + getSourceLine(systemId, lineNumber));
            StringBuffer stringBuffer = new StringBuffer("line: ");
            for (int i = 1; i < columnNumber; i++) {
                stringBuffer.append(' ');
            }
            stringBuffer.append('^');
            printWriter.println(stringBuffer.toString());
        } catch (Exception e) {
            printWriter.println("line: logSourceLine unavailable due to: " + e.getMessage());
            printWriter.println();
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        logExceptionLocation(this.m_pw, sAXParseException);
        this.m_pw.println("error: " + sAXParseException.getMessage());
        this.m_pw.flush();
        if (getThrowOnError()) {
            throw sAXParseException;
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        logExceptionLocation(this.m_pw, sAXParseException);
        this.m_pw.println("fatalError: " + sAXParseException.getMessage());
        this.m_pw.flush();
        if (getThrowOnFatalError()) {
            throw sAXParseException;
        }
    }

    public boolean getThrowOnError() {
        return this.throwOnError;
    }

    public boolean getThrowOnFatalError() {
        return this.throwOnFatalError;
    }

    public boolean getThrowOnWarning() {
        return this.throwOnWarning;
    }

    public void setThrowOnError(boolean z) {
        this.throwOnError = z;
    }

    public void setThrowOnFatalError(boolean z) {
        this.throwOnFatalError = z;
    }

    public void setThrowOnWarning(boolean z) {
        this.throwOnWarning = z;
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
        logExceptionLocation(this.m_pw, sAXParseException);
        this.m_pw.println("warning: " + sAXParseException.getMessage());
        this.m_pw.flush();
        if (getThrowOnWarning()) {
            throw sAXParseException;
        }
    }

    public ListingErrorHandler() {
        this.m_pw = null;
        this.m_pw = new PrintWriter((OutputStream) System.err, true);
    }

    @Override // javax.xml.transform.ErrorListener
    public void error(TransformerException transformerException) throws TransformerException {
        logExceptionLocation(this.m_pw, transformerException);
        this.m_pw.println("error: " + transformerException.getMessage());
        this.m_pw.flush();
        if (getThrowOnError()) {
            throw transformerException;
        }
    }

    @Override // javax.xml.transform.ErrorListener
    public void fatalError(TransformerException transformerException) throws TransformerException {
        logExceptionLocation(this.m_pw, transformerException);
        this.m_pw.println("error: " + transformerException.getMessage());
        this.m_pw.flush();
        if (getThrowOnError()) {
            throw transformerException;
        }
    }

    @Override // javax.xml.transform.ErrorListener
    public void warning(TransformerException transformerException) throws TransformerException {
        logExceptionLocation(this.m_pw, transformerException);
        this.m_pw.println("warning: " + transformerException.getMessage());
        this.m_pw.flush();
        if (getThrowOnWarning()) {
            throw transformerException;
        }
    }
}
