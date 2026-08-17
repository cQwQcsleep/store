package com.intellij.util.graph;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface OutboundSemiGraph<Node> {
    Collection<Node> getNodes();

    Iterator<Node> getOut(Node node);
}
