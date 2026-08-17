package com.sun.org.apache.xerces.internal.impl.xs.opti;

import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.util.ArrayList;
import java.util.Enumeration;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SchemaDOM extends DefaultDocument {
    static final int relationsColResizeFactor = 10;
    static final int relationsRowResizeFactor = 15;
    int currLoc;
    private StringBuffer fAnnotationBuffer = null;
    boolean hidden;
    boolean inCDATA;
    int nextFreeLoc;
    ElementImpl parent;
    NodeImpl[][] relations;

    public SchemaDOM() {
        reset();
    }

    private static String escapeAttValue(String str, int i) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        stringBuffer.append(str.substring(0, i));
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"') {
                stringBuffer.append(SerializerConstants.ENTITY_QUOT);
            } else if (cCharAt == '<') {
                stringBuffer.append(SerializerConstants.ENTITY_LT);
            } else if (cCharAt == '&') {
                stringBuffer.append(SerializerConstants.ENTITY_AMP);
            } else if (cCharAt == '\t') {
                stringBuffer.append("&#x9;");
            } else if (cCharAt == '\n') {
                stringBuffer.append(SerializerConstants.ENTITY_CRLF);
            } else if (cCharAt == '\r') {
                stringBuffer.append("&#xD;");
            } else {
                stringBuffer.append(cCharAt);
            }
            i++;
        }
        return stringBuffer.toString();
    }

    public static void indent(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            System.out.print(' ');
        }
    }

    private static String processAttValue(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == '<' || cCharAt == '&' || cCharAt == '\t' || cCharAt == '\n' || cCharAt == '\r') {
                return escapeAttValue(str, i);
            }
        }
        return str;
    }

    private void processElement(QName qName, XMLAttributes xMLAttributes, ElementImpl elementImpl) {
        elementImpl.prefix = qName.prefix;
        elementImpl.localpart = qName.localpart;
        elementImpl.rawname = qName.rawname;
        elementImpl.uri = qName.uri;
        elementImpl.schemaDOM = this;
        Attr[] attrArr = new Attr[xMLAttributes.getLength()];
        for (int i = 0; i < xMLAttributes.getLength(); i++) {
            attrArr[i] = new AttrImpl(elementImpl, xMLAttributes.getPrefix(i), xMLAttributes.getLocalName(i), xMLAttributes.getQName(i), xMLAttributes.getURI(i), xMLAttributes.getValue(i));
        }
        elementImpl.attrs = attrArr;
        if (this.nextFreeLoc == this.relations.length) {
            resizeRelations();
        }
        NodeImpl[][] nodeImplArr = this.relations;
        NodeImpl nodeImpl = nodeImplArr[this.currLoc][0];
        ElementImpl elementImpl2 = this.parent;
        if (nodeImpl != elementImpl2) {
            int i2 = this.nextFreeLoc;
            nodeImplArr[i2][0] = elementImpl2;
            this.nextFreeLoc = i2 + 1;
            this.currLoc = i2;
        }
        int i3 = 1;
        while (true) {
            NodeImpl[][] nodeImplArr2 = this.relations;
            int i4 = this.currLoc;
            NodeImpl[] nodeImplArr3 = nodeImplArr2[i4];
            if (i3 >= nodeImplArr3.length) {
                resizeRelations(i4);
                break;
            } else if (nodeImplArr3[i3] == null) {
                break;
            } else {
                i3++;
            }
        }
        NodeImpl[][] nodeImplArr4 = this.relations;
        int i5 = this.currLoc;
        nodeImplArr4[i5][i3] = elementImpl;
        this.parent.parentRow = i5;
        elementImpl.row = i5;
        elementImpl.col = i3;
    }

    private void resizeRelations() {
        NodeImpl[][] nodeImplArr = this.relations;
        int length = nodeImplArr.length + 15;
        NodeImpl[][] nodeImplArr2 = new NodeImpl[length][];
        System.arraycopy(nodeImplArr, 0, nodeImplArr2, 0, nodeImplArr.length);
        for (int length2 = this.relations.length; length2 < length; length2++) {
            nodeImplArr2[length2] = new NodeImpl[10];
        }
        this.relations = nodeImplArr2;
    }

    public static void traverse(Node node, int i) {
        indent(i);
        System.out.print("<" + node.getNodeName());
        if (node.hasAttributes()) {
            NamedNodeMap attributes = node.getAttributes();
            for (int i2 = 0; i2 < attributes.getLength(); i2++) {
                System.out.print("  " + ((Attr) attributes.item(i2)).getName() + "=\"" + ((Attr) attributes.item(i2)).getValue() + "\"");
            }
        }
        if (!node.hasChildNodes()) {
            System.out.println("/>");
            return;
        }
        System.out.println(">");
        int i3 = i + 4;
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            traverse(firstChild, i3);
        }
        indent(i);
        System.out.println("</" + node.getNodeName() + ">");
    }

    public void characters(XMLString xMLString) {
        boolean z = this.inCDATA;
        StringBuffer stringBuffer = this.fAnnotationBuffer;
        if (z) {
            stringBuffer.append(xMLString.ch, xMLString.offset, xMLString.length);
            return;
        }
        for (int i = xMLString.offset; i < xMLString.offset + xMLString.length; i++) {
            char c = xMLString.ch[i];
            if (c == '&') {
                stringBuffer.append(SerializerConstants.ENTITY_AMP);
            } else if (c == '<') {
                stringBuffer.append(SerializerConstants.ENTITY_LT);
            } else if (c == '>') {
                stringBuffer.append(SerializerConstants.ENTITY_GT);
            } else if (c == '\r') {
                stringBuffer.append("&#xD;");
            } else {
                stringBuffer.append(c);
            }
        }
    }

    public void charactersRaw(String str) {
        this.fAnnotationBuffer.append(str);
    }

    public void comment(XMLString xMLString) {
        this.fAnnotationBuffer.append("<!--");
        int i = xMLString.length;
        if (i > 0) {
            this.fAnnotationBuffer.append(xMLString.ch, xMLString.offset, i);
        }
        this.fAnnotationBuffer.append("-->");
    }

    public ElementImpl emptyElement(QName qName, XMLAttributes xMLAttributes, int i, int i2) {
        return emptyElement(qName, xMLAttributes, i, i2, -1);
    }

    public void endAnnotation(QName qName, ElementImpl elementImpl) {
        StringBuffer stringBuffer = this.fAnnotationBuffer;
        stringBuffer.append("\n</");
        stringBuffer.append(qName.rawname);
        stringBuffer.append(">");
        elementImpl.fAnnotation = this.fAnnotationBuffer.toString();
        this.fAnnotationBuffer = null;
    }

    public void endAnnotationCDATA() {
        this.fAnnotationBuffer.append("]]>");
        this.inCDATA = false;
    }

    public void endAnnotationElement(String str) {
        StringBuffer stringBuffer = this.fAnnotationBuffer;
        stringBuffer.append("</");
        stringBuffer.append(str);
        stringBuffer.append(">");
    }

    public void endElement() {
        int i = this.parent.row;
        this.currLoc = i;
        this.parent = (ElementImpl) this.relations[i][0];
    }

    public void endSyntheticAnnotationElement(String str, boolean z) {
        StringBuffer stringBuffer = this.fAnnotationBuffer;
        if (!z) {
            stringBuffer.append("</");
            stringBuffer.append(str);
            stringBuffer.append(">");
        } else {
            stringBuffer.append("\n</");
            stringBuffer.append(str);
            stringBuffer.append(">");
            this.parent.fSyntheticAnnotation = this.fAnnotationBuffer.toString();
            this.fAnnotationBuffer = null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultDocument, org.w3c.dom.Document
    public Element getDocumentElement() {
        return (ElementImpl) this.relations[0][1];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultDocument, org.w3c.dom.Document
    public DOMImplementation getImplementation() {
        return SchemaDOMImplementation.getDOMImplementation();
    }

    public void printDOM() {
    }

    public void processingInstruction(String str, XMLString xMLString) {
        StringBuffer stringBuffer = this.fAnnotationBuffer;
        stringBuffer.append("<?");
        stringBuffer.append(str);
        if (xMLString.length > 0) {
            StringBuffer stringBuffer2 = this.fAnnotationBuffer;
            stringBuffer2.append(' ');
            stringBuffer2.append(xMLString.ch, xMLString.offset, xMLString.length);
        }
        this.fAnnotationBuffer.append("?>");
    }

    public void reset() {
        if (this.relations != null) {
            for (int i = 0; i < this.relations.length; i++) {
                int i2 = 0;
                while (true) {
                    NodeImpl[] nodeImplArr = this.relations[i];
                    if (i2 < nodeImplArr.length) {
                        nodeImplArr[i2] = null;
                        i2++;
                    }
                }
            }
        }
        this.relations = new NodeImpl[15][];
        ElementImpl elementImpl = new ElementImpl(0, 0, 0);
        this.parent = elementImpl;
        elementImpl.rawname = "DOCUMENT_NODE";
        this.currLoc = 0;
        this.nextFreeLoc = 1;
        this.inCDATA = false;
        int i3 = 0;
        while (true) {
            NodeImpl[][] nodeImplArr2 = this.relations;
            if (i3 >= 15) {
                nodeImplArr2[this.currLoc][0] = this.parent;
                return;
            } else {
                nodeImplArr2[i3] = new NodeImpl[10];
                i3++;
            }
        }
    }

    public void startAnnotation(String str, XMLAttributes xMLAttributes, NamespaceContext namespaceContext) {
        if (this.fAnnotationBuffer == null) {
            this.fAnnotationBuffer = new StringBuffer(256);
        }
        StringBuffer stringBuffer = this.fAnnotationBuffer;
        stringBuffer.append("<");
        stringBuffer.append(str);
        stringBuffer.append(" ");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < xMLAttributes.getLength(); i++) {
            String value = xMLAttributes.getValue(i);
            String prefix = xMLAttributes.getPrefix(i);
            String qName = xMLAttributes.getQName(i);
            String str2 = XMLSymbols.PREFIX_XMLNS;
            if (prefix == str2 || qName == str2) {
                arrayList.add(prefix == str2 ? xMLAttributes.getLocalName(i) : XMLSymbols.EMPTY_STRING);
            }
            StringBuffer stringBuffer2 = this.fAnnotationBuffer;
            stringBuffer2.append(qName);
            stringBuffer2.append("=\"");
            stringBuffer2.append(processAttValue(value));
            stringBuffer2.append("\" ");
        }
        Enumeration<String> allPrefixes = namespaceContext.getAllPrefixes();
        while (allPrefixes.hasMoreElements()) {
            String strNextElement = allPrefixes.nextElement();
            String uri = namespaceContext.getURI(strNextElement);
            if (uri == null) {
                uri = XMLSymbols.EMPTY_STRING;
            }
            if (!arrayList.contains(strNextElement)) {
                String str3 = XMLSymbols.EMPTY_STRING;
                StringBuffer stringBuffer3 = this.fAnnotationBuffer;
                if (strNextElement == str3) {
                    stringBuffer3.append("xmlns");
                    stringBuffer3.append("=\"");
                    stringBuffer3.append(processAttValue(uri));
                    stringBuffer3.append("\" ");
                } else {
                    stringBuffer3.append("xmlns:");
                    stringBuffer3.append(strNextElement);
                    stringBuffer3.append("=\"");
                    stringBuffer3.append(processAttValue(uri));
                    stringBuffer3.append("\" ");
                }
            }
        }
        this.fAnnotationBuffer.append(">\n");
    }

    public void startAnnotationCDATA() {
        this.inCDATA = true;
        this.fAnnotationBuffer.append("<![CDATA[");
    }

    public void startAnnotationElement(String str, XMLAttributes xMLAttributes) {
        StringBuffer stringBuffer = this.fAnnotationBuffer;
        stringBuffer.append("<");
        stringBuffer.append(str);
        for (int i = 0; i < xMLAttributes.getLength(); i++) {
            String value = xMLAttributes.getValue(i);
            StringBuffer stringBuffer2 = this.fAnnotationBuffer;
            stringBuffer2.append(" ");
            stringBuffer2.append(xMLAttributes.getQName(i));
            stringBuffer2.append("=\"");
            stringBuffer2.append(processAttValue(value));
            stringBuffer2.append("\"");
        }
        this.fAnnotationBuffer.append(">");
    }

    public ElementImpl startElement(QName qName, XMLAttributes xMLAttributes, int i, int i2, int i3) {
        ElementImpl elementImpl = new ElementImpl(i, i2, i3);
        processElement(qName, xMLAttributes, elementImpl);
        this.parent = elementImpl;
        return elementImpl;
    }

    public ElementImpl emptyElement(QName qName, XMLAttributes xMLAttributes, int i, int i2, int i3) {
        ElementImpl elementImpl = new ElementImpl(i, i2, i3);
        processElement(qName, xMLAttributes, elementImpl);
        return elementImpl;
    }

    public ElementImpl startElement(QName qName, XMLAttributes xMLAttributes, int i, int i2) {
        return startElement(qName, xMLAttributes, i, i2, -1);
    }

    public void endAnnotationElement(QName qName) {
        endAnnotationElement(qName.rawname);
    }

    private void resizeRelations(int i) {
        NodeImpl[] nodeImplArr = this.relations[i];
        NodeImpl[] nodeImplArr2 = new NodeImpl[nodeImplArr.length + 10];
        System.arraycopy(nodeImplArr, 0, nodeImplArr2, 0, nodeImplArr.length);
        this.relations[i] = nodeImplArr2;
    }

    public void endSyntheticAnnotationElement(QName qName, boolean z) {
        endSyntheticAnnotationElement(qName.rawname, z);
    }

    public void startAnnotationElement(QName qName, XMLAttributes xMLAttributes) {
        startAnnotationElement(qName.rawname, xMLAttributes);
    }

    public void startAnnotation(QName qName, XMLAttributes xMLAttributes, NamespaceContext namespaceContext) {
        startAnnotation(qName.rawname, xMLAttributes, namespaceContext);
    }
}
