package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Mr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0677Mr implements Comparable {
    public final int b;
    public final Mm0 c;
    public final boolean d;
    public final boolean e = false;

    public C0677Mr(int i, Mm0 mm0, boolean z) {
        this.b = i;
        this.c = mm0;
        this.d = z;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.b - ((C0677Mr) obj).b;
    }
}
