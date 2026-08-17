package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yS, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3110yS extends KR {
    public static final /* synthetic */ boolean c = true;
    public final long a;
    public final long b;

    public C3110yS(long j, long j2) {
        if (!c && j2 <= j) {
            x1f.a();
            throw null;
        }
        this.a = j;
        this.b = j2;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean F() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final boolean a(InterfaceC2617sg interfaceC2617sg) {
        B1 b1 = (B1) interfaceC2617sg;
        b1.getClass();
        if (b1 instanceof C1682hi) {
            return true;
        }
        if (b1 instanceof C2525rc0) {
            return a((int) interfaceC2617sg.e().b);
        }
        if (b1 instanceof C3110yS) {
            C3110yS c3110ySD = interfaceC2617sg.d();
            return this.a <= c3110ySD.b && this.b >= c3110ySD.a;
        }
        if (c || (b1 instanceof AbstractC3195zS)) {
            interfaceC2617sg.i().a(this);
            return true;
        }
        x1f.a();
        return false;
    }

    @Override // com.android.tools.r8.internal.B1
    public final B1 b(C0333y c0333y, com.android.tools.r8.graph.I2 i2, AbstractC3148ys abstractC3148ys, AbstractC3148ys abstractC3148ys2) {
        return this;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final long c() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.B1, com.android.tools.r8.internal.InterfaceC2617sg
    public final C3110yS d() {
        return this;
    }

    @Override // com.android.tools.r8.internal.B1
    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == C3110yS.class) {
            C3110yS c3110yS = (C3110yS) obj;
            if (this.a == c3110yS.a && this.b == c3110yS.b) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.B1
    public final int hashCode() {
        int iHashCode = (Long.hashCode(this.b) + ((Long.hashCode(this.a) + 31) * 31)) * 31;
        if (c || iHashCode == Objects.hash(Long.valueOf(this.a), Long.valueOf(this.b))) {
            return iHashCode;
        }
        x1f.a();
        return 0;
    }

    @Override // com.android.tools.r8.internal.B1
    public final String toString() {
        return "NumberFromIntervalValue([" + this.a + "; " + this.b + "])";
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final boolean a(int i) {
        long j = i;
        return this.a <= j && j <= this.b;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2617sg
    public final AbstractC2173nV a(int[] iArr) {
        return AbstractC2173nV.c;
    }
}
