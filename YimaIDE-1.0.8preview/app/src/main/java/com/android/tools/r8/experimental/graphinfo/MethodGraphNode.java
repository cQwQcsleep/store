package com.android.tools.r8.experimental.graphinfo;

import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MethodGraphNode extends GraphNode {
    static final /* synthetic */ boolean d = true;
    private final MethodReference c;

    public MethodGraphNode(boolean z, MethodReference methodReference) {
        super(z);
        if (d || methodReference != null) {
            this.c = methodReference;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof MethodGraphNode) && ((MethodGraphNode) obj).c.equals(this.c);
        }
        return true;
    }

    public MethodReference getReference() {
        return this.c;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public int hashCode() {
        return this.c.hashCode();
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public String toString() {
        return this.c.toString();
    }
}
