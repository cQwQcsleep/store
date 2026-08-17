package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.GraphNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class w4 {
    public static final /* synthetic */ boolean c = true;
    public final GraphNode a;
    public final w4 b;

    public w4(GraphNode graphNode, w4 w4Var) {
        if (!c && graphNode == null) {
            x1f.a();
            throw null;
        }
        this.a = graphNode;
        this.b = w4Var;
    }
}
