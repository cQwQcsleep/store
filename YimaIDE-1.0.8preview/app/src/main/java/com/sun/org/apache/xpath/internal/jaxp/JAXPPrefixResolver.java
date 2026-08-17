package com.sun.org.apache.xpath.internal.jaxp;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import javax.xml.namespace.NamespaceContext;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JAXPPrefixResolver implements PrefixResolver {
    public static final String S_XMLNAMESPACEURI = "http://www.w3.org/XML/1998/namespace";
    private NamespaceContext namespaceContext;

    public JAXPPrefixResolver(NamespaceContext namespaceContext) {
        this.namespaceContext = namespaceContext;
    }

    @Override // com.sun.org.apache.xml.internal.utils.PrefixResolver
    public String getBaseIdentifier() {
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.utils.PrefixResolver
    public String getNamespaceForPrefix(String str, Node node) {
        if (str.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        String nodeValue = null;
        while (node != null && nodeValue == null) {
            short nodeType = node.getNodeType();
            if (nodeType != 1 && nodeType != 5) {
                break;
            }
            if (nodeType == 1) {
                NamedNodeMap attributes = node.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node nodeItem = attributes.item(i);
                    String nodeName = nodeItem.getNodeName();
                    boolean zStartsWith = nodeName.startsWith("xmlns:");
                    if (zStartsWith || nodeName.equals("xmlns")) {
                        if ((zStartsWith ? nodeName.substring(nodeName.indexOf(58) + 1) : "").equals(str)) {
                            nodeValue = nodeItem.getNodeValue();
                            break;
                        }
                    }
                }
            }
            node = node.getParentNode();
        }
        return nodeValue;
    }

    @Override // com.sun.org.apache.xml.internal.utils.PrefixResolver
    public boolean handlesNullPrefixes() {
        return false;
    }

    @Override // com.sun.org.apache.xml.internal.utils.PrefixResolver
    public String getNamespaceForPrefix(String str) {
        return this.namespaceContext.getNamespaceURI(str);
    }
}
