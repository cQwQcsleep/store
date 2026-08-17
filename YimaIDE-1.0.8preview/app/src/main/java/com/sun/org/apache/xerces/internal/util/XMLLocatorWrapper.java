package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XMLLocatorWrapper implements XMLLocator {
    private XMLLocator fLocator = null;

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getBaseSystemId() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getBaseSystemId();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getCharacterOffset() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getCharacterOffset();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getColumnNumber() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getColumnNumber();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getEncoding() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getEncoding();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getExpandedSystemId() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getExpandedSystemId();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getLineNumber() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getLineNumber();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getLiteralSystemId() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getLiteralSystemId();
        }
        return null;
    }

    public XMLLocator getLocator() {
        return this.fLocator;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getPublicId() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getPublicId();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getXMLVersion() {
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            return xMLLocator.getXMLVersion();
        }
        return null;
    }

    public void setLocator(XMLLocator xMLLocator) {
        this.fLocator = xMLLocator;
    }
}
