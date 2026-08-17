package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ji, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1852ji implements Iterator {
    public int b = -1;
    public int c;
    public int d;
    public C3092yA e;
    public int f;
    public final /* synthetic */ C1937ki g;

    public C1852ji(C1937ki c1937ki) {
        this.g = c1937ki;
        int i = c1937ki.b;
        int length = c1937ki.a.length();
        if (length < 0) {
            w01.a(AbstractC1784iv.a(length, "Cannot coerce value to an empty range: maximum ", " is less than minimum 0."));
            throw null;
        }
        if (i < 0) {
            i = 0;
        } else if (i > length) {
            i = length;
        }
        this.c = i;
        this.d = i;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001a  */
    /* JADX WARN: Code duplicated, block: B:12:0x0022  */
    /* JADX WARN: Code duplicated, block: B:13:0x0036  */
    /* JADX WARN: Code duplicated, block: B:15:0x004a  */
    /* JADX WARN: Code duplicated, block: B:16:0x005e  */
    /* JADX WARN: Code duplicated, block: B:18:0x0074  */
    /* JADX WARN: Code duplicated, block: B:19:0x0077  */
    /* JADX WARN: Code duplicated, block: B:22:0x0086  */
    public final void a() {
        C1491fW c1491fW;
        int iIntValue;
        int i;
        C3092yA c3092yA;
        int i2 = this.d;
        if (i2 < 0) {
            this.b = 0;
            this.e = null;
            return;
        }
        C1937ki c1937ki = this.g;
        int i3 = c1937ki.c;
        if (i3 > 0) {
            int i4 = this.f + 1;
            this.f = i4;
            if (i4 >= i3) {
                this.e = new C3092yA(this.c, AbstractC1679hg0.a(this.g.a));
                this.d = -1;
            } else if (i2 > c1937ki.a.length()) {
                this.e = new C3092yA(this.c, AbstractC1679hg0.a(this.g.a));
                this.d = -1;
            } else {
                C1937ki c1937ki2 = this.g;
                c1491fW = (C1491fW) c1937ki2.d.a(c1937ki2.a, Integer.valueOf(this.d));
                if (c1491fW == null) {
                    this.e = new C3092yA(this.c, AbstractC1679hg0.a(this.g.a));
                    this.d = -1;
                } else {
                    iIntValue = ((Number) c1491fW.b).intValue();
                    int iIntValue2 = ((Number) c1491fW.c).intValue();
                    i = this.c;
                    if (iIntValue <= Integer.MIN_VALUE) {
                        c3092yA = C3092yA.e;
                    } else {
                        c3092yA = new C3092yA(i, iIntValue - 1);
                    }
                    this.e = c3092yA;
                    int i5 = iIntValue + iIntValue2;
                    this.c = i5;
                    this.d = i5 + (iIntValue2 == 0 ? 1 : 0);
                }
            }
        } else if (i2 > c1937ki.a.length()) {
            this.e = new C3092yA(this.c, AbstractC1679hg0.a(this.g.a));
            this.d = -1;
        } else {
            C1937ki c1937ki3 = this.g;
            c1491fW = (C1491fW) c1937ki3.d.a(c1937ki3.a, Integer.valueOf(this.d));
            if (c1491fW == null) {
                this.e = new C3092yA(this.c, AbstractC1679hg0.a(this.g.a));
                this.d = -1;
            } else {
                iIntValue = ((Number) c1491fW.b).intValue();
                int iIntValue3 = ((Number) c1491fW.c).intValue();
                i = this.c;
                if (iIntValue <= Integer.MIN_VALUE) {
                    c3092yA = C3092yA.e;
                } else {
                    c3092yA = new C3092yA(i, iIntValue - 1);
                }
                this.e = c3092yA;
                int i6 = iIntValue + iIntValue3;
                this.c = i6;
                this.d = i6 + (iIntValue3 == 0 ? 1 : 0);
            }
        }
        this.b = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.b == -1) {
            a();
        }
        return this.b == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.b == -1) {
            a();
        }
        if (this.b == 0) {
            z0e.a();
            return null;
        }
        C3092yA c3092yA = this.e;
        KB.a((Object) c3092yA, "null cannot be cast to non-null type kotlin.ranges.IntRange");
        this.e = null;
        this.b = -1;
        return c3092yA;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
