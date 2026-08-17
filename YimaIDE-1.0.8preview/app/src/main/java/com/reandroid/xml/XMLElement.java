package com.reandroid.xml;

import com.reandroid.common.Namespace;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.IndexIterator;
import com.reandroid.utils.collection.SizedSupplier;
import com.reandroid.xml.base.Attribute;
import com.reandroid.xml.base.Element;
import com.reandroid.xml.kxml2.KXmlParser;
import com.reandroid.xml.kxml2.KXmlSerializer;
import defpackage.aca;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLElement extends XMLNodeTree implements Element<XMLNode> {
    private static final ArrayCollection<XMLAttribute> EMPTY_ATTRIBUTES = ArrayCollection.empty();
    private static final ArrayCollection<XMLNamespace> EMPTY_NAMESPACES = ArrayCollection.empty();
    private ArrayCollection<XMLAttribute> mAttributes;
    private String mName;
    private XMLNamespace mNamespace;
    private ArrayCollection<XMLNamespace> mNamespaceList;
    private boolean mVoidHtml;

    public XMLElement() {
        this.mAttributes = EMPTY_ATTRIBUTES;
        this.mNamespaceList = EMPTY_NAMESPACES;
    }

    public static XMLElement parseElement(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLElement xMLElement = new XMLElement();
        xMLElement.parse(xmlPullParser);
        return xMLElement;
    }

    private void parseNamespaces(XmlPullParser xmlPullParser) throws XmlPullParserException {
        int namespaceCount = xmlPullParser.getNamespaceCount(getDepth());
        for (int i = 0; i < namespaceCount; i++) {
            addNamespace(xmlPullParser.getNamespaceUri(i), xmlPullParser.getNamespacePrefix(i));
        }
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if (XMLNamespace.looksNamespace(attributeName, attributeValue)) {
                addNamespace(attributeValue, XMLUtil.splitName(attributeName));
            }
        }
    }

    private void transferChildrenToParent() {
        XMLNodeTree xMLNodeTree = (XMLNodeTree) getParentNode();
        if (xMLNodeTree != null) {
            int iIndexOfExact = xMLNodeTree.indexOfExact(this);
            List list = CollectionUtil.toList(iterator());
            clear();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                iIndexOfExact++;
                xMLNodeTree.add(iIndexOfExact, (XMLNode) it.next());
            }
        }
    }

    public XMLElement addAttribute(String str, String str2, String str3, String str4) {
        if (!StringsUtil.isEmpty(str3)) {
            if (XMLNamespace.looksNamespace(str3, str4)) {
                newNamespace(str4, XMLUtil.splitName(str3));
                return this;
            }
            XMLAttribute xMLAttribute = new XMLAttribute();
            addAttribute(xMLAttribute);
            xMLAttribute.setName(str, str2, str3);
            xMLAttribute.setValue(str4);
        }
        return this;
    }

    public void addNamespace(XMLNamespace xMLNamespace) {
        if (xMLNamespace == null || this.mNamespaceList.contains(xMLNamespace)) {
            return;
        }
        ArrayCollection<XMLNamespace> arrayCollection = this.mNamespaceList;
        if (arrayCollection == EMPTY_NAMESPACES) {
            this.mNamespaceList = new ArrayCollection<>();
        } else if (arrayCollection.contains(xMLNamespace)) {
            return;
        }
        this.mNamespaceList.add(xMLNamespace);
    }

    public void addText(String str) {
        newText(str);
    }

    public void appendAttributes(Appendable appendable, boolean z, boolean z2) throws IOException {
        char c = z ? ' ' : ';';
        Iterator<? extends XMLAttribute> attributes = getAttributes();
        while (attributes.hasNext()) {
            appendable.append(c);
            attributes.next().write(appendable, z, z2);
        }
    }

    public void clearAttributes() {
        if (this.mAttributes.size() == 0) {
            return;
        }
        int i = 0;
        while (true) {
            int size = this.mAttributes.size();
            ArrayCollection<XMLAttribute> arrayCollection = this.mAttributes;
            if (i >= size) {
                arrayCollection.clear();
                this.mAttributes.trimToSize();
                return;
            } else {
                arrayCollection.get(i).setParentNode(null);
                i++;
            }
        }
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public void endSerialize(XmlSerializer xmlSerializer) throws IOException {
        if (!isVoidHtml()) {
            xmlSerializer.endTag(getUri(), getName(false));
            return;
        }
        KXmlSerializer kXmlSerializer = XMLUtil.getKXmlSerializer(xmlSerializer);
        if (kXmlSerializer != null) {
            kXmlSerializer.endTag(true, getUri(), getName(false));
        }
    }

    public boolean equalsName(String str) {
        if (str == null) {
            return getName() == null;
        }
        String strSplitPrefix = XMLUtil.splitPrefix(str);
        if (strSplitPrefix == null || strSplitPrefix.equals(getPrefix())) {
            return str.equals(getName());
        }
        return false;
    }

    public XMLAttribute getAttribute(String str) {
        if (str == null) {
            return null;
        }
        int attributeCount = getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            XMLAttribute attributeAt = getAttributeAt(i);
            if (attributeAt.equalsName(str)) {
                return attributeAt;
            }
        }
        return null;
    }

    public XMLAttribute getAttributeAt(int i) {
        return this.mAttributes.get(i);
    }

    @Override // com.reandroid.xml.base.Element
    public int getAttributeCount() {
        return this.mAttributes.size();
    }

    public String getAttributeValue(String str) {
        XMLAttribute attribute = getAttribute(str);
        if (attribute != null) {
            return attribute.getValueAsString();
        }
        return null;
    }

    public Iterator<? extends XMLAttribute> getAttributes() {
        return new IndexIterator(new SizedSupplier<XMLAttribute>() { // from class: com.reandroid.xml.XMLElement.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.reandroid.utils.collection.SizedSupplier
            public XMLAttribute get(int i) {
                return XMLElement.this.getAttributeAt(i);
            }

            @Override // com.reandroid.utils.collection.SizedItem
            public int size() {
                return XMLElement.this.getAttributeCount();
            }
        });
    }

    public List<XMLElement> getChildElementList() {
        return CollectionUtil.toList(iterator(XMLElement.class));
    }

    public int getChildElementsCount() {
        return super.countNodeWithType(XMLElement.class);
    }

    public int getDepth() {
        int i = 1;
        for (XMLElement parentElement = getParentElement(); parentElement != null; parentElement = parentElement.getParentElement()) {
            i++;
        }
        return i;
    }

    public Iterator<XMLElement> getElements(Predicate<XMLElement> predicate) {
        return iterator(XMLElement.class, predicate);
    }

    @Override // com.reandroid.xml.base.NamedNode
    public String getName(boolean z) {
        String prefix;
        String name = getName();
        if (!z || (prefix = getPrefix()) == null) {
            return name;
        }
        return prefix + ":" + name;
    }

    @Override // com.reandroid.xml.base.Element
    public XMLNamespace getNamespaceAt(int i) {
        return this.mNamespaceList.get(i);
    }

    @Override // com.reandroid.xml.base.Element
    public int getNamespaceCount() {
        return this.mNamespaceList.size();
    }

    @Override // com.reandroid.xml.base.Element
    public Iterator<? extends XMLNamespace> getNamespaces() {
        return this.mNamespaceList.iterator();
    }

    public XMLNamespace getOrCreateXMLNamespace(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        XMLNamespace xMLNamespace = getXMLNamespace(str, str2);
        return xMLNamespace != null ? xMLNamespace : getRootElement().newNamespace(str, str2);
    }

    public XMLDocument getParentDocument() {
        XMLNode parentNode = getRootElement().getParentNode();
        if (parentNode instanceof XMLDocument) {
            return (XMLDocument) parentNode;
        }
        return null;
    }

    public XMLElement getParentElement() {
        XMLNode parentNode = getParentNode();
        if (parentNode instanceof XMLElement) {
            return (XMLElement) parentNode;
        }
        return null;
    }

    @Override // com.reandroid.xml.base.NamedNode
    public String getPrefix() {
        XMLNamespace namespace = getNamespace();
        if (namespace != null) {
            return namespace.getPrefix();
        }
        return null;
    }

    public XMLElement getRootElement() {
        XMLElement parentElement = getParentElement();
        return parentElement != null ? parentElement.getRootElement() : this;
    }

    public String getTextContent(boolean z) {
        StringWriter stringWriter = new StringWriter();
        try {
            Iterator<XMLNode> it = iterator();
            while (it.hasNext()) {
                it.next().write(stringWriter, true, z);
            }
            stringWriter.flush();
            stringWriter.close();
        } catch (IOException unused) {
        }
        return stringWriter.toString();
    }

    @Override // com.reandroid.xml.base.NamedNode
    public String getUri() {
        XMLNamespace namespace = getNamespace();
        if (namespace != null) {
            return namespace.getUri();
        }
        return null;
    }

    public XMLNamespace getXMLNamespace(String str, String str2) {
        if (str != null && str2 != null) {
            int namespaceCount = getNamespaceCount();
            for (int i = 0; i < namespaceCount; i++) {
                XMLNamespace namespaceAt = getNamespaceAt(i);
                if (namespaceAt.isEqual(str, str2)) {
                    return namespaceAt;
                }
            }
            XMLElement parentElement = getParentElement();
            if (parentElement != null) {
                return parentElement.getXMLNamespace(str, str2);
            }
        }
        return null;
    }

    public XMLNamespace getXMLNamespaceByPrefix(String str) {
        if (str == null) {
            return null;
        }
        int namespaceCount = getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            XMLNamespace namespaceAt = getNamespaceAt(i);
            if (str.equals(namespaceAt.getPrefix())) {
                return namespaceAt;
            }
        }
        XMLElement parentElement = getParentElement();
        if (parentElement != null) {
            return parentElement.getXMLNamespaceByPrefix(str);
        }
        return null;
    }

    public XMLNamespace getXMLNamespaceByUri(String str) {
        if (str == null) {
            return null;
        }
        int namespaceCount = getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            XMLNamespace namespaceAt = getNamespaceAt(i);
            if (str.equals(namespaceAt.getUri())) {
                return namespaceAt;
            }
        }
        XMLElement parentElement = getParentElement();
        if (parentElement != null) {
            return parentElement.getXMLNamespaceByUri(str);
        }
        return null;
    }

    public boolean hasAttribute(String str) {
        return getAttribute(str) != null;
    }

    public boolean isVoidHtml() {
        return this.mVoidHtml;
    }

    public Collection<XMLAttribute> listAttributes() {
        return this.mAttributes;
    }

    public XMLAttribute newAttribute() {
        XMLAttribute xMLAttribute = new XMLAttribute();
        addAttribute(xMLAttribute);
        return xMLAttribute;
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public XMLComment newComment() {
        XMLComment xMLComment = new XMLComment();
        add((XMLNode) xMLComment);
        return xMLComment;
    }

    @Override // com.reandroid.xml.base.Element, com.reandroid.xml.base.NodeFactory
    public XMLElement newElement() {
        XMLElement xMLElement = new XMLElement();
        add((XMLNode) xMLElement);
        return xMLElement;
    }

    @Override // com.reandroid.xml.base.Element
    public XMLNamespace newNamespace(String str, String str2) {
        XMLNamespace xMLNamespace = new XMLNamespace(str, str2);
        addNamespace(xMLNamespace);
        return xMLNamespace;
    }

    @Override // com.reandroid.xml.base.NodeFactory
    public XMLText newText() {
        XMLText xMLText = new XMLText();
        add((XMLNode) xMLText);
        return xMLText;
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public void onEndParse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        if (eventType != 3) {
            if (eventType == 1) {
                setVoidHtml(true);
                return;
            }
            return;
        }
        if ((xmlPullParser instanceof KXmlParser) && size() == 0 && ((KXmlParser) xmlPullParser).isClosedWithTag()) {
            newText("");
        }
        if (equalsName(xmlPullParser.getName())) {
            xmlPullParser.nextToken();
        } else if (XMLUtil.hasFeatureRelaxed(xmlPullParser)) {
            setVoidHtml(true);
        }
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public void onStartParse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLUtil.expectEvent(xmlPullParser, 2);
        parseNamespaces(xmlPullParser);
        setName(xmlPullParser.getNamespace(), xmlPullParser.getPrefix(), xmlPullParser.getName());
        parseAttributes(xmlPullParser);
        xmlPullParser.nextToken();
    }

    public void parseAttributes(XmlPullParser xmlPullParser) {
        boolean feature = xmlPullParser.getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (!XMLNamespace.looksNamespace(attributeName, attributeValue)) {
                String attributeNamespace = xmlPullParser.getAttributeNamespace(i);
                String attributePrefix = xmlPullParser.getAttributePrefix(i);
                if (feature) {
                    attributeName = XMLUtil.splitName(attributeName);
                }
                addAttribute(attributeNamespace, attributePrefix, attributeName, attributeValue);
            }
        }
    }

    public XMLAttribute removeAttribute(XMLAttribute xMLAttribute) {
        if (this.mAttributes.remove(xMLAttribute) && xMLAttribute != null) {
            xMLAttribute.setParentNode(null);
        }
        return xMLAttribute;
    }

    public XMLAttribute removeAttributeAt(int i) {
        XMLAttribute xMLAttributeRemove = this.mAttributes.remove(i);
        if (xMLAttributeRemove != null) {
            xMLAttributeRemove.setParentNode(null);
        }
        return xMLAttributeRemove;
    }

    public XMLAttribute setAttribute(String str, String str2) {
        if (StringsUtil.isEmpty(str)) {
            return null;
        }
        XMLAttribute attribute = getAttribute(str);
        if (attribute != null) {
            attribute.setValue(str2);
            return attribute;
        }
        if (XMLNamespace.looksNamespace(str, str2)) {
            newNamespace(str2, XMLUtil.splitName(str));
            return attribute;
        }
        newAttribute().set(str, str2);
        return attribute;
    }

    public void setName(String str, String str2, String str3) {
        XMLNamespace xMLNamespaceByPrefix;
        this.mName = XMLUtil.splitName(str3);
        if (str2 == null) {
            str2 = XMLUtil.splitPrefix(str3);
        }
        if (XMLUtil.isEmpty(str)) {
            str = null;
        }
        if (str == null && str2 == null) {
            return;
        }
        if (str == null) {
            xMLNamespaceByPrefix = getXMLNamespaceByPrefix(str2);
            if (xMLNamespaceByPrefix == null) {
                aca.a("Namespace not found for prefix: ", str2);
                return;
            }
        } else {
            XMLNamespace xMLNamespaceByUri = getXMLNamespaceByUri(str);
            if (xMLNamespaceByUri == null) {
                w01.a("Namespace not found for uri: ".concat(str));
                return;
            }
            xMLNamespaceByPrefix = xMLNamespaceByUri;
        }
        setNamespace(xMLNamespaceByPrefix);
    }

    @Override // com.reandroid.xml.base.NamedNode
    public void setNamespace(Namespace namespace) {
        if (namespace == null) {
            setNamespace(null, null);
        } else {
            setNamespace(namespace.getUri(), namespace.getPrefix());
        }
    }

    public void setTextContent(String str, boolean z) {
        super.clear();
        if (z) {
            str = XMLUtil.escapeXmlChars(str);
        }
        addText(str);
    }

    public void setVoidHtml(boolean z) {
        this.mVoidHtml = z;
        if (z) {
            transferChildrenToParent();
        }
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public void startSerialize(XmlSerializer xmlSerializer) throws IOException {
        int namespaceCount = getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            XMLNamespace namespaceAt = getNamespaceAt(i);
            xmlSerializer.setPrefix(namespaceAt.getPrefix(), namespaceAt.getUri());
        }
        xmlSerializer.startTag(getUri(), getName(false));
        int attributeCount = getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            getAttributeAt(i2).serialize(xmlSerializer);
        }
    }

    @Override // com.reandroid.xml.XMLNode
    public void write(Appendable appendable, boolean z, boolean z2) throws IOException {
        appendable.append('<');
        appendable.append(getName());
        appendAttributes(appendable, z, z2);
        Iterator<XMLNode> it = iterator();
        boolean z3 = false;
        while (it.hasNext()) {
            if (!z3) {
                appendable.append(">");
            }
            it.next().write(appendable, z, z2);
            z3 = true;
        }
        if (isVoidHtml()) {
            return;
        }
        if (!z3) {
            appendable.append(" />");
            return;
        }
        appendable.append("</");
        appendable.append(getName());
        appendable.append('>');
    }

    @Override // com.reandroid.xml.base.NamedNode
    public XMLNamespace getNamespace() {
        return this.mNamespace;
    }

    public XMLElement(String str) {
        this();
        setName(str);
    }

    public XMLAttribute removeAttribute(String str) {
        return removeAttribute(getAttribute(str));
    }

    public void setNamespace(XMLNamespace xMLNamespace) {
        this.mNamespace = xMLNamespace;
    }

    public void setNamespace(String str, String str2) {
        setNamespace(getOrCreateXMLNamespace(str, str2));
    }

    public XMLElement addAttribute(String str, String str2) {
        if (!StringsUtil.isEmpty(str)) {
            if (XMLNamespace.looksNamespace(str, str2)) {
                newNamespace(str2, XMLUtil.splitName(str));
                return this;
            }
            newAttribute().set(str, str2);
        }
        return this;
    }

    @Override // com.reandroid.xml.base.NamedNode
    public String getName() {
        return this.mName;
    }

    public void addNamespace(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        newNamespace(str, str2);
    }

    public String getTextContent() {
        return getTextContent(false);
    }

    public void addAttribute(Attribute attribute) {
        if (attribute == null) {
            return;
        }
        XMLAttribute xMLAttribute = (XMLAttribute) attribute;
        if (this.mAttributes == EMPTY_ATTRIBUTES) {
            this.mAttributes = new ArrayCollection<>();
        }
        this.mAttributes.add(xMLAttribute);
        xMLAttribute.setParentNode(this);
    }

    public void addAttribute(int i, Attribute attribute) {
        if (attribute == null) {
            return;
        }
        XMLAttribute xMLAttribute = (XMLAttribute) attribute;
        if (this.mAttributes == EMPTY_ATTRIBUTES) {
            this.mAttributes = new ArrayCollection<>();
        }
        this.mAttributes.add(i, xMLAttribute);
        xMLAttribute.setParentNode(this);
    }

    public void setName(String str, String str2) {
        setName(str, null, str2);
    }

    @Override // com.reandroid.xml.base.NamedNode
    public void setName(String str) {
        setName(null, null, str);
    }
}
