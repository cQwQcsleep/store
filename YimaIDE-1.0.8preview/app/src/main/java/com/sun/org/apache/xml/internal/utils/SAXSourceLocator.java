package com.sun.org.apache.xml.internal.utils;

import java.io.Serializable;
import javax.xml.transform.SourceLocator;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SAXSourceLocator extends LocatorImpl implements SourceLocator, Serializable {
    static final long serialVersionUID = 3181680946321164112L;
    Locator m_locator;

    public SAXSourceLocator(SourceLocator sourceLocator) {
        this.m_locator = null;
        setColumnNumber(sourceLocator.getColumnNumber());
        setLineNumber(sourceLocator.getLineNumber());
        setPublicId(sourceLocator.getPublicId());
        setSystemId(sourceLocator.getSystemId());
    }

    @Override // org.xml.sax.helpers.LocatorImpl, org.xml.sax.Locator
    public int getColumnNumber() {
        Locator locator = this.m_locator;
        return locator == null ? super.getColumnNumber() : locator.getColumnNumber();
    }

    @Override // org.xml.sax.helpers.LocatorImpl, org.xml.sax.Locator
    public int getLineNumber() {
        Locator locator = this.m_locator;
        return locator == null ? super.getLineNumber() : locator.getLineNumber();
    }

    @Override // org.xml.sax.helpers.LocatorImpl, org.xml.sax.Locator
    public String getPublicId() {
        Locator locator = this.m_locator;
        return locator == null ? super.getPublicId() : locator.getPublicId();
    }

    @Override // org.xml.sax.helpers.LocatorImpl, org.xml.sax.Locator
    public String getSystemId() {
        Locator locator = this.m_locator;
        return locator == null ? super.getSystemId() : locator.getSystemId();
    }

    public SAXSourceLocator(Locator locator) {
        this.m_locator = locator;
        setColumnNumber(locator.getColumnNumber());
        setLineNumber(locator.getLineNumber());
        setPublicId(locator.getPublicId());
        setSystemId(locator.getSystemId());
    }

    public SAXSourceLocator() {
    }

    public SAXSourceLocator(SAXParseException sAXParseException) {
        setLineNumber(sAXParseException.getLineNumber());
        setColumnNumber(sAXParseException.getColumnNumber());
        setPublicId(sAXParseException.getPublicId());
        setSystemId(sAXParseException.getSystemId());
    }
}
