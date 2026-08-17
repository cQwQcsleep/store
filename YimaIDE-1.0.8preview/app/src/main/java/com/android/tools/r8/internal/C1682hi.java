package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1682hi extends KR {
    public static final /* synthetic */ boolean c = true;
    public final int a;
    public final int b;

    public C1682hi(int i, int i2) {
        if (!c && (i & i2) != 0) {
            x1f.a();
            throw null;
        }
        this.a = i;
        this.b = i2;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean F() {
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final boolean a(int i) {
        return (this.a & (~i)) == 0 && (this.b & i) == 0;
    }

    @Override // com.android.tools.r8.internal.B1
    public final B1 b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final long c() {
        return -2147483648L;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == C1682hi.class) {
            C1682hi c1682hi = (C1682hi) obj;
            if (this.a == c1682hi.a && this.b == c1682hi.b) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.B1
    public final int hashCode() {
        int i = this.a;
        int i2 = (((i + 31) * 31) + this.b) * 31;
        if (c || i2 == Objects.hash(Integer.valueOf(i), Integer.valueOf(this.b))) {
            return i2;
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.B1
    public final C1682hi l() {
        return this;
    }

    @Override // com.android.tools.r8.internal.B1
    public final String toString() {
        return "DefiniteBitsNumberValue(set: " + Integer.toBinaryString(this.a) + "; unset: " + Integer.toBinaryString(this.b) + ")";
    }

    @Override // com.android.tools.r8.internal.B1
    public final int v() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.B1
    public final int w() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final boolean a(InterfaceC2617sg interfaceC2617sg) {
        return true;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final AbstractC2173nV a(int[] iArr) {
        return AbstractC2173nV.c;
    }
}
