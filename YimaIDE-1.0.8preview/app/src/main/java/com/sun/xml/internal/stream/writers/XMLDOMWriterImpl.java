package com.sun.xml.internal.stream.writers;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import defpackage.txf;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.dom.DOMResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.helpers.NamespaceSupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDOMWriterImpl implements XMLStreamWriterBase {
    private Node currentNode;
    private NamespaceSupport namespaceContext;
    private boolean[] needContextPop;
    private Node node;
    private Document ownerDoc;
    private StringBuffer stringBuffer;
    private int resizeValue = 20;
    private int depth = 0;

    public XMLDOMWriterImpl(DOMResult dOMResult) {
        this.ownerDoc = null;
        this.currentNode = null;
        this.node = null;
        this.namespaceContext = null;
        this.needContextPop = null;
        this.stringBuffer = null;
        Node node = dOMResult.getNode();
        this.node = node;
        short nodeType = node.getNodeType();
        Node node2 = this.node;
        if (nodeType == 9) {
            Document document = (Document) node2;
            this.ownerDoc = document;
            this.currentNode = document;
        } else {
            this.ownerDoc = node2.getOwnerDocument();
            this.currentNode = this.node;
        }
        this.stringBuffer = new StringBuffer();
        this.needContextPop = new boolean[this.resizeValue];
        this.namespaceContext = new NamespaceSupport();
    }

    private Node getNode() {
        Node node = this.currentNode;
        return node == null ? this.ownerDoc : node;
    }

    private String getQName(String str, String str2) {
        this.stringBuffer.setLength(0);
        this.stringBuffer.append(str);
        this.stringBuffer.append(":");
        this.stringBuffer.append(str2);
        return this.stringBuffer.toString();
    }

    private void incDepth() {
        int i = this.depth + 1;
        this.depth = i;
        boolean[] zArr = this.needContextPop;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[this.resizeValue + i];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.needContextPop = zArr2;
        }
    }

    public void close() throws XMLStreamException {
    }

    public void flush() throws XMLStreamException {
    }

    public NamespaceContext getNamespaceContext() {
        return null;
    }

    public String getPrefix(String str) throws XMLStreamException {
        NamespaceSupport namespaceSupport = this.namespaceContext;
        if (namespaceSupport != null) {
            return namespaceSupport.getPrefix(str);
        }
        return null;
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    public void setDefaultNamespace(String str) throws XMLStreamException {
        this.namespaceContext.declarePrefix("", str);
        boolean[] zArr = this.needContextPop;
        int i = this.depth;
        if (zArr[i]) {
            return;
        }
        zArr[i] = true;
    }

    public void setNamespaceContext(NamespaceContext namespaceContext) throws XMLStreamException {
        throw new UnsupportedOperationException();
    }

    public void setPrefix(String str, String str2) throws XMLStreamException {
        if (str == null) {
            jnd.a("Prefix cannot be null");
            return;
        }
        this.namespaceContext.declarePrefix(str, str2);
        boolean[] zArr = this.needContextPop;
        int i = this.depth;
        if (zArr[i]) {
            return;
        }
        zArr[i] = true;
    }

    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        if (this.currentNode.getNodeType() != 1) {
            lpe.a("Current DOM Node type  is ", this.currentNode.getNodeType(), "and does not allow attributes to be set ");
            return;
        }
        if (str == null) {
            jnd.a("NamespaceURI cannot be null");
            return;
        }
        if (str2 == null) {
            jnd.a("Local name cannot be null");
            return;
        }
        NamespaceSupport namespaceSupport = this.namespaceContext;
        String prefix = namespaceSupport != null ? namespaceSupport.getPrefix(str) : null;
        if (prefix == null) {
            txf.a("Namespace URI ", str, "is not bound to any prefix");
            return;
        }
        if (!prefix.isEmpty()) {
            str2 = getQName(prefix, str2);
        }
        Attr attrCreateAttributeNS = this.ownerDoc.createAttributeNS(str, str2);
        attrCreateAttributeNS.setValue(str3);
        ((Element) this.currentNode).setAttributeNode(attrCreateAttributeNS);
    }

    public void writeCData(String str) throws XMLStreamException {
        if (str == null) {
            jnd.a("CDATA cannot be null");
        } else {
            getNode().appendChild(this.ownerDoc.createCDATASection(str));
        }
    }

    public void writeCharacters(char[] cArr, int i, int i2) throws XMLStreamException {
        this.currentNode.appendChild(this.ownerDoc.createTextNode(new String(cArr, i, i2)));
    }

    public void writeComment(String str) throws XMLStreamException {
        getNode().appendChild(this.ownerDoc.createComment(str));
    }

    public void writeDTD(String str) throws XMLStreamException {
        throw new UnsupportedOperationException();
    }

    public void writeDefaultNamespace(String str) throws XMLStreamException {
        short nodeType = this.currentNode.getNodeType();
        Node node = this.currentNode;
        if (nodeType == 1) {
            ((Element) node).setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", str);
        } else {
            lpe.a("Current DOM Node type  is ", node.getNodeType(), "and does not allow attributes to be set ");
        }
    }

    public void writeEmptyElement(String str, String str2) throws XMLStreamException {
        if (this.ownerDoc != null) {
            if (str == null) {
                jnd.a("NamespaceURI cannot be null");
                return;
            }
            if (str2 == null) {
                jnd.a("Local name cannot be null");
                return;
            }
            NamespaceSupport namespaceSupport = this.namespaceContext;
            String prefix = namespaceSupport != null ? namespaceSupport.getPrefix(str) : null;
            if (prefix == null) {
                txf.a("Namespace URI ", str, "is not bound to any prefix");
                return;
            }
            if (!"".equals(prefix)) {
                str2 = getQName(prefix, str2);
            }
            Element elementCreateElementNS = this.ownerDoc.createElementNS(str, str2);
            Node node = this.currentNode;
            if (node != null) {
                node.appendChild(elementCreateElementNS);
            } else {
                this.ownerDoc.appendChild(elementCreateElementNS);
            }
        }
    }

    public void writeEndDocument() throws XMLStreamException {
        this.currentNode = null;
        int i = 0;
        while (true) {
            int i2 = this.depth;
            if (i >= i2) {
                this.depth = 0;
                return;
            }
            boolean[] zArr = this.needContextPop;
            if (zArr[i2]) {
                zArr[i2] = false;
                this.namespaceContext.popContext();
            }
            this.depth--;
            i++;
        }
    }

    public void writeEndElement() throws XMLStreamException {
        Node parentNode = this.currentNode.getParentNode();
        if (this.currentNode.getNodeType() == 9) {
            this.currentNode = null;
        } else {
            this.currentNode = parentNode;
        }
        boolean[] zArr = this.needContextPop;
        int i = this.depth;
        if (zArr[i]) {
            zArr[i] = false;
            this.namespaceContext.popContext();
        }
        this.depth--;
    }

    public void writeEntityRef(String str) throws XMLStreamException {
        this.currentNode.appendChild(this.ownerDoc.createEntityReference(str));
    }

    public void writeNamespace(String str, String str2) throws XMLStreamException {
        if (str == null) {
            jnd.a("prefix cannot be null");
        } else if (str2 != null) {
            ((Element) this.currentNode).setAttributeNS("http://www.w3.org/2000/xmlns/", str.isEmpty() ? "xmlns" : getQName("xmlns", str), str2);
        } else {
            jnd.a("NamespaceURI cannot be null");
        }
    }

    public void writeProcessingInstruction(String str) throws XMLStreamException {
        if (str == null) {
            jnd.a("Target cannot be null");
        } else {
            this.currentNode.appendChild(this.ownerDoc.createProcessingInstruction(str, ""));
        }
    }

    public void writeStartDocument(String str, String str2, boolean z, boolean z2) throws XMLStreamException {
        if (str != null && this.ownerDoc.getClass().isAssignableFrom(DocumentImpl.class)) {
            ((DocumentImpl) this.ownerDoc).setXmlEncoding(str);
        }
        this.ownerDoc.setXmlVersion(str2);
        if (z2) {
            this.ownerDoc.setXmlStandalone(z);
        }
    }

    public void writeStartElement(String str, String str2) throws XMLStreamException {
        if (this.ownerDoc != null) {
            if (str == null) {
                jnd.a("NamespaceURI cannot be null");
                return;
            }
            if (str2 == null) {
                jnd.a("Local name cannot be null");
                return;
            }
            NamespaceSupport namespaceSupport = this.namespaceContext;
            String prefix = namespaceSupport != null ? namespaceSupport.getPrefix(str) : null;
            if (prefix == null) {
                txf.a("Namespace URI ", str, "is not bound to any prefix");
                return;
            }
            if (!"".equals(prefix)) {
                str2 = getQName(prefix, str2);
            }
            Element elementCreateElementNS = this.ownerDoc.createElementNS(str, str2);
            Node node = this.currentNode;
            if (node != null) {
                node.appendChild(elementCreateElementNS);
            } else {
                this.ownerDoc.appendChild(elementCreateElementNS);
            }
            this.currentNode = elementCreateElementNS;
        }
        if (this.needContextPop[this.depth]) {
            this.namespaceContext.pushContext();
        }
        incDepth();
    }

    public void writeCharacters(String str) throws XMLStreamException {
        this.currentNode.appendChild(this.ownerDoc.createTextNode(str));
    }

    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        if (str != null) {
            this.currentNode.appendChild(this.ownerDoc.createProcessingInstruction(str, str2));
        } else {
            jnd.a("Target cannot be null");
        }
    }

    public void writeStartDocument(String str) throws XMLStreamException {
        writeStartDocument(null, str, false, false);
    }

    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        writeStartDocument(str, str2, false, false);
    }

    public void writeStartDocument() throws XMLStreamException {
        this.ownerDoc.setXmlVersion("1.0");
    }

    public void writeEmptyElement(String str) throws XMLStreamException {
        Document document = this.ownerDoc;
        if (document != null) {
            Element elementCreateElement = document.createElement(str);
            Node node = this.currentNode;
            if (node != null) {
                node.appendChild(elementCreateElement);
            } else {
                this.ownerDoc.appendChild(elementCreateElement);
            }
        }
    }

    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException {
        if (this.ownerDoc != null) {
            if (str3 == null) {
                jnd.a("NamespaceURI cannot be null");
                return;
            }
            if (str2 == null) {
                jnd.a("Local name cannot be null");
                return;
            }
            if (str != null) {
                if (!"".equals(str)) {
                    str2 = getQName(str, str2);
                }
                Element elementCreateElementNS = this.ownerDoc.createElementNS(str3, str2);
                Node node = this.currentNode;
                if (node != null) {
                    node.appendChild(elementCreateElementNS);
                    return;
                } else {
                    this.ownerDoc.appendChild(elementCreateElementNS);
                    return;
                }
            }
            jnd.a("Prefix cannot be null");
        }
    }

    public void writeAttribute(String str, String str2) throws XMLStreamException {
        if (this.currentNode.getNodeType() == 1) {
            Attr attrCreateAttribute = this.ownerDoc.createAttribute(str);
            attrCreateAttribute.setValue(str2);
            ((Element) this.currentNode).setAttributeNode(attrCreateAttribute);
            return;
        }
        lpe.a("Current DOM Node type  is ", this.currentNode.getNodeType(), "and does not allow attributes to be set ");
    }

    public void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException {
        if (this.currentNode.getNodeType() != 1) {
            lpe.a("Current DOM Node type  is ", this.currentNode.getNodeType(), "and does not allow attributes to be set ");
            return;
        }
        if (str2 == null) {
            jnd.a("NamespaceURI cannot be null");
            return;
        }
        if (str3 == null) {
            jnd.a("Local name cannot be null");
            return;
        }
        if (str != null) {
            if (!str.isEmpty()) {
                str3 = getQName(str, str3);
            }
            Attr attrCreateAttributeNS = this.ownerDoc.createAttributeNS(str2, str3);
            attrCreateAttributeNS.setValue(str4);
            ((Element) this.currentNode).setAttributeNodeNS(attrCreateAttributeNS);
            return;
        }
        jnd.a("prefix cannot be null");
    }

    public void writeStartElement(String str) throws XMLStreamException {
        Document document = this.ownerDoc;
        if (document != null) {
            Element elementCreateElement = document.createElement(str);
            Node node = this.currentNode;
            if (node != null) {
                node.appendChild(elementCreateElement);
            } else {
                this.ownerDoc.appendChild(elementCreateElement);
            }
            this.currentNode = elementCreateElement;
        }
        if (this.needContextPop[this.depth]) {
            this.namespaceContext.pushContext();
        }
        incDepth();
    }

    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException {
        if (this.ownerDoc != null) {
            if (str3 == null) {
                jnd.a("NamespaceURI cannot be null");
                return;
            }
            if (str2 == null) {
                jnd.a("Local name cannot be null");
                return;
            }
            if (str != null) {
                if (!str.isEmpty()) {
                    str2 = getQName(str, str2);
                }
                Element elementCreateElementNS = this.ownerDoc.createElementNS(str3, str2);
                Node node = this.currentNode;
                if (node != null) {
                    node.appendChild(elementCreateElementNS);
                } else {
                    this.ownerDoc.appendChild(elementCreateElementNS);
                }
                this.currentNode = elementCreateElementNS;
                if (this.needContextPop[this.depth]) {
                    this.namespaceContext.pushContext();
                }
                incDepth();
                return;
            }
            jnd.a("Prefix cannot be null");
        }
    }
}
