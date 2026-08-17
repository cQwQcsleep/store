package com.reandroid.xml;

import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.xml.base.Node;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class XMLNode implements Node {
    private int mColumnNumber;
    private int mLineNumber;
    private XMLNode mParent;

    @Override // com.reandroid.xml.base.Node
    public int getColumnNumber() {
        return this.mColumnNumber;
    }

    public String getDebugText() {
        return XMLDebugStringBuilder.build(this);
    }

    public int getLength() {
        return 0;
    }

    @Override // com.reandroid.xml.base.Node
    public int getLineNumber() {
        return this.mLineNumber;
    }

    public XMLNode getRootParentNode() {
        XMLNode parentNode = getParentNode();
        return parentNode != null ? parentNode.getRootParentNode() : this;
    }

    public int getTextLength() {
        return 0;
    }

    public Iterator<XMLNode> iterator() {
        return EmptyIterator.of();
    }

    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public void removeSelf() {
        XMLNode parentNode = getParentNode();
        if (parentNode instanceof XMLNodeTree) {
            ((XMLNodeTree) parentNode).remove(this);
        }
    }

    @Override // com.reandroid.xml.base.Node
    public void setColumnNumber(int i) {
        this.mColumnNumber = i;
    }

    @Override // com.reandroid.xml.base.Node
    public void setLineNumber(int i) {
        this.mLineNumber = i;
    }

    public void setParentNode(XMLNode xMLNode) {
        if (xMLNode != this) {
            this.mParent = xMLNode;
        }
    }

    public String toString() {
        return getDebugText();
    }

    public String toText(boolean z, boolean z2) {
        StringWriter stringWriter = new StringWriter();
        try {
            write(stringWriter, z, z2);
            stringWriter.flush();
            stringWriter.close();
        } catch (IOException unused) {
        }
        return stringWriter.toString();
    }

    public abstract void write(Appendable appendable, boolean z, boolean z2) throws IOException;

    @Override // com.reandroid.xml.base.Node, com.reandroid.xml.base.Attribute
    public XMLNode getParentNode() {
        return this.mParent;
    }
}
