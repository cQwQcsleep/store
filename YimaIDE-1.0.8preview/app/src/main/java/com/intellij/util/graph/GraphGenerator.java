package com.intellij.util.graph;

import com.intellij.openapi.util.Pair;
import com.intellij.util.graph.GraphGenerator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class GraphGenerator<Node> implements Graph<Node> {
    private final InboundSemiGraph<Node> myGraph;
    private final Map<Node, List<Node>> myOuts;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 4) ? 2 : 3];
        if (i == 2 || i == 3 || i == 4) {
            objArr[0] = "com/intellij/util/graph/GraphGenerator";
        } else {
            objArr[0] = "graph";
        }
        if (i == 2) {
            objArr[1] = "getNodes";
        } else if (i == 3) {
            objArr[1] = "getIn";
        } else if (i != 4) {
            objArr[1] = "com/intellij/util/graph/GraphGenerator";
        } else {
            objArr[1] = "getOut";
        }
        if (i == 1) {
            objArr[2] = "<init>";
        } else if (i != 2 && i != 3 && i != 4) {
            objArr[2] = "generate";
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private GraphGenerator(InboundSemiGraph<Node> inboundSemiGraph) {
        if (inboundSemiGraph == null) {
            $$$reportNull$$$0(1);
        }
        this.myGraph = inboundSemiGraph;
        this.myOuts = new HashMap();
        buildOuts();
    }

    public static /* synthetic */ List a(Object obj) {
        return new ArrayList();
    }

    private void buildOuts() {
        HashSet hashSet = new HashSet();
        for (Node node : this.myGraph.getNodes()) {
            Iterator<Node> in = this.myGraph.getIn(node);
            while (in.hasNext()) {
                Node next = in.next();
                if (hashSet.add(new Pair(next, node))) {
                    this.myOuts.computeIfAbsent(next, new Function() { // from class: i06
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return GraphGenerator.a(obj);
                        }
                    }).add(node);
                }
            }
        }
    }

    public static <T> Graph<T> generate(InboundSemiGraph<T> inboundSemiGraph) {
        if (inboundSemiGraph == null) {
            $$$reportNull$$$0(0);
        }
        return new GraphGenerator(inboundSemiGraph);
    }

    @Override // com.intellij.util.graph.InboundSemiGraph
    public Iterator<Node> getIn(Node node) {
        Iterator<Node> in = this.myGraph.getIn(node);
        if (in == null) {
            $$$reportNull$$$0(3);
        }
        return in;
    }

    @Override // com.intellij.util.graph.InboundSemiGraph
    public Collection<Node> getNodes() {
        Collection<Node> nodes = this.myGraph.getNodes();
        if (nodes == null) {
            $$$reportNull$$$0(2);
        }
        return nodes;
    }

    @Override // com.intellij.util.graph.OutboundSemiGraph
    public Iterator<Node> getOut(Node node) {
        List<Node> list = this.myOuts.get(node);
        Iterator<Node> it = list != null ? list.iterator() : Collections.emptyIterator();
        if (it == null) {
            $$$reportNull$$$0(4);
        }
        return it;
    }
}
