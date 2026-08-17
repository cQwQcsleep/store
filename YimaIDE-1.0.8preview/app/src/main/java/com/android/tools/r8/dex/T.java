package com.android.tools.r8.dex;

import com.android.tools.r8.dex.code.AbstractC0138z1;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T {
    public static final /* synthetic */ boolean d = true;
    public AbstractC0138z1 a;
    public AbstractC0138z1 b;
    public final boolean c;

    public T(AbstractC0138z1 abstractC0138z1, AbstractC0138z1 abstractC0138z2, boolean z) {
        boolean z2 = d;
        if (!z2 && abstractC0138z1 == null) {
            x1f.a();
            throw null;
        }
        if (!z2 && abstractC0138z2 == null) {
            x1f.a();
            throw null;
        }
        this.a = abstractC0138z1;
        this.b = abstractC0138z2;
        this.c = z;
    }

    public final int a() {
        return this.a.q();
    }

    public final int b() {
        boolean z = this.c;
        AbstractC0138z1 abstractC0138z1 = this.b;
        if (!z) {
            return abstractC0138z1.q() - this.a.q();
        }
        return (this.b.t() + abstractC0138z1.q()) - this.a.q();
    }
}
