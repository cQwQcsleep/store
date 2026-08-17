package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EC extends Ck0 {
    public int b = 0;
    public final /* synthetic */ Iterator[] c;

    public EC(Iterator[] itArr) {
        this.c = itArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b < this.c.length;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        Iterator it = this.c[this.b];
        Objects.requireNonNull(it);
        Iterator it2 = it;
        Iterator[] itArr = this.c;
        int i = this.b;
        itArr[i] = null;
        this.b = i + 1;
        return it2;
    }
}
