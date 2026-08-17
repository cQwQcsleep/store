package com.android.tools.r8.experimental.graphinfo;

import com.android.tools.r8.references.FieldReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FieldGraphNode extends GraphNode {
    static final /* synthetic */ boolean d = true;
    private final FieldReference c;

    public FieldGraphNode(boolean z, FieldReference fieldReference) {
        super(z);
        if (d || fieldReference != null) {
            this.c = fieldReference;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof FieldGraphNode) && ((FieldGraphNode) obj).c.equals(this.c);
        }
        return true;
    }

    public FieldReference getReference() {
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
