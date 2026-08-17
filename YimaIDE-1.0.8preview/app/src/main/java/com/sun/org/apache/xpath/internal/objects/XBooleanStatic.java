package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XBooleanStatic extends XBoolean {
    static final long serialVersionUID = -8064147275772687409L;
    private final boolean m_val;

    public XBooleanStatic(boolean z) {
        super(z);
        this.m_val = z;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XBoolean, com.sun.org.apache.xpath.internal.objects.XObject
    public boolean equals(XObject xObject) {
        try {
            return this.m_val == xObject.bool();
        } catch (TransformerException e) {
            throw new WrappedRuntimeException(e);
        }
    }
}
