package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.dtm.DTM;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.axes.OneStepIterator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XObjectFactory {
    public static XObject create(Object obj, XPathContext xPathContext) {
        if (obj instanceof XObject) {
            return (XObject) obj;
        }
        if (obj instanceof String) {
            return new XString((String) obj);
        }
        if (obj instanceof Boolean) {
            return new XBoolean((Boolean) obj);
        }
        if (obj instanceof Number) {
            return new XNumber((Number) obj);
        }
        if (obj instanceof DTM) {
            DTM dtm = (DTM) obj;
            try {
                int document = dtm.getDocument();
                DTMAxisIterator axisIterator = dtm.getAxisIterator(13);
                axisIterator.setStartNode(document);
                OneStepIterator oneStepIterator = new OneStepIterator(axisIterator, 13);
                oneStepIterator.setRoot(document, xPathContext);
                return new XNodeSet(oneStepIterator);
            } catch (Exception e) {
                throw new WrappedRuntimeException(e);
            }
        }
        if (obj instanceof DTMAxisIterator) {
            DTMAxisIterator dTMAxisIterator = (DTMAxisIterator) obj;
            try {
                OneStepIterator oneStepIterator2 = new OneStepIterator(dTMAxisIterator, 13);
                oneStepIterator2.setRoot(dTMAxisIterator.getStartNode(), xPathContext);
                return new XNodeSet(oneStepIterator2);
            } catch (Exception e2) {
                throw new WrappedRuntimeException(e2);
            }
        }
        if (obj instanceof DTMIterator) {
            return new XNodeSet((DTMIterator) obj);
        }
        if (obj instanceof Node) {
            return new XNodeSetForDOM((Node) obj, xPathContext);
        }
        if (obj instanceof NodeList) {
            return new XNodeSetForDOM((NodeList) obj, xPathContext);
        }
        return obj instanceof NodeIterator ? new XNodeSetForDOM((NodeIterator) obj, xPathContext) : new XObject(obj);
    }

    public static XObject create(Object obj) {
        if (obj instanceof XObject) {
            return (XObject) obj;
        }
        if (obj instanceof String) {
            return new XString((String) obj);
        }
        if (obj instanceof Boolean) {
            return new XBoolean((Boolean) obj);
        }
        if (obj instanceof Double) {
            return new XNumber((Double) obj);
        }
        return new XObject(obj);
    }
}
