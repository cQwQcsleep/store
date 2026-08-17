package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1860jm implements Iterator {
    public static final /* synthetic */ boolean d = true;
    public H5 b;
    public final /* synthetic */ C2031lm c;

    public C1860jm(C2031lm c2031lm, H5 h5) {
        this.c = c2031lm;
        this.b = h5;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            return null;
        }
        H5 h5 = this.b;
        if (h5.o() == 0) {
            this.b = null;
            return h5;
        }
        C2031lm c2031lm = this.c;
        H5 h6 = this.b;
        if (!C2031lm.f && c2031lm.e) {
            x1f.a();
            return null;
        }
        H5 h7 = c2031lm.b[h6.o()];
        this.b = h7;
        if (d || h7 != h5) {
            return h5;
        }
        x1f.a();
        return null;
    }
}
