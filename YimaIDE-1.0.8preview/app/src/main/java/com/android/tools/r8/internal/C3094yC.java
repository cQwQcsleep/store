package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3094yC extends AbstractC0728Oq {
    public final /* synthetic */ Iterable b;
    public final /* synthetic */ int c = 1;

    public C3094yC(Iterable iterable) {
        this.b = iterable;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Iterable iterable = this.b;
        if (iterable instanceof List) {
            List list = (List) iterable;
            return list.subList(Math.min(list.size(), this.c), list.size()).iterator();
        }
        Iterator it = iterable.iterator();
        int i = this.c;
        it.getClass();
        if (!(i >= 0)) {
            w01.a("numberToAdvance must be nonnegative");
            return null;
        }
        for (int i2 = 0; i2 < i && it.hasNext(); i2++) {
            it.next();
        }
        return new C3010xC(it);
    }

    @Override // java.lang.Iterable
    public final Spliterator spliterator() {
        Iterable iterable = this.b;
        if (!(iterable instanceof List)) {
            return De0.a(iterable).skip(this.c).spliterator();
        }
        List list = (List) iterable;
        return list.subList(Math.min(list.size(), this.c), list.size()).spliterator();
    }
}
