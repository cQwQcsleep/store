package com.sun.org.apache.xerces.internal.xpointer;

import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class XPointerErrorHandler implements XMLErrorHandler {
    protected PrintWriter fOut;

    public XPointerErrorHandler() {
        this(new PrintWriter(System.err));
    }

    private void printError(String str, XMLParseException xMLParseException) {
        this.fOut.print("[");
        this.fOut.print(str);
        this.fOut.print("] ");
        String expandedSystemId = xMLParseException.getExpandedSystemId();
        if (expandedSystemId != null) {
            int iLastIndexOf = expandedSystemId.lastIndexOf(47);
            if (iLastIndexOf != -1) {
                expandedSystemId = expandedSystemId.substring(iLastIndexOf + 1);
            }
            this.fOut.print(expandedSystemId);
        }
        this.fOut.print(':');
        this.fOut.print(xMLParseException.getLineNumber());
        this.fOut.print(':');
        this.fOut.print(xMLParseException.getColumnNumber());
        this.fOut.print(": ");
        this.fOut.print(xMLParseException.getMessage());
        this.fOut.println();
        this.fOut.flush();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void error(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        printError("Error", xMLParseException);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void fatalError(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        printError("Fatal Error", xMLParseException);
        throw xMLParseException;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler
    public void warning(String str, String str2, XMLParseException xMLParseException) throws XNIException {
        printError("Warning", xMLParseException);
    }

    public XPointerErrorHandler(PrintWriter printWriter) {
        this.fOut = printWriter;
    }
}
