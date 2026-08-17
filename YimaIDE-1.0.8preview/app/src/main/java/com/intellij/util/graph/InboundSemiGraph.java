package com.intellij.util.graph;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface InboundSemiGraph<Node> {
    Iterator<Node> getIn(Node node);

    Collection<Node> getNodes();
}
