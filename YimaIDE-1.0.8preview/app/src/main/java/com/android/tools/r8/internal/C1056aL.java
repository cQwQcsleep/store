package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.aL, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1056aL implements Iterator<ZK>, ZK {
    public static final /* synthetic */ boolean g = true;
    public final E7 b;
    public int c = 0;
    public int d = 0;
    public int e = -1;
    public int f = -1;

    public C1056aL(E7 e7) {
        this.b = e7;
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int a(SK sk) {
        return sk.i + this.e;
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int b() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int d() {
        return this.d - this.c;
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int e() {
        return k();
    }

    @Override // com.android.tools.r8.internal.ZK
    public final long f() {
        if (!g && !h()) {
            x1f.a();
            return 0L;
        }
        this.c += 8;
        E7 e7 = this.b;
        boolean z = W7.a;
        return (((long) (e7.a() & 255)) << 8) | (((long) (e7.a() & 255)) << 56) | (((long) (e7.a() & 255)) << 48) | (((long) (e7.a() & 255)) << 40) | (((long) (e7.a() & 255)) << 32) | (((long) (e7.a() & 255)) << 24) | (((long) (e7.a() & 255)) << 16) | ((long) (e7.a() & 255));
    }

    @Override // com.android.tools.r8.internal.ZK
    public final boolean h() {
        return this.c < this.d;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (h()) {
            int i = this.d;
            int i2 = this.c;
            int i3 = i - i2;
            this.c = i2 + i3;
            E7 e7 = this.b;
            int i4 = e7.d;
            int i5 = i4 + i3;
            int i6 = e7.b;
            if (i5 > i6) {
                i3 = i6 - i4;
            }
            e7.d = i4 + i3;
        }
        return this.b.hasNext();
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int j() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int k() {
        if (g || h()) {
            return t();
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int l() {
        return k();
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int o() {
        this.c++;
        byte bA = this.b.a();
        boolean z = W7.a;
        return bA & 255;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public ZK next() {
        if (h()) {
            int i = this.d;
            int i2 = this.c;
            int i3 = i - i2;
            this.c = i2 + i3;
            E7 e7 = this.b;
            int i4 = e7.d;
            int i5 = i4 + i3;
            int i6 = e7.b;
            if (i5 > i6) {
                i3 = i6 - i4;
            }
            e7.d = i4 + i3;
        }
        this.e++;
        this.c++;
        byte bA = this.b.a();
        boolean z = W7.a;
        int i7 = bA & 255;
        this.f = i7;
        boolean zF = gL.f(i7);
        int i8 = this.c;
        if (zF) {
            this.d = i8;
            return this;
        }
        this.c = i8 + 1;
        int iA = this.b.a() & 255;
        if (iA == 0) {
            iA = t();
        }
        this.d = this.c + iA;
        return this;
    }

    public final int t() {
        this.c += 4;
        E7 e7 = this.b;
        boolean z = W7.a;
        return (e7.a() & 255) | ((e7.a() & 255) << 24) | ((e7.a() & 255) << 16) | ((e7.a() & 255) << 8);
    }

    @Override // com.android.tools.r8.internal.ZK
    public final int a() {
        return k();
    }

    @Override // com.android.tools.r8.internal.ZK
    public void a(YK yk) {
        yk.a(this);
    }
}
