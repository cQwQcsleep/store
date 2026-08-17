package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ul, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2798ul implements Comparable {
    public final C2490r8 b;
    public int c;
    public int d;

    public C2798ul(C2490r8 c2490r8, int i, int i2) {
        this.b = c2490r8;
        this.c = i;
        this.d = i2;
    }

    public final int a(C2798ul c2798ul) {
        return Integer.compare(this.c, c2798ul.c);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Integer.compare(this.c, ((C2798ul) obj).c);
    }
}
