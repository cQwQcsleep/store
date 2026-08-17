package com.sun.org.apache.xpath.internal.jaxp;

import java.util.Iterator;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathNodes;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPathNodesImpl implements XPathNodes {
    Class<Node> elementType;
    NodeList nodeList;

    public class NodeSetIterator<E> implements Iterator<E> {
        int currentIndex;
        Class<E> elementType;

        public NodeSetIterator(Class<E> cls) {
            this.elementType = cls;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            NodeList nodeList = XPathNodesImpl.this.nodeList;
            return nodeList != null && this.currentIndex < nodeList.getLength();
        }

        @Override // java.util.Iterator
        public E next() {
            NodeList nodeList = XPathNodesImpl.this.nodeList;
            if (nodeList == null || nodeList.getLength() <= 0) {
                return null;
            }
            Class<E> cls = this.elementType;
            NodeList nodeList2 = XPathNodesImpl.this.nodeList;
            int i = this.currentIndex;
            this.currentIndex = i + 1;
            return cls.cast(nodeList2.item(i));
        }
    }

    public XPathNodesImpl(NodeList nodeList, Class<Node> cls) {
        this.nodeList = nodeList;
        this.elementType = cls;
    }

    @Override // javax.xml.xpath.XPathNodes
    public Node get(int i) throws XPathException {
        if (i < 0 || i >= size()) {
            ib9.a("Index ", i, " is out of bounds");
            return null;
        }
        NodeList nodeList = this.nodeList;
        if (nodeList != null) {
            return nodeList.item(i);
        }
        return null;
    }

    @Override // javax.xml.xpath.XPathNodes, java.lang.Iterable
    public Iterator<Node> iterator() {
        return new NodeSetIterator(this.elementType);
    }

    @Override // javax.xml.xpath.XPathNodes
    public int size() {
        NodeList nodeList = this.nodeList;
        if (nodeList != null) {
            return nodeList.getLength();
        }
        return 0;
    }
}
