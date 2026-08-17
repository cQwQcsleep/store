package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ql, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2457ql {
    public static final /* synthetic */ boolean d = true;
    public final AbstractC0890Uw a;
    public int b = -1;
    public int c = -1;

    public AbstractC2457ql(AbstractC0890Uw abstractC0890Uw) {
        if (d || abstractC0890Uw != null) {
            this.a = abstractC0890Uw;
        } else {
            x1f.a();
            throw null;
        }
    }

    public abstract int a(C2884vl c2884vl);

    public final AbstractC0890Uw a() {
        return this.a;
    }

    public abstract void a(C2884vl c2884vl, ArrayList arrayList);

    public abstract boolean a(AbstractC2457ql abstractC2457ql, C2884vl c2884vl);

    public final int b() {
        if (d || this.b >= 0) {
            return this.b;
        }
        x01.a(this);
        return 0;
    }

    public abstract int c();

    public abstract int d();

    public abstract int e();
}
