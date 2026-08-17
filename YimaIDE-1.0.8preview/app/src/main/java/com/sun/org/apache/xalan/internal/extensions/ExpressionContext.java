package com.sun.org.apache.xalan.internal.extensions;

import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xpath.internal.XPathContext;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ExpressionContext {
    Node getContextNode();

    NodeIterator getContextNodes();

    ErrorListener getErrorListener();

    XObject getVariableOrParam(QName qName) throws TransformerException;

    XPathContext getXPathContext() throws TransformerException;

    double toNumber(Node node);

    String toString(Node node);
}
