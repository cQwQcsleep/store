package com.sun.org.apache.xerces.internal.impl.xs.opti;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ElementImpl extends DefaultElement {
    Attr[] attrs;
    int charOffset;
    int col;
    int column;
    String fAnnotation;
    String fSyntheticAnnotation;
    int line;
    int parentRow;
    int row;
    SchemaDOM schemaDOM;

    public ElementImpl(String str, String str2, String str3, String str4, int i, int i2, int i3) {
        super(str, str2, str3, str4, (short) 1);
        this.row = -1;
        this.col = -1;
        this.parentRow = -1;
        this.line = i;
        this.column = i2;
        this.charOffset = i3;
    }

    private static boolean nsEquals(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public String getAnnotation() {
        return this.fAnnotation;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement, org.w3c.dom.Element
    public String getAttribute(String str) {
        int i = 0;
        while (true) {
            Attr[] attrArr = this.attrs;
            if (i >= attrArr.length) {
                return "";
            }
            if (attrArr[i].getName().equals(str)) {
                return this.attrs[i].getValue();
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement, org.w3c.dom.Element
    public String getAttributeNS(String str, String str2) {
        int i = 0;
        while (true) {
            Attr[] attrArr = this.attrs;
            if (i >= attrArr.length) {
                return "";
            }
            if (attrArr[i].getLocalName().equals(str2) && nsEquals(this.attrs[i].getNamespaceURI(), str)) {
                return this.attrs[i].getValue();
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement, org.w3c.dom.Element
    public Attr getAttributeNode(String str) {
        int i = 0;
        while (true) {
            Attr[] attrArr = this.attrs;
            if (i >= attrArr.length) {
                return null;
            }
            if (attrArr[i].getName().equals(str)) {
                return this.attrs[i];
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement, org.w3c.dom.Element
    public Attr getAttributeNodeNS(String str, String str2) {
        int i = 0;
        while (true) {
            Attr[] attrArr = this.attrs;
            if (i >= attrArr.length) {
                return null;
            }
            if (attrArr[i].getName().equals(str2) && nsEquals(this.attrs[i].getNamespaceURI(), str)) {
                return this.attrs[i];
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        return new NamedNodeMapImpl(this.attrs);
    }

    public int getCharacterOffset() {
        return this.charOffset;
    }

    public int getColumnNumber() {
        return this.column;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Node getFirstChild() {
        int i = this.parentRow;
        if (i == -1) {
            return null;
        }
        return this.schemaDOM.relations[i][1];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Node getLastChild() {
        if (this.parentRow == -1) {
            return null;
        }
        int i = 1;
        while (true) {
            NodeImpl[] nodeImplArr = this.schemaDOM.relations[this.parentRow];
            if (i >= nodeImplArr.length) {
                if (i == 1) {
                    i++;
                }
                return nodeImplArr[i - 1];
            }
            if (nodeImplArr[i] == null) {
                return nodeImplArr[i - 1];
            }
            i++;
        }
    }

    public int getLineNumber() {
        return this.line;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Node getNextSibling() {
        int i = this.col;
        NodeImpl[] nodeImplArr = this.schemaDOM.relations[this.row];
        if (i == nodeImplArr.length - 1) {
            return null;
        }
        return nodeImplArr[i + 1];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Document getOwnerDocument() {
        return this.schemaDOM;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Node getParentNode() {
        return this.schemaDOM.relations[this.row][0];
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public Node getPreviousSibling() {
        int i = this.col;
        if (i == 1) {
            return null;
        }
        return this.schemaDOM.relations[this.row][i - 1];
    }

    public String getSyntheticAnnotation() {
        return this.fSyntheticAnnotation;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement, org.w3c.dom.Element
    public String getTagName() {
        return this.rawname;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement, org.w3c.dom.Element
    public boolean hasAttribute(String str) {
        int i = 0;
        while (true) {
            Attr[] attrArr = this.attrs;
            if (i >= attrArr.length) {
                return false;
            }
            if (attrArr[i].getName().equals(str)) {
                return true;
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement, org.w3c.dom.Element
    public boolean hasAttributeNS(String str, String str2) {
        int i = 0;
        while (true) {
            Attr[] attrArr = this.attrs;
            if (i >= attrArr.length) {
                return false;
            }
            if (attrArr[i].getName().equals(str2) && nsEquals(this.attrs[i].getNamespaceURI(), str)) {
                return true;
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public boolean hasAttributes() {
        return this.attrs.length != 0;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultNode, org.w3c.dom.Node
    public boolean hasChildNodes() {
        return this.parentRow != -1;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultElement, org.w3c.dom.Element
    public void setAttribute(String str, String str2) {
        int i = 0;
        while (true) {
            Attr[] attrArr = this.attrs;
            if (i >= attrArr.length) {
                return;
            }
            if (attrArr[i].getName().equals(str)) {
                this.attrs[i].setValue(str2);
                return;
            }
            i++;
        }
    }

    public ElementImpl(int i, int i2) {
        this(i, i2, -1);
    }

    public ElementImpl(int i, int i2, int i3) {
        this.row = -1;
        this.col = -1;
        this.parentRow = -1;
        this.nodeType = (short) 1;
        this.line = i;
        this.column = i2;
        this.charOffset = i3;
    }

    public ElementImpl(String str, String str2, String str3, String str4, int i, int i2) {
        this(str, str2, str3, str4, i, i2, -1);
    }
}
