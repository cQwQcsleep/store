package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.GraphConsumer;
import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.internal.C2807us;
import com.android.tools.r8.shaking.C3457t;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3457t implements GraphConsumer {
    public final GraphConsumer a;
    public final HashMap b = new HashMap();

    public C3457t(GraphConsumer graphConsumer) {
        this.a = graphConsumer;
    }

    public static /* synthetic */ Map b(GraphNode graphNode) {
        return new HashMap();
    }

    public static /* synthetic */ Set c(GraphNode graphNode) {
        return new HashSet();
    }

    public Map<GraphNode, Set<C2807us>> a(GraphNode graphNode) {
        return (Map) this.b.get(graphNode);
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphConsumer
    public final void acceptEdge(GraphNode graphNode, GraphNode graphNode2, C2807us c2807us) {
        ((Set) ((Map) this.b.computeIfAbsent(graphNode2, new Function() { // from class: kbi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3457t.b((GraphNode) obj);
            }
        })).computeIfAbsent(graphNode, new Function() { // from class: mbi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C3457t.c((GraphNode) obj);
            }
        })).add(c2807us);
        GraphConsumer graphConsumer = this.a;
        if (graphConsumer != null) {
            graphConsumer.acceptEdge(graphNode, graphNode2, c2807us);
        }
    }

    public Set<GraphNode> a() {
        return this.b.keySet();
    }
}
