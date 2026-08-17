package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.util.List;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XRTreeFragSelectWrapper extends XRTreeFrag implements Cloneable {
    static final long serialVersionUID = -6526177905590461251L;

    public XRTreeFragSelectWrapper(Expression expression) {
        super(expression);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XRTreeFrag
    public DTMIterator asNodeIterator() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_ASNODEITERATOR_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XRTreeFrag, com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_DETACH_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        XObject xObjectExecute = ((Expression) this.m_obj).execute(xPathContext);
        xObjectExecute.allowDetachToRelease(this.m_allowRelease);
        return xObjectExecute.getType() == 3 ? xObjectExecute : new XString(xObjectExecute.str());
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
        ((Expression) this.m_obj).fixupVariables(list, i);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XRTreeFrag, com.sun.org.apache.xpath.internal.objects.XObject
    public int getType() {
        return 3;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XRTreeFrag, com.sun.org.apache.xpath.internal.objects.XObject
    public double num() throws TransformerException {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NUM_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XRTreeFrag, com.sun.org.apache.xpath.internal.objects.XObject
    public int rtf() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_RTF_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XRTreeFrag, com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_STR_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XRTreeFrag, com.sun.org.apache.xpath.internal.objects.XObject
    public XMLString xstr() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_XSTR_NOT_SUPPORTED_XRTREEFRAGSELECTWRAPPER", null));
    }
}
