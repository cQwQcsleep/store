package com.android.tools.r8.experimental.graphinfo;

import com.android.tools.r8.references.ClassReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ClassGraphNode extends GraphNode {
    static final /* synthetic */ boolean d = true;
    private final ClassReference c;

    public ClassGraphNode(boolean z, ClassReference classReference) {
        super(z);
        if (d || classReference != null) {
            this.c = classReference;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ClassGraphNode) && ((ClassGraphNode) obj).c.equals(this.c);
        }
        return true;
    }

    public ClassReference getReference() {
        return this.c;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public int hashCode() {
        return this.c.hashCode();
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public String toString() {
        return this.c.getDescriptor();
    }
}
