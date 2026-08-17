package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.ExpressionNode;
import com.sun.org.apache.xpath.internal.ExpressionOwner;
import com.sun.org.apache.xpath.internal.NodeSetDTM;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.XPathException;
import com.sun.org.apache.xpath.internal.XPathVisitor;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import java.io.Serializable;
import java.util.List;
import javax.xml.transform.TransformerException;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XObject extends Expression implements Serializable, Cloneable {
    public static final int CLASS_BOOLEAN = 1;
    public static final int CLASS_NODESET = 4;
    public static final int CLASS_NULL = -1;
    public static final int CLASS_NUMBER = 2;
    public static final int CLASS_RTREEFRAG = 5;
    public static final int CLASS_STRING = 3;
    public static final int CLASS_UNKNOWN = 0;
    public static final int CLASS_UNRESOLVEDVARIABLE = 600;
    static final long serialVersionUID = -821887098985662951L;
    protected Object m_obj;

    public XObject(Object obj) {
        setObject(obj);
    }

    public static XObject create(Object obj) {
        return XObjectFactory.create(obj);
    }

    public void allowDetachToRelease(boolean z) {
    }

    public void appendToFsb(FastStringBuffer fastStringBuffer) {
        fastStringBuffer.append(str());
    }

    public boolean bool() throws TransformerException {
        error("ER_CANT_CONVERT_TO_NUMBER", new Object[]{getTypeString()});
        return false;
    }

    public boolean boolWithSideEffects() throws TransformerException {
        return bool();
    }

    public void callVisitors(ExpressionOwner expressionOwner, XPathVisitor xPathVisitor) {
        assertion(false, "callVisitors should not be called for this object!!!");
    }

    public Object castToType(int i, XPathContext xPathContext) throws TransformerException {
        if (i == 0) {
            return this.m_obj;
        }
        if (i == 1) {
            return Boolean.valueOf(bool());
        }
        if (i == 2) {
            return Double.valueOf(num());
        }
        if (i == 3) {
            return str();
        }
        if (i == 4) {
            return iter();
        }
        error("ER_CANT_CONVERT_TO_TYPE", new Object[]{getTypeString(), Integer.toString(i)});
        return null;
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public boolean deepEquals(Expression expression) {
        return isSameClass(expression) && equals((XObject) expression);
    }

    public void destruct() {
        if (this.m_obj != null) {
            allowDetachToRelease(true);
            detach();
            setObject(null);
        }
    }

    public void detach() {
    }

    public void dispatchCharactersEvents(ContentHandler contentHandler) throws SAXException {
        xstr().dispatchCharactersEvents(contentHandler);
    }

    public boolean equals(XObject xObject) {
        if (xObject.getType() == 4) {
            return xObject.equals(this);
        }
        Object obj = this.m_obj;
        Object obj2 = xObject.m_obj;
        if (obj != null) {
            return obj.equals(obj2);
        }
        return obj2 == null;
    }

    public void error(String str, Object[] objArr) throws TransformerException {
        throw new XPathException(XPATHMessages.createXPATHMessage(str, objArr), (ExpressionNode) this);
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public XObject execute(XPathContext xPathContext) throws TransformerException {
        return this;
    }

    @Override // com.sun.org.apache.xpath.internal.Expression
    public void fixupVariables(List<QName> list, int i) {
    }

    public XObject getFresh() {
        return this;
    }

    public int getType() {
        return 0;
    }

    public String getTypeString() {
        return "#UNKNOWN (" + object().getClass().getName() + ")";
    }

    public boolean greaterThan(XObject xObject) throws TransformerException {
        if (xObject.getType() == 4) {
            return xObject.lessThan(this);
        }
        return num() > xObject.num();
    }

    public boolean greaterThanOrEqual(XObject xObject) throws TransformerException {
        if (xObject.getType() == 4) {
            return xObject.lessThanOrEqual(this);
        }
        return num() >= xObject.num();
    }

    public DTMIterator iter() throws TransformerException {
        error("ER_CANT_CONVERT_TO_NODELIST", new Object[]{getTypeString()});
        return null;
    }

    public boolean lessThan(XObject xObject) throws TransformerException {
        if (xObject.getType() == 4) {
            return xObject.greaterThan(this);
        }
        return num() < xObject.num();
    }

    public boolean lessThanOrEqual(XObject xObject) throws TransformerException {
        if (xObject.getType() == 4) {
            return xObject.greaterThanOrEqual(this);
        }
        return num() <= xObject.num();
    }

    public NodeSetDTM mutableNodeset() throws TransformerException {
        error("ER_CANT_CONVERT_TO_MUTABLENODELIST", new Object[]{getTypeString()});
        return (NodeSetDTM) this.m_obj;
    }

    public NodeList nodelist() throws TransformerException {
        error("ER_CANT_CONVERT_TO_NODELIST", new Object[]{getTypeString()});
        return null;
    }

    public NodeIterator nodeset() throws TransformerException {
        error("ER_CANT_CONVERT_TO_NODELIST", new Object[]{getTypeString()});
        return null;
    }

    public boolean notEquals(XObject xObject) throws TransformerException {
        return xObject.getType() == 4 ? xObject.notEquals(this) : !equals(xObject);
    }

    public double num() throws TransformerException {
        error("ER_CANT_CONVERT_TO_NUMBER", new Object[]{getTypeString()});
        return XPath.MATCH_SCORE_QNAME;
    }

    public double numWithSideEffects() throws TransformerException {
        return num();
    }

    public Object object() {
        return this.m_obj;
    }

    public void reset() {
    }

    public int rtf(XPathContext xPathContext) {
        int iRtf = rtf();
        if (-1 != iRtf) {
            return iRtf;
        }
        DTM dtmCreateDocumentFragment = xPathContext.createDocumentFragment();
        dtmCreateDocumentFragment.appendTextChild(str());
        return dtmCreateDocumentFragment.getDocument();
    }

    public DocumentFragment rtree(XPathContext xPathContext) {
        int iRtf = rtf();
        if (-1 != iRtf) {
            DTM dtm = xPathContext.getDTM(iRtf);
            return (DocumentFragment) dtm.getNode(dtm.getDocument());
        }
        DTM dtmCreateDocumentFragment = xPathContext.createDocumentFragment();
        dtmCreateDocumentFragment.appendTextChild(str());
        return (DocumentFragment) dtmCreateDocumentFragment.getNode(dtmCreateDocumentFragment.getDocument());
    }

    public void setObject(Object obj) {
        this.m_obj = obj;
    }

    public String str() {
        Object obj = this.m_obj;
        return obj != null ? obj.toString() : "";
    }

    public String toString() {
        return str();
    }

    public XMLString xstr() {
        return XMLStringFactoryImpl.getFactory().newstr(str());
    }

    public static XObject create(Object obj, XPathContext xPathContext) {
        return XObjectFactory.create(obj, xPathContext);
    }

    public XObject() {
    }

    public void error(String str) throws TransformerException {
        error(str, null);
    }

    public int rtf() {
        return -1;
    }

    public DocumentFragment rtree() {
        return null;
    }
}
