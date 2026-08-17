package com.sun.org.apache.xml.internal.serializer.dom3;

import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
final class DOMErrorHandlerImpl implements DOMErrorHandler {
    @Override // org.w3c.dom.DOMErrorHandler
    public boolean handleError(DOMError dOMError) {
        String str;
        boolean z = true;
        if (dOMError.getSeverity() == 1) {
            z = false;
            str = "[Warning]";
        } else if (dOMError.getSeverity() == 2) {
            str = "[Error]";
        } else {
            str = dOMError.getSeverity() == 3 ? "[Fatal Error]" : null;
        }
        System.err.println(str + ": " + dOMError.getMessage() + "\t");
        System.err.println("Type : " + dOMError.getType() + "\tRelated Data: " + dOMError.getRelatedData() + "\tRelated Exception: " + dOMError.getRelatedException());
        return z;
    }
}
