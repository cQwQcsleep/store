package com.sun.org.apache.xpath.internal.axes;

import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ContextNodeList {
    Object clone() throws CloneNotSupportedException;

    NodeIterator cloneWithReset() throws CloneNotSupportedException;

    Node getCurrentNode();

    int getCurrentPos();

    int getLast();

    boolean isFresh();

    void reset();

    void runTo(int i);

    void setCurrentPos(int i);

    void setLast(int i);

    void setShouldCacheNodes(boolean z);

    int size();
}
