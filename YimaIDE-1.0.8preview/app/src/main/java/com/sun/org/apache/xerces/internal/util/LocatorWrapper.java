package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import org.xml.sax.Locator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocatorWrapper implements XMLLocator {
    private final Locator locator;

    public LocatorWrapper(Locator locator) {
        this.locator = locator;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getBaseSystemId() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getCharacterOffset() {
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getColumnNumber() {
        return this.locator.getColumnNumber();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getEncoding() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getExpandedSystemId() {
        return this.locator.getSystemId();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getLineNumber() {
        return this.locator.getLineNumber();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getLiteralSystemId() {
        return this.locator.getSystemId();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getPublicId() {
        return this.locator.getPublicId();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getXMLVersion() {
        return null;
    }
}
