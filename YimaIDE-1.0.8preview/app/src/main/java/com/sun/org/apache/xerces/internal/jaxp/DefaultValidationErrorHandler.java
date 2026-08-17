package com.sun.org.apache.xerces.internal.jaxp;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xerces.internal.util.SAXMessageFormatter;
import java.util.Locale;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class DefaultValidationErrorHandler extends DefaultHandler {
    private static int ERROR_COUNT_LIMIT = 10;
    private int errorCount = 0;
    private Locale locale;

    public DefaultValidationErrorHandler(Locale locale) {
        Locale.getDefault();
        this.locale = locale;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        int i = this.errorCount;
        if (i >= ERROR_COUNT_LIMIT) {
            return;
        }
        if (i == 0) {
            System.err.println(SAXMessageFormatter.formatMessage(this.locale, "errorHandlerNotSet", new Object[]{Integer.valueOf(i)}));
        }
        String systemId = sAXParseException.getSystemId();
        if (systemId == null) {
            systemId = PsiKeyword.NULL;
        }
        System.err.println("Error: URI=" + systemId + " Line=" + sAXParseException.getLineNumber() + ": " + sAXParseException.getMessage());
        this.errorCount = this.errorCount + 1;
    }
}
