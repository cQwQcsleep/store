package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import defpackage.dfc;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
abstract class ResXmlDocumentOrElement extends ResXmlNodeTree {
    public ResXmlDocumentOrElement(Block block) {
        super(block);
    }

    private ResXmlNode createForEvent(int i) {
        if (i == 2) {
            return newElement();
        }
        if (ResXmlNode.isTextEvent(i)) {
            return getOrCreateLastText();
        }
        if (i == 9) {
            return newText();
        }
        if (i == 0) {
            return newDocument();
        }
        gke.a("Unexpected event ", i);
        return null;
    }

    private boolean isEndEvent(int i) {
        return (i == 3 && isElement()) || (i == 1 && isDocument());
    }

    public static /* synthetic */ boolean k(Predicate predicate, ResXmlNode resXmlNode) {
        return (resXmlNode instanceof ResXmlElement) && predicate.test((ResXmlElement) resXmlNode);
    }

    public boolean autoSetAttributeNames() {
        Iterator<ResXmlAttribute> itRecursiveAttributes = recursiveAttributes();
        boolean z = false;
        while (itRecursiveAttributes.hasNext()) {
            if (itRecursiveAttributes.next().autoSetName()) {
                z = true;
            }
        }
        return z;
    }

    public void fixAttributeNames() {
        Iterator<ResXmlAttribute> itRecursiveAttributes = recursiveAttributes();
        while (itRecursiveAttributes.hasNext()) {
            itRecursiveAttributes.next().autoSetName();
        }
    }

    public void fixNamespaces() {
        Iterator<ResXmlElement> elements = getElements();
        while (elements.hasNext()) {
            elements.next().fixNamespaces();
        }
    }

    public ResXmlElement getElement(String str) {
        return (ResXmlElement) CollectionUtil.getFirst(getElements(str));
    }

    public Iterator<ResXmlElement> getElements(final String str) {
        return iterator(ResXmlElement.class, new Predicate() { // from class: com.reandroid.arsc.chunk.xml.b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlElement) obj).equalsName(str);
            }
        });
    }

    public int getElementsCount(String str) {
        return CollectionUtil.count(getElements(str));
    }

    public Iterator<ResXmlElement> getElementsWithChild(final String[] strArr, int i) {
        int length = strArr.length - 1;
        if (i > length) {
            return EmptyIterator.of();
        }
        Iterator<ResXmlElement> elements = getElements(strArr[i]);
        if (i == length || !elements.hasNext()) {
            return elements;
        }
        final int i2 = i + 1;
        return new IterableIterator<ResXmlElement, ResXmlElement>(elements) { // from class: com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement.1
            public Iterator<ResXmlElement> iterator(ResXmlElement resXmlElement) {
                return resXmlElement.getElementsWithChild(strArr, i2);
            }
        };
    }

    public ResXmlElement getOrCreateElement(String str) {
        ResXmlElement element = getElement(str);
        return element == null ? newElement(str) : element;
    }

    public ResXmlTextNode getOrCreateLastText() {
        int size = size();
        ResXmlTextNode resXmlTextNode = null;
        if (size != 0) {
            ResXmlNode resXmlNodeM61get = m61get(size - 1);
            if (resXmlNodeM61get instanceof ResXmlTextNode) {
                ResXmlTextNode resXmlTextNode2 = (ResXmlTextNode) resXmlNodeM61get;
                if (!resXmlTextNode2.isComment()) {
                    resXmlTextNode = resXmlTextNode2;
                }
            }
        }
        return resXmlTextNode == null ? newText() : resXmlTextNode;
    }

    public Iterator<ResXmlTextNode> getTexts() {
        return iterator(ResXmlTextNode.class);
    }

    public int getTextsCount() {
        return countNodeWithType(ResXmlTextNode.class);
    }

    public boolean hasElement() {
        return containsNodeWithType(ResXmlElement.class);
    }

    public boolean hasText() {
        return containsNodeWithType(ResXmlTextNode.class);
    }

    public int lastIndexOf(String str) {
        ResXmlElement resXmlElement = (ResXmlElement) CollectionUtil.getLast(getElements(str));
        if (resXmlElement != null) {
            return resXmlElement.getIndex();
        }
        return -1;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void linkStringReferences() {
        Iterator<ResXmlNode> it = iterator();
        while (it.hasNext()) {
            it.next().linkStringReferences();
        }
    }

    @Deprecated
    public List<ResXmlElement> listElements(String str) {
        return CollectionUtil.toList(getElements(str));
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public ResXmlDocument newDocument() {
        ResXmlDocument resXmlDocument = new ResXmlDocument();
        add((ResXmlNode) resXmlDocument);
        return resXmlDocument;
    }

    public ResXmlElement newElement(String str) {
        ResXmlElement resXmlElement = new ResXmlElement();
        add((ResXmlNode) resXmlElement);
        if (str != null) {
            resXmlElement.setName(str);
        }
        return resXmlElement;
    }

    public ResXmlElement newElementAt(int i, String str) {
        ResXmlElement resXmlElement = new ResXmlElement();
        add(i, (ResXmlNode) resXmlElement);
        if (str != null) {
            resXmlElement.setName(str);
        }
        return resXmlElement;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public ResXmlTextNode newText() {
        ResXmlTextNode resXmlTextNode = new ResXmlTextNode();
        add((ResXmlNode) resXmlTextNode);
        return resXmlTextNode;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public UnknownResXmlNode newUnknown(ChunkType chunkType) {
        UnknownResXmlNode unexpectedResXmlNode = UnexpectedResXmlNode.isSet(chunkType) ? new UnexpectedResXmlNode(chunkType) : new UnknownResXmlNode();
        add((ResXmlNode) unexpectedResXmlNode);
        return unexpectedResXmlNode;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        parseInnerNodes(xmlPullParser);
    }

    public void parseInnerNodes(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        while (true) {
            int eventType = xmlPullParser.getEventType();
            if (isEndEvent(eventType)) {
                return;
            } else {
                createForEvent(eventType).parse(xmlPullParser);
            }
        }
    }

    public Iterator<ResXmlAttribute> recursiveAttributes() {
        IterableIterator<ResXmlElement, ResXmlAttribute> iterableIterator = new IterableIterator<ResXmlElement, ResXmlAttribute>(recursiveElements()) { // from class: com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement.2
            public Iterator<ResXmlAttribute> iterator(ResXmlElement resXmlElement) {
                return resXmlElement.getAttributes();
            }
        };
        return this instanceof ResXmlElement ? CombiningIterator.two(((ResXmlElement) this).getAttributes(), iterableIterator) : iterableIterator;
    }

    public Iterator<ResXmlElement> recursiveElements() {
        return recursive(ResXmlElement.class);
    }

    public boolean removeElementsIf(final Predicate<? super ResXmlElement> predicate) {
        return removeIf(new Predicate() { // from class: com.reandroid.arsc.chunk.xml.a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ResXmlDocumentOrElement.k(predicate, (ResXmlNode) obj);
            }
        });
    }

    public boolean removeNullElements() {
        boolean zRemoveElementsIf = removeElementsIf(new dfc());
        Iterator<ResXmlElement> elements = getElements();
        while (elements.hasNext()) {
            if (elements.next().removeNullElements()) {
                zRemoveElementsIf = true;
            }
        }
        return zRemoveElementsIf;
    }

    public void removeUndefinedAttributes() {
        Iterator<ResXmlElement> elements = getElements();
        while (elements.hasNext()) {
            elements.next().removeUndefinedAttributes();
        }
    }

    public void removeUnusedNamespaces() {
        Iterator<ResXmlElement> elements = getElements();
        while (elements.hasNext()) {
            elements.next().removeUnusedNamespaces();
        }
    }

    public void setAttributesUnitSize(int i, boolean z) {
        Iterator<ResXmlElement> elements = getElements();
        while (elements.hasNext()) {
            elements.next().setAttributesUnitSize(i, z);
        }
    }

    public Iterator<ResXmlElement> recursiveElements(Predicate<? super ResXmlElement> predicate) {
        return recursive(ResXmlElement.class, predicate);
    }

    public int getElementsCount() {
        return countNodeWithType(ResXmlElement.class);
    }

    public Iterator<ResXmlElement> getElements() {
        return iterator(ResXmlElement.class);
    }

    public Iterator<ResXmlElement> getElements(Predicate<? super ResXmlElement> predicate) {
        return iterator(ResXmlElement.class, predicate);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public ResXmlElement newElement() {
        return newElement(null);
    }

    public ResXmlElement newElementAt(int i) {
        return newElementAt(i, null);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public UnknownResXmlNode newUnknown() {
        return newUnknown(ChunkType.NULL);
    }

    public Iterator<ResXmlAttribute> recursiveAttributes(Predicate<? super ResXmlAttribute> predicate) {
        return FilterIterator.of(recursiveAttributes(), predicate);
    }

    public Iterator<ResXmlElement> getElementsWithChild(String... strArr) {
        if (strArr != null && strArr.length != 0) {
            return getElementsWithChild(strArr, 0);
        }
        return EmptyIterator.of();
    }
}
