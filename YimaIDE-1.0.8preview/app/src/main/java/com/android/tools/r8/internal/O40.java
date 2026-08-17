package com.android.tools.r8.internal;

import java.util.Map;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class O40 extends AbstractC2895vu {
    public static final O40 l = new O40(null, null, AbstractC0706Nu.e, 0, 0);
    public final transient C0784Qu[] f;
    public final transient C0784Qu[] g;
    public final transient Map.Entry[] h;
    public final transient int i;
    public final transient int j;
    public transient N40 k;

    public O40(C0784Qu[] c0784QuArr, C0784Qu[] c0784QuArr2, Map.Entry[] entryArr, int i, int i2) {
        this.f = c0784QuArr;
        this.g = c0784QuArr2;
        this.h = entryArr;
        this.i = i;
        this.j = i2;
    }

    @Override // java.util.Map
    public final void forEach(BiConsumer biConsumer) {
        biConsumer.getClass();
        for (Map.Entry entry : this.h) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Object get(Object obj) {
        C0784Qu[] c0784QuArr = this.f;
        int i = this.i;
        T40 t40 = T40.i;
        if (obj == null || c0784QuArr == null) {
            return null;
        }
        for (C0784Qu c0784QuB = c0784QuArr[i & AbstractC1189bt.a(obj.hashCode())]; c0784QuB != null; c0784QuB = c0784QuB.b()) {
            if (obj.equals(c0784QuB.b)) {
                return c0784QuB.c;
            }
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final int hashCode() {
        return this.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv i() {
        if (!isEmpty()) {
            return new C0810Ru(this, this.h);
        }
        int i = AbstractC2554rv.c;
        return W40.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv j() {
        return new C0862Tu(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final boolean m() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2895vu
    /* JADX INFO: renamed from: s */
    public final AbstractC2895vu f() {
        if (isEmpty()) {
            return l;
        }
        N40 n40 = this.k;
        if (n40 != null) {
            return n40;
        }
        N40 n41 = new N40(this);
        this.k = n41;
        return n41;
    }

    @Override // java.util.Map
    public final int size() {
        return this.h.length;
    }
}
