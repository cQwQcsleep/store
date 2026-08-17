package com.android.tools.r8.graph;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class E extends AbstractC0259n1 {
    public static final /* synthetic */ boolean c = true;
    public volatile int b = -1;

    public abstract boolean c(Object obj);

    public final boolean equals(Object obj) {
        return this == obj || c(obj);
    }

    public final int hashCode() {
        int iN0 = this.b;
        if (iN0 == -1) {
            iN0 = n0();
            if (iN0 == -1) {
                iN0 = 0;
            }
            this.b = iN0;
        }
        if (!c) {
            j(iN0);
        }
        return iN0;
    }

    public final void j(int i) {
        int iN0 = n0();
        if (iN0 == -1) {
            iN0 = 0;
        }
        if (c || i == iN0) {
            return;
        }
        throw new AssertionError("Hash code for " + this + " has changed from " + this.b + " to " + iN0);
    }

    public abstract int n0();
}
