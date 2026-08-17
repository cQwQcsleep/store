package org.xml.sax.helpers;

import org.xml.sax.Locator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocatorImpl implements Locator {
    private int columnNumber;
    private int lineNumber;
    private String publicId;
    private String systemId;

    public LocatorImpl(Locator locator) {
        setPublicId(locator.getPublicId());
        setSystemId(locator.getSystemId());
        setLineNumber(locator.getLineNumber());
        setColumnNumber(locator.getColumnNumber());
    }

    @Override // org.xml.sax.Locator
    public int getColumnNumber() {
        return this.columnNumber;
    }

    @Override // org.xml.sax.Locator
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // org.xml.sax.Locator
    public String getPublicId() {
        return this.publicId;
    }

    @Override // org.xml.sax.Locator
    public String getSystemId() {
        return this.systemId;
    }

    public void setColumnNumber(int i) {
        this.columnNumber = i;
    }

    public void setLineNumber(int i) {
        this.lineNumber = i;
    }

    public void setPublicId(String str) {
        this.publicId = str;
    }

    public void setSystemId(String str) {
        this.systemId = str;
    }

    public LocatorImpl() {
    }
}
