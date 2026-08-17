package com.android.tools.r8.shaking;

import com.android.tools.r8.experimental.graphinfo.GraphNode;
import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A1 extends GraphNode {
    public final Origin c;

    public A1(Origin origin) {
        super(false);
        this.c = origin;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof A1) {
            return ((A1) obj).c.equals(this.c);
        }
        return false;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public final int hashCode() {
        return this.c.hashCode();
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public final String toString() {
        return this.c.toString();
    }
}
