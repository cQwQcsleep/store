package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;
import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionNode;
import com.sun.org.apache.xpath.internal.NodeSetDTM;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.axes.RTFIterator;
import javax.xml.transform.TransformerException;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XRTreeFrag extends XObject implements Cloneable {
    static final long serialVersionUID = -3201553822254911567L;
    private DTMXRTreeFrag m_DTMXRTreeFrag;
    protected boolean m_allowRelease;
    private int m_dtmRoot;
    private XMLString m_xmlStr;

    public XRTreeFrag(int i, XPathContext xPathContext, ExpressionNode expressionNode) {
        super(null);
        this.m_dtmRoot = -1;
        this.m_allowRelease = false;
        this.m_xmlStr = null;
        exprSetParent(expressionNode);
        initDTM(i, xPathContext);
    }

    private final void initDTM(int i, XPathContext xPathContext) {
        this.m_dtmRoot = i;
        DTM dtm = xPathContext.getDTM(i);
        if (dtm != null) {
            this.m_DTMXRTreeFrag = xPathContext.getDTMXRTreeFrag(xPathContext.getDTMIdentity(dtm));
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void allowDetachToRelease(boolean z) {
        this.m_allowRelease = z;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public void appendToFsb(FastStringBuffer fastStringBuffer) {
        ((XString) xstr()).appendToFsb(fastStringBuffer);
    }

    public DTMIterator asNodeIterator() {
        return new RTFIterator(this.m_dtmRoot, this.m_DTMXRTreeFrag.getXPathContext().getDTMManager());
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean bool() {
        return true;
    }

    public NodeList convertToNodeset() {
        Object obj = this.m_obj;
        return obj instanceof NodeList ? (NodeList) obj : new DTMNodeList(asNodeIterator());
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject, com.sun.org.apache.xml.internal.dtm.DTMIterator
    public void detach() {
        if (this.m_allowRelease) {
            this.m_DTMXRTreeFrag.destruct();
            setObject(null);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean equals(XObject xObject) {
        try {
            if (4 == xObject.getType()) {
                return xObject.equals((XObject) this);
            }
            if (1 == xObject.getType()) {
                return bool() == xObject.bool();
            }
            if (2 == xObject.getType()) {
                return num() == xObject.num();
            }
            if (4 != xObject.getType() && 3 != xObject.getType() && 5 != xObject.getType()) {
                return super.equals(xObject);
            }
            return xstr().equals(xObject.xstr());
        } catch (TransformerException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public int getType() {
        return 5;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String getTypeString() {
        return "#RTREEFRAG";
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public double num() throws TransformerException {
        return xstr().toDouble();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public Object object() {
        return this.m_DTMXRTreeFrag.getXPathContext() != null ? new DTMNodeIterator(new NodeSetDTM(this.m_dtmRoot, this.m_DTMXRTreeFrag.getXPathContext().getDTMManager())) : super.object();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public int rtf() {
        return this.m_dtmRoot;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        String string = this.m_DTMXRTreeFrag.getDTM().getStringValue(this.m_dtmRoot).toString();
        return string == null ? "" : string;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public XMLString xstr() {
        if (this.m_xmlStr == null) {
            this.m_xmlStr = this.m_DTMXRTreeFrag.getDTM().getStringValue(this.m_dtmRoot);
        }
        return this.m_xmlStr;
    }

    public XRTreeFrag(int i, XPathContext xPathContext) {
        super(null);
        this.m_dtmRoot = -1;
        this.m_allowRelease = false;
        this.m_xmlStr = null;
        initDTM(i, xPathContext);
    }

    public XRTreeFrag(Expression expression) {
        super(expression);
        this.m_dtmRoot = -1;
        this.m_allowRelease = false;
        this.m_xmlStr = null;
    }
}
