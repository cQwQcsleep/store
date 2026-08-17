package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.dtm.DTMManager;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;
import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xml.internal.utils.XMLString;
import com.sun.org.apache.xpath.internal.NodeSetDTM;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.axes.NodeSequence;
import java.util.ArrayList;
import javax.xml.transform.TransformerException;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XNodeSet extends NodeSequence {
    static final long serialVersionUID = 1916026368035639667L;
    static final LessThanComparator S_LT = new LessThanComparator();
    static final LessThanOrEqualComparator S_LTE = new LessThanOrEqualComparator();
    static final GreaterThanComparator S_GT = new GreaterThanComparator();
    static final GreaterThanOrEqualComparator S_GTE = new GreaterThanOrEqualComparator();
    static final EqualComparator S_EQ = new EqualComparator();
    static final NotEqualComparator S_NEQ = new NotEqualComparator();

    public XNodeSet(DTMIterator dTMIterator) {
        if (!(dTMIterator instanceof XNodeSet)) {
            setIter(dTMIterator);
            return;
        }
        XNodeSet xNodeSet = (XNodeSet) dTMIterator;
        setIter(xNodeSet.m_iter);
        this.m_dtmMgr = xNodeSet.m_dtmMgr;
        this.m_last = xNodeSet.m_last;
        if (!xNodeSet.hasCache()) {
            xNodeSet.setShouldCacheNodes(true);
        }
        setObject(xNodeSet.getIteratorCache());
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public void appendToFsb(FastStringBuffer fastStringBuffer) {
        ((XString) xstr()).appendToFsb(fastStringBuffer);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean bool() {
        return item(0) != -1;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean boolWithSideEffects() {
        return nextNode() != -1;
    }

    public boolean compare(XObject xObject, Comparator comparator) throws TransformerException {
        int iNextNode;
        int iNextNode2;
        int iNextNode3;
        int type = xObject.getType();
        boolean z = true;
        if (4 != type) {
            if (1 == type) {
                return comparator.compareNumbers(bool() ? 1.0d : XPath.MATCH_SCORE_QNAME, xObject.num());
            }
            if (2 == type) {
                DTMIterator dTMIteratorIterRaw = iterRaw();
                double dNum = xObject.num();
                do {
                    iNextNode3 = dTMIteratorIterRaw.nextNode();
                    if (-1 == iNextNode3) {
                        z = false;
                        break;
                    }
                } while (!comparator.compareNumbers(getNumberFromNode(iNextNode3), dNum));
                dTMIteratorIterRaw.reset();
                return z;
            }
            if (5 == type) {
                XMLString xMLStringXstr = xObject.xstr();
                DTMIterator dTMIteratorIterRaw2 = iterRaw();
                do {
                    iNextNode2 = dTMIteratorIterRaw2.nextNode();
                    if (-1 == iNextNode2) {
                        z = false;
                        break;
                    }
                } while (!comparator.compareStrings(getStringFromNode(iNextNode2), xMLStringXstr));
                dTMIteratorIterRaw2.reset();
                return z;
            }
            if (3 != type) {
                return comparator.compareNumbers(num(), xObject.num());
            }
            XMLString xMLStringXstr2 = xObject.xstr();
            DTMIterator dTMIteratorIterRaw3 = iterRaw();
            do {
                iNextNode = dTMIteratorIterRaw3.nextNode();
                if (-1 == iNextNode) {
                    z = false;
                    break;
                }
            } while (!comparator.compareStrings(getStringFromNode(iNextNode), xMLStringXstr2));
            dTMIteratorIterRaw3.reset();
            return z;
        }
        DTMIterator dTMIteratorIterRaw4 = iterRaw();
        DTMIterator dTMIteratorIterRaw5 = ((XNodeSet) xObject).iterRaw();
        ArrayList arrayList = null;
        boolean z2 = false;
        while (true) {
            int iNextNode4 = dTMIteratorIterRaw4.nextNode();
            if (-1 == iNextNode4) {
                dTMIteratorIterRaw4.reset();
                dTMIteratorIterRaw5.reset();
                return z2;
            }
            XMLString stringFromNode = getStringFromNode(iNextNode4);
            if (arrayList == null) {
                while (true) {
                    int iNextNode5 = dTMIteratorIterRaw5.nextNode();
                    if (-1 == iNextNode5) {
                        break;
                    }
                    XMLString stringFromNode2 = getStringFromNode(iNextNode5);
                    if (comparator.compareStrings(stringFromNode, stringFromNode2)) {
                        z2 = true;
                        break;
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(stringFromNode2);
                }
            } else {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    if (comparator.compareStrings(stringFromNode, (XMLString) arrayList.get(i))) {
                        z2 = true;
                        break;
                        break;
                    }
                }
            }
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public void dispatchCharactersEvents(ContentHandler contentHandler) throws SAXException {
        int iItem = item(0);
        if (iItem != -1) {
            this.m_dtmMgr.getDTM(iItem).dispatchCharactersEvents(iItem, contentHandler, false);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean equals(XObject xObject) {
        try {
            return compare(xObject, S_EQ);
        } catch (TransformerException e) {
            throw new WrappedRuntimeException(e);
        }
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public XObject getFresh() {
        try {
            return hasCache() ? (XObject) cloneWithReset() : this;
        } catch (CloneNotSupportedException e) {
            f63.a(e.getMessage());
            return null;
        }
    }

    public double getNumberFromNode(int i) {
        return this.m_dtmMgr.getDTM(i).getStringValue(i).toDouble();
    }

    public XMLString getStringFromNode(int i) {
        return -1 != i ? this.m_dtmMgr.getDTM(i).getStringValue(i) : XString.EMPTYSTRING;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public int getType() {
        return 4;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String getTypeString() {
        return "#NODESET";
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean greaterThan(XObject xObject) throws TransformerException {
        return compare(xObject, S_GT);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean greaterThanOrEqual(XObject xObject) throws TransformerException {
        return compare(xObject, S_GTE);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public DTMIterator iter() {
        try {
            return hasCache() ? cloneWithReset() : this;
        } catch (CloneNotSupportedException e) {
            f63.a(e.getMessage());
            return null;
        }
    }

    public DTMIterator iterRaw() {
        return this;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean lessThan(XObject xObject) throws TransformerException {
        return compare(xObject, S_LT);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean lessThanOrEqual(XObject xObject) throws TransformerException {
        return compare(xObject, S_LTE);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public NodeSetDTM mutableNodeset() {
        Object obj = this.m_obj;
        if (obj instanceof NodeSetDTM) {
            return (NodeSetDTM) obj;
        }
        NodeSetDTM nodeSetDTM = new NodeSetDTM(iter());
        setObject(nodeSetDTM);
        setCurrentPos(0);
        return nodeSetDTM;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public NodeList nodelist() throws TransformerException {
        DTMNodeList dTMNodeList = new DTMNodeList(this);
        SetVector(((XNodeSet) dTMNodeList.getDTMIterator()).getVector());
        return dTMNodeList;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public NodeIterator nodeset() throws TransformerException {
        return new DTMNodeIterator(iter());
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public boolean notEquals(XObject xObject) throws TransformerException {
        return compare(xObject, S_NEQ);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public double num() {
        int iItem = item(0);
        if (iItem != -1) {
            return getNumberFromNode(iItem);
        }
        return Double.NaN;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public double numWithSideEffects() {
        int iNextNode = nextNode();
        if (iNextNode != -1) {
            return getNumberFromNode(iNextNode);
        }
        return Double.NaN;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public Object object() {
        Object obj = this.m_obj;
        return obj == null ? this : obj;
    }

    public void release(DTMIterator dTMIterator) {
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        int iItem = item(0);
        return iItem != -1 ? getStringFromNode(iItem).toString() : "";
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public XMLString xstr() {
        int iItem = item(0);
        return iItem != -1 ? getStringFromNode(iItem) : XString.EMPTYSTRING;
    }

    public XNodeSet() {
    }

    public XNodeSet(XNodeSet xNodeSet) {
        setIter(xNodeSet.m_iter);
        this.m_dtmMgr = xNodeSet.m_dtmMgr;
        this.m_last = xNodeSet.m_last;
        if (!xNodeSet.hasCache()) {
            xNodeSet.setShouldCacheNodes(true);
        }
        setObject(xNodeSet.m_obj);
    }

    public XNodeSet(DTMManager dTMManager) {
        this(-1, dTMManager);
    }

    public XNodeSet(int i, DTMManager dTMManager) {
        super(new NodeSetDTM(dTMManager));
        this.m_dtmMgr = dTMManager;
        if (-1 != i) {
            ((NodeSetDTM) this.m_obj).addNode(i);
            this.m_last = 1;
        } else {
            this.m_last = 0;
        }
    }
}
