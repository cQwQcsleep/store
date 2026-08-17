package com.android.tools.r8.internal;

import java.util.Arrays;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2469qv {
    public Object[] a;
    public int b;

    public AbstractC2469qv(AbstractC2469qv abstractC2469qv) {
        Object[] objArr = abstractC2469qv.a;
        this.a = Arrays.copyOf(objArr, objArr.length);
        this.b = abstractC2469qv.b;
    }

    public abstract AbstractC2469qv a(Object obj);

    public abstract AbstractC2554rv a();

    public abstract AbstractC2469qv b();

    public final void b(Object obj) {
        int i = this.b + 1;
        Object[] objArr = this.a;
        if (i > objArr.length) {
            this.a = Arrays.copyOf(this.a, AbstractC2981wu.a(objArr.length, i));
        }
        Object[] objArr2 = this.a;
        int i2 = this.b;
        this.b = i2 + 1;
        objArr2[i2] = obj;
    }

    public AbstractC2469qv c() {
        return this;
    }

    public AbstractC2469qv(int i) {
        this.a = new Object[i];
        this.b = 0;
    }
}
