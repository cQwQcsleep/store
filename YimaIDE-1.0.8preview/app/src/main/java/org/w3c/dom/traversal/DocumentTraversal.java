package org.w3c.dom.traversal;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocumentTraversal {
    NodeIterator createNodeIterator(Node node, int i, NodeFilter nodeFilter, boolean z) throws DOMException;

    TreeWalker createTreeWalker(Node node, int i, NodeFilter nodeFilter, boolean z) throws DOMException;
}
