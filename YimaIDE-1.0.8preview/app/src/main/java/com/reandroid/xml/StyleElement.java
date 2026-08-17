package com.reandroid.xml;

import com.reandroid.utils.collection.IndexIterator;
import com.reandroid.utils.collection.SizedSupplier;
import com.reandroid.xml.base.Attribute;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StyleElement extends XMLElement implements Span {
    @Override // com.reandroid.xml.XMLElement
    public void addAttribute(Attribute attribute) {
        if (attribute instanceof StyleAttribute) {
            super.addAttribute(attribute);
        } else {
            b58.a("Incompatible attribute type: ", attribute.getClass());
        }
    }

    public void copyFrom(XMLElement xMLElement) {
        setName(xMLElement.getName());
        setVoidHtml(xMLElement.isVoidHtml());
        Iterator<? extends XMLAttribute> attributes = xMLElement.getAttributes();
        while (attributes.hasNext()) {
            newAttribute().setFrom(attributes.next());
        }
        for (XMLNode xMLNode : xMLElement) {
            if (xMLNode instanceof XMLElement) {
                newElement().copyFrom((XMLElement) xMLNode);
            } else if (xMLNode instanceof XMLText) {
                getOrCreateLastText().appendText(((XMLText) xMLNode).getText());
            } else if (xMLNode instanceof XMLCDSect) {
                getOrCreateLastText().appendText(((XMLCDSect) xMLNode).getText());
            }
        }
    }

    @Override // com.reandroid.xml.XMLElement, com.reandroid.xml.XMLNodeTree
    public void endSerialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.endTag(null, getName());
    }

    @Override // com.reandroid.xml.XMLElement
    public StyleAttribute getAttribute(String str) {
        return (StyleAttribute) super.getAttribute(str);
    }

    @Override // com.reandroid.xml.XMLElement, com.reandroid.xml.base.Element
    public StyleAttribute getAttributeAt(int i) {
        return (StyleAttribute) super.getAttributeAt(i);
    }

    @Override // com.reandroid.xml.XMLElement, com.reandroid.xml.base.Element
    public Iterator<StyleAttribute> getAttributes() {
        return new IndexIterator(new SizedSupplier<StyleAttribute>() { // from class: com.reandroid.xml.StyleElement.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.reandroid.utils.collection.SizedSupplier
            public StyleAttribute get(int i) {
                return StyleElement.this.getAttributeAt(i);
            }

            @Override // com.reandroid.utils.collection.SizedItem
            public int size() {
                return StyleElement.this.getAttributeCount();
            }
        });
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public Iterator<StyleElement> getElements() {
        return iterator(StyleElement.class);
    }

    @Override // com.reandroid.xml.Span
    public int getFirstChar() {
        XMLNode next;
        Iterator<XMLNode> itRecursiveNodes = ((XMLNodeTree) getRootParentNode()).recursiveNodes();
        int textLength = 0;
        while (itRecursiveNodes.hasNext() && (next = itRecursiveNodes.next()) != this) {
            textLength += next.getTextLength();
        }
        return textLength;
    }

    @Override // com.reandroid.xml.Span
    public int getLastChar() {
        int firstChar = getFirstChar() + getLength();
        return firstChar != 0 ? firstChar - 1 : firstChar;
    }

    @Override // com.reandroid.xml.XMLNode
    public int getLength() {
        Iterator<XMLNode> it = iterator();
        int length = 0;
        while (it.hasNext()) {
            length += it.next().getLength();
        }
        return length;
    }

    @Override // com.reandroid.xml.XMLElement
    public StyleElement getParentElement() {
        return (StyleElement) super.getParentElement();
    }

    @Override // com.reandroid.xml.Span
    public String getSpanAttributes() {
        return SpanAttributesEncoder.encodeAttributes(this);
    }

    @Override // com.reandroid.xml.Span
    public int getSpanOrder() {
        XMLNode next;
        Iterator<XMLNode> itRecursiveNodes = ((XMLNodeTree) getRootParentNode()).recursiveNodes();
        int i = 0;
        while (itRecursiveNodes.hasNext() && (next = itRecursiveNodes.next()) != this) {
            if (next instanceof StyleElement) {
                i++;
            }
        }
        return i;
    }

    @Override // com.reandroid.xml.Span
    public String getTagName() {
        return getName();
    }

    public String getTagString() {
        String tagName = getTagName();
        String spanAttributes = getSpanAttributes();
        if (spanAttributes == null) {
            return tagName;
        }
        return tagName + spanAttributes;
    }

    @Override // com.reandroid.xml.XMLElement, com.reandroid.xml.base.Element
    public StyleAttribute newAttribute() {
        StyleAttribute styleAttribute = new StyleAttribute();
        addAttribute(styleAttribute);
        return styleAttribute;
    }

    @Override // com.reandroid.xml.XMLElement, com.reandroid.xml.XMLNodeTree
    public XMLComment newComment() {
        return null;
    }

    @Override // com.reandroid.xml.XMLElement, com.reandroid.xml.base.Element, com.reandroid.xml.base.NodeFactory
    public StyleElement newElement() {
        StyleElement styleElement = new StyleElement();
        add((XMLNode) styleElement);
        return styleElement;
    }

    @Override // com.reandroid.xml.XMLElement, com.reandroid.xml.base.NodeFactory
    public StyleText newText() {
        StyleText styleText = new StyleText();
        add((XMLNode) styleText);
        return styleText;
    }

    @Override // com.reandroid.xml.XMLNodeTree, com.reandroid.xml.XMLNode, com.reandroid.xml.base.XmlReader
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLUtil.expectEvent(xmlPullParser, 2);
        setName(xmlPullParser.getName());
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            newAttribute().set(xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
        }
        int next = xmlPullParser.next();
        while (next != 3 && next != 1) {
            if (next == 2) {
                newElement().parse(xmlPullParser);
            } else if (XMLText.isTextEvent(next)) {
                newText().parse(xmlPullParser);
            } else {
                xmlPullParser.next();
            }
            next = xmlPullParser.getEventType();
        }
        if (xmlPullParser.getEventType() == 3) {
            xmlPullParser.next();
        }
    }

    @Override // com.reandroid.xml.XMLElement, com.reandroid.xml.XMLNodeTree
    public void startSerialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, getName());
        Iterator<StyleAttribute> attributes = getAttributes();
        while (attributes.hasNext()) {
            attributes.next().serialize(xmlSerializer);
        }
    }

    @Override // com.reandroid.xml.Span
    public StyleElement toElement() {
        return this;
    }

    @Override // com.reandroid.xml.XMLNode
    public String toString() {
        return "[" + getFirstChar() + ", " + getLastChar() + "] " + getTagString();
    }

    public void writeStyledText(Appendable appendable) throws IOException {
        for (XMLNode xMLNode : this) {
            if (xMLNode instanceof StyleText) {
                ((StyleText) xMLNode).writeStyledText(appendable);
            } else if (xMLNode instanceof StyleElement) {
                ((StyleElement) xMLNode).writeStyledText(appendable);
            }
        }
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public StyleText newText(String str) {
        StyleText styleTextNewText = newText();
        styleTextNewText.setText(str);
        return styleTextNewText;
    }
}
