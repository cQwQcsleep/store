package com.intellij.util.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CachingSemiGraph<Node> implements InboundSemiGraph<Node> {
    private final Map<Node, List<Node>> myIn;
    private final Set<Node> myNodes;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3) ? 2 : 3];
        if (i == 2 || i == 3) {
            objArr[0] = "com/intellij/util/graph/CachingSemiGraph";
        } else {
            objArr[0] = "original";
        }
        if (i == 2) {
            objArr[1] = "getNodes";
        } else if (i != 3) {
            objArr[1] = "com/intellij/util/graph/CachingSemiGraph";
        } else {
            objArr[1] = "getIn";
        }
        if (i == 1) {
            objArr[2] = "<init>";
        } else if (i != 2 && i != 3) {
            objArr[2] = "cache";
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CachingSemiGraph(InboundSemiGraph<Node> inboundSemiGraph) {
        if (inboundSemiGraph == 0) {
            $$$reportNull$$$0(1);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(inboundSemiGraph.getNodes());
        this.myNodes = linkedHashSet;
        this.myIn = new HashMap();
        for (Object obj : linkedHashSet) {
            Iterator in = inboundSemiGraph.getIn(obj);
            if (in.hasNext()) {
                ArrayList arrayList = new ArrayList();
                while (in.hasNext()) {
                    arrayList.add(in.next());
                }
                this.myIn.put((Node) obj, arrayList);
            }
        }
    }

    public static <T> InboundSemiGraph<T> cache(InboundSemiGraph<T> inboundSemiGraph) {
        if (inboundSemiGraph == null) {
            $$$reportNull$$$0(0);
        }
        return new CachingSemiGraph(inboundSemiGraph);
    }

    @Override // com.intellij.util.graph.InboundSemiGraph
    public Iterator<Node> getIn(Node node) {
        List<Node> list = this.myIn.get(node);
        Iterator<Node> it = list != null ? list.iterator() : Collections.emptyIterator();
        if (it == null) {
            $$$reportNull$$$0(3);
        }
        return it;
    }

    @Override // com.intellij.util.graph.InboundSemiGraph
    public Collection<Node> getNodes() {
        Set<Node> set = this.myNodes;
        if (set == null) {
            $$$reportNull$$$0(2);
        }
        return set;
    }
}
