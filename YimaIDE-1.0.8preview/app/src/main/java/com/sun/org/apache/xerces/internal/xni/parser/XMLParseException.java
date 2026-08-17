package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XNIException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLParseException extends XNIException {
    static final long serialVersionUID = 1732959359448549967L;
    protected String fBaseSystemId;
    protected int fCharacterOffset;
    protected int fColumnNumber;
    protected String fExpandedSystemId;
    protected int fLineNumber;
    protected String fLiteralSystemId;
    protected String fPublicId;

    public XMLParseException(XMLLocator xMLLocator, String str) {
        super(str);
        this.fLineNumber = -1;
        this.fColumnNumber = -1;
        this.fCharacterOffset = -1;
        if (xMLLocator != null) {
            this.fPublicId = xMLLocator.getPublicId();
            this.fLiteralSystemId = xMLLocator.getLiteralSystemId();
            this.fExpandedSystemId = xMLLocator.getExpandedSystemId();
            this.fBaseSystemId = xMLLocator.getBaseSystemId();
            this.fLineNumber = xMLLocator.getLineNumber();
            this.fColumnNumber = xMLLocator.getColumnNumber();
            this.fCharacterOffset = xMLLocator.getCharacterOffset();
        }
    }

    public String getBaseSystemId() {
        return this.fBaseSystemId;
    }

    public int getCharacterOffset() {
        return this.fCharacterOffset;
    }

    public int getColumnNumber() {
        return this.fColumnNumber;
    }

    public String getExpandedSystemId() {
        return this.fExpandedSystemId;
    }

    public int getLineNumber() {
        return this.fLineNumber;
    }

    public String getLiteralSystemId() {
        return this.fLiteralSystemId;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    @Override // java.lang.Throwable
    public String toString() {
        Exception exception;
        StringBuffer stringBuffer = new StringBuffer();
        String str = this.fPublicId;
        if (str != null) {
            stringBuffer.append(str);
        }
        stringBuffer.append(':');
        String str2 = this.fLiteralSystemId;
        if (str2 != null) {
            stringBuffer.append(str2);
        }
        stringBuffer.append(':');
        String str3 = this.fExpandedSystemId;
        if (str3 != null) {
            stringBuffer.append(str3);
        }
        stringBuffer.append(':');
        String str4 = this.fBaseSystemId;
        if (str4 != null) {
            stringBuffer.append(str4);
        }
        stringBuffer.append(':');
        stringBuffer.append(this.fLineNumber);
        stringBuffer.append(':');
        stringBuffer.append(this.fColumnNumber);
        stringBuffer.append(':');
        stringBuffer.append(this.fCharacterOffset);
        stringBuffer.append(':');
        String message = getMessage();
        if (message == null && (exception = getException()) != null) {
            message = exception.getMessage();
        }
        if (message != null) {
            stringBuffer.append(message);
        }
        return stringBuffer.toString();
    }

    public XMLParseException(XMLLocator xMLLocator, String str, Exception exc) {
        super(str, exc);
        this.fLineNumber = -1;
        this.fColumnNumber = -1;
        this.fCharacterOffset = -1;
        if (xMLLocator != null) {
            this.fPublicId = xMLLocator.getPublicId();
            this.fLiteralSystemId = xMLLocator.getLiteralSystemId();
            this.fExpandedSystemId = xMLLocator.getExpandedSystemId();
            this.fBaseSystemId = xMLLocator.getBaseSystemId();
            this.fLineNumber = xMLLocator.getLineNumber();
            this.fColumnNumber = xMLLocator.getColumnNumber();
            this.fCharacterOffset = xMLLocator.getCharacterOffset();
        }
    }
}
