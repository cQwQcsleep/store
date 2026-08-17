package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IC extends Ck0 {
    public boolean b;
    public final /* synthetic */ Object c;

    public IC(Object obj) {
        this.c = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return !this.b;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.b) {
            z0e.a();
            return null;
        }
        this.b = true;
        return this.c;
    }
}
