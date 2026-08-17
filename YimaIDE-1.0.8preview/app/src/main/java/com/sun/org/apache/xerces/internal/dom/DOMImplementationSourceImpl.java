package com.sun.org.apache.xerces.internal.dom;

import java.util.ArrayList;
import java.util.StringTokenizer;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;
import org.w3c.dom.DOMImplementationSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMImplementationSourceImpl implements DOMImplementationSource {
    @Override // org.w3c.dom.DOMImplementationSource
    public DOMImplementation getDOMImplementation(String str) {
        DOMImplementation dOMImplementation = CoreDOMImplementationImpl.getDOMImplementation();
        if (testImpl(dOMImplementation, str)) {
            return dOMImplementation;
        }
        DOMImplementation dOMImplementation2 = DOMImplementationImpl.getDOMImplementation();
        if (testImpl(dOMImplementation2, str)) {
            return dOMImplementation2;
        }
        return null;
    }

    @Override // org.w3c.dom.DOMImplementationSource
    public DOMImplementationList getDOMImplementationList(String str) {
        DOMImplementation dOMImplementation = CoreDOMImplementationImpl.getDOMImplementation();
        ArrayList arrayList = new ArrayList();
        if (testImpl(dOMImplementation, str)) {
            arrayList.add(dOMImplementation);
        }
        DOMImplementation dOMImplementation2 = DOMImplementationImpl.getDOMImplementation();
        if (testImpl(dOMImplementation2, str)) {
            arrayList.add(dOMImplementation2);
        }
        return new DOMImplementationListImpl(arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0033  */
    /* JADX WARN: Code duplicated, block: B:25:0x0045  */
    /* JADX WARN: Code duplicated, block: B:29:0x003e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0032 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x0039 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:32:0x0011 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x002c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0012, code lost:
    
        r7 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean testImpl(DOMImplementation dOMImplementation, String str) {
        String strNextToken;
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        if (stringTokenizer.hasMoreTokens()) {
            String strNextToken2 = stringTokenizer.nextToken();
            while (true) {
                boolean z = true;
                if (strNextToken2 == null) {
                    return true;
                }
                if (stringTokenizer.hasMoreTokens()) {
                    strNextToken = stringTokenizer.nextToken();
                    switch (strNextToken.charAt(0)) {
                    }
                    if (z) {
                        if (!dOMImplementation.hasFeature(strNextToken2, strNextToken)) {
                            return false;
                        }
                        strNextToken2 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : null;
                    } else {
                        if (!dOMImplementation.hasFeature(strNextToken2, null)) {
                            return false;
                        }
                        strNextToken2 = strNextToken;
                    }
                } else {
                    strNextToken = null;
                }
                z = false;
                if (z) {
                    if (!dOMImplementation.hasFeature(strNextToken2, strNextToken)) {
                        return false;
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                    }
                } else {
                    if (!dOMImplementation.hasFeature(strNextToken2, null)) {
                        return false;
                    }
                    strNextToken2 = strNextToken;
                }
            }
        }
    }
}
