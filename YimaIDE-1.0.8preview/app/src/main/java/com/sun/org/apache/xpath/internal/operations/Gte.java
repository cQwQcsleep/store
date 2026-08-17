package com.sun.org.apache.xpath.internal.operations;

import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Gte extends Operation {
    static final long serialVersionUID = 9142945909906680220L;

    @Override // com.sun.org.apache.xpath.internal.operations.Operation
    public XObject operate(XObject xObject, XObject xObject2) throws TransformerException {
        return xObject.greaterThanOrEqual(xObject2) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}
