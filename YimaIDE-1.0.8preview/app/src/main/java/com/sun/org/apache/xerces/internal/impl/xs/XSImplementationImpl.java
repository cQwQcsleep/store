package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.dom.CoreDOMImplementationImpl;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSException;
import com.sun.org.apache.xerces.internal.xs.XSImplementation;
import com.sun.org.apache.xerces.internal.xs.XSLoader;
import org.w3c.dom.DOMImplementation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSImplementationImpl extends CoreDOMImplementationImpl implements XSImplementation {
    static XSImplementationImpl singleton = new XSImplementationImpl();

    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSImplementation
    public XSLoader createXSLoader(StringList stringList) throws XSException {
        XSLoaderImpl xSLoaderImpl = new XSLoaderImpl();
        if (stringList != null) {
            for (int i = 0; i < stringList.getLength(); i++) {
                if (!stringList.item(i).equals("1.0")) {
                    throw new XSException((short) 1, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{stringList.item(i)}));
                }
            }
        }
        return xSLoaderImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSImplementation
    public StringList getRecognizedVersions() {
        return new StringListImpl(new String[]{"1.0"}, 1);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDOMImplementationImpl, org.w3c.dom.DOMImplementation
    public boolean hasFeature(String str, String str2) {
        return (str.equalsIgnoreCase("XS-Loader") && (str2 == null || str2.equals("1.0"))) || super.hasFeature(str, str2);
    }
}
