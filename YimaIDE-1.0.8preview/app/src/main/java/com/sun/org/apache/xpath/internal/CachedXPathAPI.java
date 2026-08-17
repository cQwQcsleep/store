package com.sun.org.apache.xpath.internal;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.PrefixResolverDefault;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javax.xml.transform.TransformerException;
import jdk.xml.internal.JdkConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CachedXPathAPI {
    protected XPathContext xpathSupport;

    public CachedXPathAPI() {
        this.xpathSupport = new XPathContext(JdkConstants.OVERRIDE_PARSER_DEFAULT);
    }

    public XObject eval(Node node, String str, Node node2) throws TransformerException {
        if (node2.getNodeType() == 9) {
            node2 = ((Document) node2).getDocumentElement();
        }
        PrefixResolverDefault prefixResolverDefault = new PrefixResolverDefault(node2);
        return new XPath(str, null, prefixResolverDefault, 0, null).execute(this.xpathSupport, this.xpathSupport.getDTMHandleFromNode(node), prefixResolverDefault);
    }

    public XPathContext getXPathContext() {
        return this.xpathSupport;
    }

    public NodeIterator selectNodeIterator(Node node, String str, Node node2) throws TransformerException {
        return eval(node, str, node2).nodeset();
    }

    public NodeList selectNodeList(Node node, String str, Node node2) throws TransformerException {
        return eval(node, str, node2).nodelist();
    }

    public Node selectSingleNode(Node node, String str, Node node2) throws TransformerException {
        return selectNodeIterator(node, str, node2).nextNode();
    }

    public NodeIterator selectNodeIterator(Node node, String str) throws TransformerException {
        return selectNodeIterator(node, str, node);
    }

    public NodeList selectNodeList(Node node, String str) throws TransformerException {
        return selectNodeList(node, str, node);
    }

    public Node selectSingleNode(Node node, String str) throws TransformerException {
        return selectSingleNode(node, str, node);
    }

    public CachedXPathAPI(CachedXPathAPI cachedXPathAPI) {
        this.xpathSupport = cachedXPathAPI.xpathSupport;
    }

    public XObject eval(Node node, String str) throws TransformerException {
        return eval(node, str, node);
    }

    public XObject eval(Node node, String str, PrefixResolver prefixResolver) throws TransformerException {
        XPath xPath = new XPath(str, null, prefixResolver, 0, null);
        XPathContext xPathContext = new XPathContext(JdkConstants.OVERRIDE_PARSER_DEFAULT);
        return xPath.execute(xPathContext, xPathContext.getDTMHandleFromNode(node), prefixResolver);
    }
}
