package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.dom.events.EventImpl;
import com.sun.org.apache.xerces.internal.dom.events.MutationEventImpl;
import defpackage.zi0;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.events.DocumentEvent;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.ranges.DocumentRange;
import org.w3c.dom.ranges.Range;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.TreeWalker;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DocumentImpl extends CoreDocumentImpl implements DocumentTraversal, DocumentEvent, DocumentRange {
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("iterators", Vector.class), new ObjectStreamField("ranges", Vector.class), new ObjectStreamField("eventListeners", Hashtable.class), new ObjectStreamField("mutationEvents", Boolean.TYPE)};
    static final long serialVersionUID = 515687835542616694L;
    protected Map<NodeImpl, List<LEntry>> eventListeners;
    protected List<NodeIterator> iterators;
    protected boolean mutationEvents;
    protected List<Range> ranges;
    EnclosingAttr savedEnclosingAttr;

    public class EnclosingAttr implements Serializable {
        private static final long serialVersionUID = 5208387723391647216L;
        AttrImpl node;
        String oldvalue;

        public EnclosingAttr() {
        }
    }

    public class LEntry implements Serializable {
        private static final long serialVersionUID = -8426757059492421631L;
        EventListener listener;
        String type;
        boolean useCapture;

        public LEntry(String str, EventListener eventListener, boolean z) {
            this.type = str;
            this.listener = eventListener;
            this.useCapture = z;
        }
    }

    public DocumentImpl() {
        this.mutationEvents = false;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        ObjectInputStream.GetField fields = objectInputStream.readFields();
        Vector vector = (Vector) fields.get("iterators", (Object) null);
        Vector vector2 = (Vector) fields.get("ranges", (Object) null);
        Hashtable hashtable = (Hashtable) fields.get("eventListeners", (Object) null);
        this.mutationEvents = fields.get("mutationEvents", false);
        if (vector != null) {
            this.iterators = new ArrayList(vector);
        }
        if (vector2 != null) {
            this.ranges = new ArrayList(vector2);
        }
        if (hashtable != null) {
            this.eventListeners = new HashMap();
            for (Map.Entry entry : hashtable.entrySet()) {
                this.eventListeners.put((NodeImpl) entry.getKey(), new ArrayList((Collection) entry.getValue()));
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Hashtable hashtable = null;
        Vector vector = this.iterators == null ? null : new Vector(this.iterators);
        Vector vector2 = this.ranges == null ? null : new Vector(this.ranges);
        if (this.eventListeners != null) {
            hashtable = new Hashtable();
            for (Map.Entry<NodeImpl, List<LEntry>> entry : this.eventListeners.entrySet()) {
                hashtable.put(entry.getKey(), new Vector(entry.getValue()));
            }
        }
        ObjectOutputStream.PutField putFieldPutFields = objectOutputStream.putFields();
        putFieldPutFields.put("iterators", vector);
        putFieldPutFields.put("ranges", vector2);
        putFieldPutFields.put("eventListeners", hashtable);
        putFieldPutFields.put("mutationEvents", this.mutationEvents);
        objectOutputStream.writeFields();
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void addEventListener(NodeImpl nodeImpl, String str, EventListener eventListener, boolean z) {
        if (str == null || str.equals("") || eventListener == null) {
            return;
        }
        removeEventListener(nodeImpl, str, eventListener, z);
        List<LEntry> eventListeners = getEventListeners(nodeImpl);
        if (eventListeners == null) {
            eventListeners = new ArrayList<>();
            setEventListeners(nodeImpl, eventListeners);
        }
        eventListeners.add(new LEntry(str, eventListener, z));
        LCount lCountLookup = LCount.lookup(str);
        if (z) {
            lCountLookup.captures++;
            lCountLookup.total++;
        } else {
            lCountLookup.bubbles++;
            lCountLookup.total++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, com.sun.org.apache.xerces.internal.dom.ParentNode, com.sun.org.apache.xerces.internal.dom.ChildNode, com.sun.org.apache.xerces.internal.dom.NodeImpl, org.w3c.dom.Node
    public Node cloneNode(boolean z) {
        DocumentImpl documentImpl = new DocumentImpl();
        callUserDataHandlers(this, documentImpl, (short) 1);
        cloneNode(documentImpl, z);
        documentImpl.mutationEvents = this.mutationEvents;
        return documentImpl;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void copyEventListeners(NodeImpl nodeImpl, NodeImpl nodeImpl2) {
        List<LEntry> eventListeners = getEventListeners(nodeImpl);
        if (eventListeners == null) {
            return;
        }
        setEventListeners(nodeImpl2, new ArrayList(eventListeners));
    }

    @Override // org.w3c.dom.events.DocumentEvent
    public Event createEvent(String str) throws DOMException {
        if (str.equalsIgnoreCase("Events") || "Event".equals(str)) {
            return new EventImpl();
        }
        if (str.equalsIgnoreCase("MutationEvents") || "MutationEvent".equals(str)) {
            return new MutationEventImpl();
        }
        zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        return null;
    }

    @Override // org.w3c.dom.traversal.DocumentTraversal
    public NodeIterator createNodeIterator(Node node, int i, NodeFilter nodeFilter, boolean z) {
        if (node == null) {
            zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
            return null;
        }
        NodeIteratorImpl nodeIteratorImpl = new NodeIteratorImpl(this, node, i, nodeFilter, z);
        if (this.iterators == null) {
            this.iterators = new ArrayList();
        }
        this.iterators.add(nodeIteratorImpl);
        return nodeIteratorImpl;
    }

    @Override // org.w3c.dom.ranges.DocumentRange
    public Range createRange() {
        if (this.ranges == null) {
            this.ranges = new ArrayList();
        }
        RangeImpl rangeImpl = new RangeImpl(this);
        this.ranges.add(rangeImpl);
        return rangeImpl;
    }

    @Override // org.w3c.dom.traversal.DocumentTraversal
    public TreeWalker createTreeWalker(Node node, int i, NodeFilter nodeFilter, boolean z) {
        if (node != null) {
            return new TreeWalkerImpl(node, i, nodeFilter, z);
        }
        zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NOT_SUPPORTED_ERR", null));
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void deletedText(NodeImpl nodeImpl, int i, int i2) {
        List<Range> list = this.ranges;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 != size; i3++) {
                ((RangeImpl) this.ranges.get(i3)).receiveDeletedText(nodeImpl, i, i2);
            }
        }
    }

    public void dispatchAggregateEvents(NodeImpl nodeImpl, AttrImpl attrImpl, String str, short s) {
        NodeImpl nodeImpl2;
        NodeImpl nodeImpl3;
        if (attrImpl != null) {
            LCount lCountLookup = LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED);
            nodeImpl3 = (NodeImpl) attrImpl.getOwnerElement();
            if (lCountLookup.total <= 0 || nodeImpl3 == null) {
                nodeImpl2 = attrImpl;
            } else {
                MutationEventImpl mutationEventImpl = new MutationEventImpl();
                nodeImpl2 = attrImpl;
                mutationEventImpl.initMutationEvent(MutationEventImpl.DOM_ATTR_MODIFIED, true, false, nodeImpl2, str, attrImpl.getNodeValue(), attrImpl.getNodeName(), s);
                nodeImpl3.dispatchEvent(mutationEventImpl);
            }
        } else {
            nodeImpl2 = attrImpl;
            nodeImpl3 = null;
        }
        if (LCount.lookup(MutationEventImpl.DOM_SUBTREE_MODIFIED).total > 0) {
            MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
            mutationEventImpl2.initMutationEvent(MutationEventImpl.DOM_SUBTREE_MODIFIED, true, false, null, null, null, null, (short) 0);
            if (nodeImpl2 == null) {
                dispatchEvent(nodeImpl, mutationEventImpl2);
                return;
            }
            dispatchEvent(nodeImpl2, mutationEventImpl2);
            if (nodeImpl3 != null) {
                dispatchEvent(nodeImpl3, mutationEventImpl2);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public boolean dispatchEvent(NodeImpl nodeImpl, Event event) {
        String str;
        if (event == null) {
            return false;
        }
        EventImpl eventImpl = (EventImpl) event;
        if (!eventImpl.initialized || (str = eventImpl.type) == null || str.equals("")) {
            throw new EventException((short) 0, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "UNSPECIFIED_EVENT_TYPE_ERR", null));
        }
        LCount lCountLookup = LCount.lookup(eventImpl.getType());
        if (lCountLookup.total == 0) {
            return eventImpl.preventDefault;
        }
        eventImpl.target = nodeImpl;
        eventImpl.stopPropagation = false;
        eventImpl.preventDefault = false;
        ArrayList arrayList = new ArrayList(10);
        for (Node parentNode = nodeImpl.getParentNode(); parentNode != null; parentNode = parentNode.getParentNode()) {
            arrayList.add(parentNode);
        }
        if (lCountLookup.captures > 0) {
            eventImpl.eventPhase = (short) 1;
            for (int size = arrayList.size() - 1; size >= 0 && !eventImpl.stopPropagation; size--) {
                NodeImpl nodeImpl2 = (NodeImpl) arrayList.get(size);
                eventImpl.currentTarget = nodeImpl2;
                ArrayList arrayList2 = (ArrayList) getEventListeners(nodeImpl2);
                if (arrayList2 != null) {
                    ArrayList arrayList3 = (ArrayList) arrayList2.clone();
                    int size2 = arrayList3.size();
                    for (int i = 0; i < size2; i++) {
                        LEntry lEntry = (LEntry) arrayList3.get(i);
                        if (lEntry.useCapture && lEntry.type.equals(eventImpl.type) && arrayList2.contains(lEntry)) {
                            try {
                                lEntry.listener.handleEvent(eventImpl);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
        }
        if (lCountLookup.bubbles > 0) {
            eventImpl.eventPhase = (short) 2;
            eventImpl.currentTarget = nodeImpl;
            ArrayList arrayList4 = (ArrayList) getEventListeners(nodeImpl);
            if (!eventImpl.stopPropagation && arrayList4 != null) {
                ArrayList arrayList5 = (ArrayList) arrayList4.clone();
                int size3 = arrayList5.size();
                for (int i2 = 0; i2 < size3; i2++) {
                    LEntry lEntry2 = (LEntry) arrayList5.get(i2);
                    if (!lEntry2.useCapture && lEntry2.type.equals(eventImpl.type) && arrayList4.contains(lEntry2)) {
                        try {
                            lEntry2.listener.handleEvent(eventImpl);
                        } catch (Exception unused2) {
                        }
                    }
                }
            }
            if (eventImpl.bubbles) {
                eventImpl.eventPhase = (short) 3;
                int size4 = arrayList.size();
                for (int i3 = 0; i3 < size4 && !eventImpl.stopPropagation; i3++) {
                    NodeImpl nodeImpl3 = (NodeImpl) arrayList.get(i3);
                    eventImpl.currentTarget = nodeImpl3;
                    ArrayList arrayList6 = (ArrayList) getEventListeners(nodeImpl3);
                    if (arrayList6 != null) {
                        ArrayList arrayList7 = (ArrayList) arrayList6.clone();
                        int size5 = arrayList7.size();
                        for (int i4 = 0; i4 < size5; i4++) {
                            LEntry lEntry3 = (LEntry) arrayList7.get(i4);
                            if (!lEntry3.useCapture && lEntry3.type.equals(eventImpl.type) && arrayList6.contains(lEntry3)) {
                                try {
                                    lEntry3.listener.handleEvent(eventImpl);
                                } catch (Exception unused3) {
                                }
                            }
                        }
                    }
                }
            }
        }
        return eventImpl.preventDefault;
    }

    public void dispatchEventToSubtree(Node node, Event event) {
        ((NodeImpl) node).dispatchEvent(event);
        if (node.getNodeType() == 1) {
            NamedNodeMap attributes = node.getAttributes();
            for (int length = attributes.getLength() - 1; length >= 0; length--) {
                dispatchingEventToSubtree(attributes.item(length), event);
            }
        }
        dispatchingEventToSubtree(node.getFirstChild(), event);
    }

    public void dispatchingEventToSubtree(Node node, Event event) {
        if (node == null) {
            return;
        }
        ((NodeImpl) node).dispatchEvent(event);
        if (node.getNodeType() == 1) {
            NamedNodeMap attributes = node.getAttributes();
            for (int length = attributes.getLength() - 1; length >= 0; length--) {
                dispatchingEventToSubtree(attributes.item(length), event);
            }
        }
        dispatchingEventToSubtree(node.getFirstChild(), event);
        dispatchingEventToSubtree(node.getNextSibling(), event);
    }

    public List<LEntry> getEventListeners(NodeImpl nodeImpl) {
        Map<NodeImpl, List<LEntry>> map = this.eventListeners;
        if (map == null) {
            return null;
        }
        return map.get(nodeImpl);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl, org.w3c.dom.Document
    public DOMImplementation getImplementation() {
        return DOMImplementationImpl.getDOMImplementation();
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public boolean getMutationEvents() {
        return this.mutationEvents;
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void insertedNode(NodeImpl nodeImpl, NodeImpl nodeImpl2, boolean z) {
        if (this.mutationEvents) {
            if (LCount.lookup(MutationEventImpl.DOM_NODE_INSERTED).total > 0) {
                MutationEventImpl mutationEventImpl = new MutationEventImpl();
                mutationEventImpl.initMutationEvent(MutationEventImpl.DOM_NODE_INSERTED, true, false, nodeImpl, null, null, null, (short) 0);
                dispatchEvent(nodeImpl2, mutationEventImpl);
            }
            if (LCount.lookup(MutationEventImpl.DOM_NODE_INSERTED_INTO_DOCUMENT).total > 0) {
                EnclosingAttr enclosingAttr = this.savedEnclosingAttr;
                NodeImpl nodeImplParentNode = enclosingAttr != null ? (NodeImpl) enclosingAttr.node.getOwnerElement() : nodeImpl;
                if (nodeImplParentNode != null) {
                    NodeImpl nodeImpl3 = nodeImplParentNode;
                    while (nodeImplParentNode != null) {
                        nodeImpl3 = nodeImplParentNode;
                        nodeImplParentNode = nodeImplParentNode.getNodeType() == 2 ? (NodeImpl) ((AttrImpl) nodeImplParentNode).getOwnerElement() : nodeImplParentNode.parentNode();
                    }
                    if (nodeImpl3.getNodeType() == 9) {
                        MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
                        mutationEventImpl2.initMutationEvent(MutationEventImpl.DOM_NODE_INSERTED_INTO_DOCUMENT, false, false, null, null, null, null, (short) 0);
                        dispatchEventToSubtree(nodeImpl2, mutationEventImpl2);
                    }
                }
            }
            if (!z) {
                dispatchAggregateEvents(nodeImpl, this.savedEnclosingAttr);
            }
        }
        List<Range> list = this.ranges;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i != size; i++) {
                ((RangeImpl) this.ranges.get(i)).insertedNodeFromDOM(nodeImpl2);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void insertedText(NodeImpl nodeImpl, int i, int i2) {
        List<Range> list = this.ranges;
        if (list != null) {
            int size = list.size();
            for (int i3 = 0; i3 != size; i3++) {
                ((RangeImpl) this.ranges.get(i3)).receiveInsertedText(nodeImpl, i, i2);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void insertingNode(NodeImpl nodeImpl, boolean z) {
        if (!this.mutationEvents || z) {
            return;
        }
        saveEnclosingAttr(nodeImpl);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void modifiedAttrValue(AttrImpl attrImpl, String str) {
        if (this.mutationEvents) {
            dispatchAggregateEvents(attrImpl, attrImpl, str, (short) 1);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void modifiedCharacterData(NodeImpl nodeImpl, String str, String str2, boolean z) {
        if (!this.mutationEvents || z) {
            return;
        }
        if (LCount.lookup(MutationEventImpl.DOM_CHARACTER_DATA_MODIFIED).total > 0) {
            MutationEventImpl mutationEventImpl = new MutationEventImpl();
            mutationEventImpl.initMutationEvent(MutationEventImpl.DOM_CHARACTER_DATA_MODIFIED, true, false, null, str, str2, null, (short) 0);
            dispatchEvent(nodeImpl, mutationEventImpl);
        }
        dispatchAggregateEvents(nodeImpl, this.savedEnclosingAttr);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void modifyingCharacterData(NodeImpl nodeImpl, boolean z) {
        if (!this.mutationEvents || z) {
            return;
        }
        saveEnclosingAttr(nodeImpl);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void removeEventListener(NodeImpl nodeImpl, String str, EventListener eventListener, boolean z) {
        List<LEntry> eventListeners;
        if (str == null || str.equals("") || eventListener == null || (eventListeners = getEventListeners(nodeImpl)) == null) {
            return;
        }
        for (int size = eventListeners.size() - 1; size >= 0; size--) {
            LEntry lEntry = eventListeners.get(size);
            if (lEntry.useCapture == z && lEntry.listener == eventListener && lEntry.type.equals(str)) {
                eventListeners.remove(size);
                if (eventListeners.isEmpty()) {
                    setEventListeners(nodeImpl, null);
                }
                LCount lCountLookup = LCount.lookup(str);
                if (z) {
                    lCountLookup.captures--;
                    lCountLookup.total--;
                    return;
                } else {
                    lCountLookup.bubbles--;
                    lCountLookup.total--;
                    return;
                }
            }
        }
    }

    public void removeNodeIterator(NodeIterator nodeIterator) {
        List<NodeIterator> list;
        if (nodeIterator == null || (list = this.iterators) == null) {
            return;
        }
        list.remove(nodeIterator);
    }

    public void removeRange(Range range) {
        List<Range> list;
        if (range == null || (list = this.ranges) == null) {
            return;
        }
        list.remove(range);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void removedAttrNode(AttrImpl attrImpl, NodeImpl nodeImpl, String str) {
        if (this.mutationEvents) {
            if (LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED).total > 0) {
                MutationEventImpl mutationEventImpl = new MutationEventImpl();
                mutationEventImpl.initMutationEvent(MutationEventImpl.DOM_ATTR_MODIFIED, true, false, attrImpl, attrImpl.getNodeValue(), null, str, (short) 3);
                dispatchEvent(nodeImpl, mutationEventImpl);
            }
            dispatchAggregateEvents(nodeImpl, null, null, (short) 0);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void removedNode(NodeImpl nodeImpl, boolean z) {
        if (!this.mutationEvents || z) {
            return;
        }
        dispatchAggregateEvents(nodeImpl, this.savedEnclosingAttr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.sun.org.apache.xerces.internal.dom.DocumentImpl] */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6, types: [com.sun.org.apache.xerces.internal.dom.NodeImpl] */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r13v6, types: [com.sun.org.apache.xerces.internal.dom.NodeImpl] */
    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void removingNode(NodeImpl nodeImpl, NodeImpl nodeImpl2, boolean z) {
        ?? r11;
        ?? r13;
        NodeImpl nodeImpl3;
        List<NodeIterator> list = this.iterators;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i != size; i++) {
                ((NodeIteratorImpl) this.iterators.get(i)).removeNode(nodeImpl2);
            }
        }
        List<Range> list2 = this.ranges;
        if (list2 != null) {
            int size2 = list2.size();
            for (int i2 = 0; i2 != size2; i2++) {
                ((RangeImpl) this.ranges.get(i2)).removeNode(nodeImpl2);
            }
        }
        if (this.mutationEvents) {
            if (!z) {
                saveEnclosingAttr(nodeImpl);
            }
            if (LCount.lookup(MutationEventImpl.DOM_NODE_REMOVED).total > 0) {
                MutationEventImpl mutationEventImpl = new MutationEventImpl();
                mutationEventImpl.initMutationEvent(MutationEventImpl.DOM_NODE_REMOVED, true, false, nodeImpl, null, null, null, (short) 0);
                dispatchEvent(nodeImpl2, mutationEventImpl);
            }
            if (LCount.lookup(MutationEventImpl.DOM_NODE_REMOVED_FROM_DOCUMENT).total > 0) {
                EnclosingAttr enclosingAttr = this.savedEnclosingAttr;
                if (enclosingAttr != null) {
                    nodeImpl3 = (NodeImpl) enclosingAttr.node.getOwnerElement();
                } else {
                    r11 = this;
                }
                if (r11 == 0) {
                    r11 = nodeImpl3;
                    return;
                }
                r11 = nodeImpl3;
                NodeImpl nodeImplParentNode = r11.parentNode();
                ?? r12 = r11;
                while (true) {
                    NodeImpl nodeImpl4 = nodeImplParentNode;
                    r13 = r12;
                    NodeImpl nodeImpl5 = nodeImpl4;
                    if (nodeImpl5 == null) {
                        break;
                    }
                    nodeImplParentNode = nodeImpl5.parentNode();
                    r12 = nodeImpl5;
                }
                if (r13.getNodeType() == 9) {
                    MutationEventImpl mutationEventImpl2 = new MutationEventImpl();
                    mutationEventImpl2.initMutationEvent(MutationEventImpl.DOM_NODE_REMOVED_FROM_DOCUMENT, false, false, null, null, null, null, (short) 0);
                    dispatchEventToSubtree(nodeImpl2, mutationEventImpl2);
                }
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void renamedAttrNode(Attr attr, Attr attr2) {
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void renamedElement(Element element, Element element2) {
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void replacedCharacterData(NodeImpl nodeImpl, String str, String str2) {
        modifiedCharacterData(nodeImpl, str, str2, false);
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void replacedNode(NodeImpl nodeImpl) {
        if (this.mutationEvents) {
            dispatchAggregateEvents(nodeImpl, this.savedEnclosingAttr);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void replacedText(NodeImpl nodeImpl) {
        List<Range> list = this.ranges;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i != size; i++) {
                ((RangeImpl) this.ranges.get(i)).receiveReplacedText(nodeImpl);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void replacingData(NodeImpl nodeImpl) {
        if (this.mutationEvents) {
            saveEnclosingAttr(nodeImpl);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void replacingNode(NodeImpl nodeImpl) {
        if (this.mutationEvents) {
            saveEnclosingAttr(nodeImpl);
        }
    }

    public void saveEnclosingAttr(NodeImpl nodeImpl) {
        this.savedEnclosingAttr = null;
        if (LCount.lookup(MutationEventImpl.DOM_ATTR_MODIFIED).total > 0) {
            while (nodeImpl != null) {
                short nodeType = nodeImpl.getNodeType();
                if (nodeType == 2) {
                    EnclosingAttr enclosingAttr = new EnclosingAttr();
                    AttrImpl attrImpl = (AttrImpl) nodeImpl;
                    enclosingAttr.node = attrImpl;
                    enclosingAttr.oldvalue = attrImpl.getNodeValue();
                    this.savedEnclosingAttr = enclosingAttr;
                    return;
                }
                if (nodeType == 5) {
                    nodeImpl = nodeImpl.parentNode();
                } else if (nodeType != 3) {
                    return;
                } else {
                    nodeImpl = nodeImpl.parentNode();
                }
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void setAttrNode(AttrImpl attrImpl, AttrImpl attrImpl2) {
        if (this.mutationEvents) {
            if (attrImpl2 == null) {
                dispatchAggregateEvents(attrImpl.ownerNode, attrImpl, null, (short) 2);
            } else {
                dispatchAggregateEvents(attrImpl.ownerNode, attrImpl, attrImpl2.getNodeValue(), (short) 1);
            }
        }
    }

    public void setEventListeners(NodeImpl nodeImpl, List<LEntry> list) {
        if (this.eventListeners == null) {
            this.eventListeners = new HashMap();
        }
        Map<NodeImpl, List<LEntry>> map = this.eventListeners;
        if (list != null) {
            map.put(nodeImpl, list);
            this.mutationEvents = true;
        } else {
            map.remove(nodeImpl);
            if (this.eventListeners.isEmpty()) {
                this.mutationEvents = false;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl
    public void setMutationEvents(boolean z) {
        this.mutationEvents = z;
    }

    public void splitData(Node node, Node node2, int i) {
        List<Range> list = this.ranges;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 != size; i2++) {
                ((RangeImpl) this.ranges.get(i2)).receiveSplitData(node, node2, i);
            }
        }
    }

    public DocumentImpl(boolean z) {
        super(z);
        this.mutationEvents = false;
    }

    public DocumentImpl(DocumentType documentType) {
        super(documentType);
        this.mutationEvents = false;
    }

    public DocumentImpl(DocumentType documentType, boolean z) {
        super(documentType, z);
        this.mutationEvents = false;
    }

    public TreeWalker createTreeWalker(Node node, short s, NodeFilter nodeFilter) {
        return createTreeWalker(node, s, nodeFilter, true);
    }

    public NodeIterator createNodeIterator(Node node, short s, NodeFilter nodeFilter) {
        return createNodeIterator(node, s, nodeFilter, true);
    }

    public void dispatchAggregateEvents(NodeImpl nodeImpl, EnclosingAttr enclosingAttr) {
        if (enclosingAttr != null) {
            dispatchAggregateEvents(nodeImpl, enclosingAttr.node, enclosingAttr.oldvalue, (short) 1);
        } else {
            dispatchAggregateEvents(nodeImpl, null, null, (short) 0);
        }
    }
}
