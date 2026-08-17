package com.reandroid.xml;

import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.IndexIterator;
import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.utils.collection.RecursiveIterator;
import com.reandroid.utils.collection.ReversedIterator;
import com.reandroid.utils.collection.SizedSupplier;
import com.reandroid.xml.XMLElement;
import com.reandroid.xml.XMLNode;
import com.reandroid.xml.base.NodeTree;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class XMLNodeTree extends XMLNode implements NodeTree<XMLNode>, Iterable<XMLNode>, SizedSupplier<XMLNode> {
    private static final ArrayCollection<XMLNode> EMPTY = ArrayCollection.empty();
    private static final int TRIM_INTERVAL = 1000;
    private int lastTrimSize;
    private ArrayCollection<XMLNode> mNodeList = EMPTY;

    private int getEndEvent() {
        return this instanceof XMLElement ? 3 : 1;
    }

    private void serializeChildes(XmlSerializer xmlSerializer) throws IOException {
        Iterator<XMLNode> it = iterator();
        while (it.hasNext()) {
            it.next().serialize(xmlSerializer);
        }
    }

    @Override // com.reandroid.xml.base.NodeTree
    public boolean add(XMLNode xMLNode) {
        boolean zAdd;
        if (xMLNode == null || xMLNode == this) {
            return false;
        }
        synchronized (this) {
            try {
                if (this.mNodeList == EMPTY) {
                    this.mNodeList = new ArrayCollection<>();
                }
                if (this.mNodeList.containsExact(xMLNode)) {
                    throw new IllegalArgumentException("Duplicate node: " + xMLNode);
                }
                zAdd = this.mNodeList.add(xMLNode);
                xMLNode.setParentNode(this);
                if (this.mNodeList.size() - this.lastTrimSize > TRIM_INTERVAL) {
                    this.mNodeList.trimToSize();
                    this.lastTrimSize = this.mNodeList.size();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return zAdd;
    }

    public void addAll(Iterable<? extends XMLNode> iterable) {
        Iterator<? extends XMLNode> it = iterable.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    @Override // com.reandroid.xml.base.NodeTree
    public void clear() {
        if (size() == 0) {
            return;
        }
        synchronized (this) {
            this.mNodeList.clear();
            this.mNodeList.trimToSize();
            this.lastTrimSize = 0;
        }
    }

    public XMLNode createForEvent(int i) {
        if (i == 2) {
            return newElement();
        }
        if (i == 10) {
            return newDocType();
        }
        if (XMLText.isTextEvent(i)) {
            return getOrCreateLastText();
        }
        if (i == 9) {
            return newComment();
        }
        if (i == 8) {
            return newProcessingInstruction();
        }
        if (i == 5) {
            return newCDSect();
        }
        return null;
    }

    public abstract void endSerialize(XmlSerializer xmlSerializer) throws IOException;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.reandroid.utils.collection.SizedSupplier
    public XMLNode get(int i) {
        XMLNode xMLNode;
        synchronized (this) {
            xMLNode = this.mNodeList.get(i);
        }
        return xMLNode;
    }

    public XMLElement getElement(String str) {
        return (XMLElement) CollectionUtil.getFirst(getElements(str));
    }

    public Iterator<XMLElement> getElements(final String str) {
        return InstanceIterator.of(iterator(), XMLElement.class, new Predicate() { // from class: vxf
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((XMLElement) obj).equalsName(str);
            }
        });
    }

    public Iterator<? extends XMLElement> getElementsReversed() {
        return InstanceIterator.of(reversedIterator(), XMLElement.class);
    }

    public XMLNode getLast() {
        int size = size();
        if (size == 0) {
            return null;
        }
        return this.mNodeList.get(size - 1);
    }

    public XMLElement getLastElement() {
        return (XMLElement) CollectionUtil.getFirst(getElementsReversed());
    }

    public XMLElement getOrCreateElement(String str) {
        XMLElement element = getElement(str);
        return element == null ? newElement(str) : element;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    public XMLText getOrCreateFirstText(String str) {
        XMLText xMLText;
        if (size() != 0) {
            XMLNode xMLNode = get(0);
            if (xMLNode instanceof XMLText) {
                xMLText = (XMLText) xMLNode;
                xMLText.setText(str);
            } else {
                xMLText = null;
            }
        } else {
            xMLText = null;
        }
        return xMLText == null ? newText(str) : xMLText;
    }

    public XMLText getOrCreateLastText() {
        XMLNode last = getLast();
        return last instanceof XMLText ? (XMLText) last : newText();
    }

    public Iterator<? extends XMLText> getTexts() {
        return iterator(XMLText.class);
    }

    public boolean hasChildElements() {
        return !CollectionUtil.isEmpty(getElements());
    }

    public boolean hasTextNode() {
        return !CollectionUtil.isEmpty(getTexts());
    }

    public int indexOf(XMLNode xMLNode) {
        return this.mNodeList.indexOf(xMLNode);
    }

    public int indexOfExact(XMLNode xMLNode) {
        return this.mNodeList.indexOfExact(xMLNode);
    }

    public Iterator<XMLNode> iterator(Predicate<? super XMLNode> predicate) {
        return new IndexIterator(this, predicate);
    }

    public void move(Object obj, int i) {
        this.mNodeList.move(obj, i);
    }

    public XMLCDSect newCDSect() {
        XMLCDSect xMLCDSect = new XMLCDSect();
        add((XMLNode) xMLCDSect);
        return xMLCDSect;
    }

    public XMLComment newComment() {
        XMLComment xMLComment = new XMLComment();
        add((XMLNode) xMLComment);
        return xMLComment;
    }

    public XMLDocType newDocType() {
        XMLDocType xMLDocType = new XMLDocType();
        add((XMLNode) xMLDocType);
        return xMLDocType;
    }

    public abstract XMLElement newElement();

    public XMLElement newElement(String str) {
        XMLElement xMLElementNewElement = newElement();
        xMLElementNewElement.setName(str);
        return xMLElementNewElement;
    }

    public XMLNamespace newNamespace(String str, String str2) {
        return new XMLNamespace(str, str2);
    }

    public XMLProcessingInstruction newProcessingInstruction() {
        XMLProcessingInstruction xMLProcessingInstruction = new XMLProcessingInstruction();
        add((XMLNode) xMLProcessingInstruction);
        return xMLProcessingInstruction;
    }

    public abstract XMLText newText();

    public XMLText newText(String str) {
        XMLText xMLTextNewText = newText();
        xMLTextNewText.setText(str);
        return xMLTextNewText;
    }

    public void onEndParse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public void onStartParse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public void onUnknownEvent(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.nextToken();
    }

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.XmlReader
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        onStartParse(xmlPullParser);
        parseInner(xmlPullParser);
        onEndParse(xmlPullParser);
    }

    public void parseInner(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int endEvent = getEndEvent();
        int eventType = xmlPullParser.getEventType();
        while (eventType != endEvent && eventType != 1) {
            XMLNode xMLNodeCreateForEvent = createForEvent(eventType);
            if (xMLNodeCreateForEvent != null) {
                xMLNodeCreateForEvent.parse(xmlPullParser);
            } else {
                onUnknownEvent(xmlPullParser);
            }
            eventType = xmlPullParser.getEventType();
        }
    }

    public Iterator<XMLNode> recursiveNodes() {
        return RecursiveIterator.of(this, new Function() { // from class: wxf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((XMLNode) obj).iterator();
            }
        });
    }

    @Override // com.reandroid.xml.base.NodeTree
    public boolean remove(XMLNode xMLNode) {
        synchronized (this) {
            if (xMLNode != null) {
                try {
                    if (this.mNodeList.remove(xMLNode)) {
                        xMLNode.setParentNode(null);
                        return true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return false;
        }
    }

    @Override // com.reandroid.xml.base.NodeTree
    public boolean removeIf(Predicate<? super XMLNode> predicate) {
        boolean zRemoveIf;
        synchronized (this) {
            zRemoveIf = this.mNodeList.removeIf(predicate);
        }
        return zRemoveIf;
    }

    public Iterator<XMLNode> reversedIterator() {
        int size = size();
        return new ReversedIterator<XMLNode>(size - 1, size) { // from class: com.reandroid.xml.XMLNodeTree.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.reandroid.utils.collection.ReversedIterator
            public XMLNode get(int i) {
                return XMLNodeTree.this.get(i);
            }
        };
    }

    @Override // com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        startSerialize(xmlSerializer);
        serializeChildes(xmlSerializer);
        endSerialize(xmlSerializer);
    }

    @Override // com.reandroid.xml.base.NodeTree, com.reandroid.utils.collection.SizedItem
    public int size() {
        return this.mNodeList.size();
    }

    @Override // com.reandroid.xml.base.NodeTree
    public boolean sort(Comparator<? super XMLNode> comparator) {
        boolean zSortItems;
        synchronized (this) {
            zSortItems = this.mNodeList.sortItems(comparator);
        }
        return zSortItems;
    }

    public abstract void startSerialize(XmlSerializer xmlSerializer) throws IOException;

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.NodeTree, java.lang.Iterable
    public Iterator<XMLNode> iterator() {
        return new IndexIterator(this);
    }

    public XMLDocType newDocType(String str) {
        XMLDocType xMLDocTypeNewDocType = newDocType();
        xMLDocTypeNewDocType.setName(str);
        return xMLDocTypeNewDocType;
    }

    public Iterator<? extends XMLElement> getElements() {
        return iterator(XMLElement.class);
    }

    @Override // com.reandroid.xml.base.NodeTree
    public XMLNode remove(int i) {
        XMLNode xMLNodeRemove;
        synchronized (this) {
            try {
                xMLNodeRemove = this.mNodeList.remove(i);
                if (xMLNodeRemove != null) {
                    xMLNodeRemove.setParentNode(null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return xMLNodeRemove;
    }

    @Deprecated
    public boolean remove(Predicate<? super XMLNode> predicate) {
        throw new RuntimeException("Depreciated method");
    }

    @Override // com.reandroid.xml.base.NodeTree
    public void add(int i, XMLNode xMLNode) {
        if (xMLNode == null || xMLNode == this) {
            return;
        }
        synchronized (this) {
            try {
                if (this.mNodeList == EMPTY) {
                    this.mNodeList = new ArrayCollection<>();
                }
                if (this.mNodeList.containsExact(xMLNode)) {
                    throw new IllegalArgumentException("Duplicate node: " + xMLNode);
                }
                this.mNodeList.add(i, xMLNode);
                xMLNode.setParentNode(this);
                if (this.mNodeList.size() - this.lastTrimSize > TRIM_INTERVAL) {
                    this.mNodeList.trimToSize();
                    this.lastTrimSize = this.mNodeList.size();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
