package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.XPath;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XBoolean extends XObject {
    static final long serialVersionUID = -2964933058866100881L;
    private final boolean m_val;
    public static final XBoolean S_TRUE = new XBooleanStatic(true);
    public static final XBoolean S_FALSE = new XBooleanStatic(false);

    public XBoolean(Boolean bool) {
        this.m_val = bool.booleanValue();
        setObject(bool);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean bool() {
        return this.m_val;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean equals(XObject xObject) {
        if (xObject.getType() == 4) {
            return xObject.equals((XObject) this);
        }
        try {
            return this.m_val == xObject.bool();
        } catch (TransformerException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public int getType() {
        return 1;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String getTypeString() {
        return "#BOOLEAN";
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public double num() {
        if (this.m_val) {
            return 1.0d;
        }
        return XPath.MATCH_SCORE_QNAME;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public Object object() {
        if (this.m_obj == null) {
            setObject(Boolean.valueOf(this.m_val));
        }
        return this.m_obj;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        return this.m_val ? "true" : "false";
    }

    public XBoolean(boolean z) {
        this.m_val = z;
    }
}
