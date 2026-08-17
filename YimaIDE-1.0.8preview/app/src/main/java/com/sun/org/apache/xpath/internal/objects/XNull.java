package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XNull extends XNodeSet {
    static final long serialVersionUID = -6841683711458983005L;

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public boolean bool() {
        return false;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public boolean equals(XObject xObject) {
        return xObject.getType() == -1;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public int getType() {
        return -1;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public String getTypeString() {
        return "#CLASS_NULL";
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public double num() {
        return XPath.MATCH_SCORE_QNAME;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public int rtf(XPathContext xPathContext) {
        return -1;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XNodeSet, com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        return "";
    }
}
