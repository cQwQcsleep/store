package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import javax.xml.transform.Source;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XMLInputSourceAdaptor implements Source {
    public final XMLInputSource fSource;

    public XMLInputSourceAdaptor(XMLInputSource xMLInputSource) {
        this.fSource = xMLInputSource;
    }

    @Override // javax.xml.transform.Source
    public String getSystemId() {
        try {
            return XMLEntityManager.expandSystemId(this.fSource.getSystemId(), this.fSource.getBaseSystemId(), false);
        } catch (URI.MalformedURIException unused) {
            return this.fSource.getSystemId();
        }
    }

    @Override // javax.xml.transform.Source
    public void setSystemId(String str) {
        this.fSource.setSystemId(str);
    }
}
