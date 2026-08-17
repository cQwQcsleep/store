package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3187zK implements Iterator {
    public AK b;
    public AK c = null;
    public int d;
    public final /* synthetic */ BK e;

    public AbstractC3187zK(BK bk) {
        this.e = bk;
        this.b = bk.g.e;
        this.d = bk.f;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b != this.e.g;
    }

    @Override // java.util.Iterator
    public final void remove() {
        AK ak = this.c;
        if (ak == null) {
            g33.a();
            return;
        }
        this.e.b(ak, true);
        this.c = null;
        this.d = this.e.f;
    }
}
