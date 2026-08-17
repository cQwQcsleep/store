package com.sun.xml.internal.stream;

import com.sun.org.apache.xerces.internal.impl.PropertyManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.msg.XMLMessageFormatter;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import defpackage.knd;
import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StaxErrorReporter extends XMLErrorReporter {
    protected XMLReporter fXMLReporter = null;

    public StaxErrorReporter(PropertyManager propertyManager) {
        putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", new XMLMessageFormatter());
        reset(propertyManager);
    }

    public Location convertToStaxLocation(final XMLLocator xMLLocator) {
        return new Location() { // from class: com.sun.xml.internal.stream.StaxErrorReporter.1
            @Override // javax.xml.stream.Location
            public int getCharacterOffset() {
                return xMLLocator.getCharacterOffset();
            }

            @Override // javax.xml.stream.Location
            public int getColumnNumber() {
                return xMLLocator.getColumnNumber();
            }

            @Override // javax.xml.stream.Location
            public int getLineNumber() {
                return xMLLocator.getLineNumber();
            }

            public String getLocationURI() {
                return "";
            }

            @Override // javax.xml.stream.Location
            public String getPublicId() {
                return xMLLocator.getPublicId();
            }

            @Override // javax.xml.stream.Location
            public String getSystemId() {
                return xMLLocator.getLiteralSystemId();
            }
        };
    }

    @Override // com.sun.org.apache.xerces.internal.impl.XMLErrorReporter
    public String reportError(XMLLocator xMLLocator, String str, String str2, Object[] objArr, short s) throws XNIException {
        String string;
        MessageFormatter messageFormatter = getMessageFormatter(str);
        if (messageFormatter != null) {
            string = messageFormatter.formatMessage(this.fLocale, str2, objArr);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append('#');
            stringBuffer.append(str2);
            int length = objArr != null ? objArr.length : 0;
            if (length > 0) {
                stringBuffer.append('?');
                for (int i = 0; i < length; i++) {
                    stringBuffer.append(objArr[i]);
                    if (i < length - 1) {
                        stringBuffer.append('&');
                    }
                }
            }
            string = stringBuffer.toString();
        }
        if (s == 0) {
            try {
                XMLReporter xMLReporter = this.fXMLReporter;
                if (xMLReporter != null) {
                    xMLReporter.report(string, "WARNING", (Object) null, convertToStaxLocation(xMLLocator));
                    return string;
                }
            } catch (XMLStreamException e) {
                knd.a(e);
                return null;
            }
        } else if (s == 1) {
            try {
                XMLReporter xMLReporter2 = this.fXMLReporter;
                if (xMLReporter2 != null) {
                    xMLReporter2.report(string, "ERROR", (Object) null, convertToStaxLocation(xMLLocator));
                    return string;
                }
            } catch (XMLStreamException e2) {
                knd.a(e2);
                return null;
            }
        } else if (s == 2 && !this.fContinueAfterFatalError) {
            throw new XNIException(string);
        }
        return string;
    }

    public void reset(PropertyManager propertyManager) {
        this.fXMLReporter = (XMLReporter) propertyManager.getProperty(XMLInputFactory.REPORTER);
    }

    public StaxErrorReporter() {
        putMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210", new XMLMessageFormatter());
    }
}
