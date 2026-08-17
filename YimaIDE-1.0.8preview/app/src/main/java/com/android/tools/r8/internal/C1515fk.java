package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1515fk extends AbstractC2027lk implements InterfaceC1046aB {
    public static final C1344dk e = new C1344dk();
    public final C0798Ri b;
    public final String c;
    public final C1260ck d;

    public C1515fk(C1260ck c1260ck, Integer num) {
        String str = "UNKNOWN_ENUM_VALUE_" + c1260ck.b.k() + "_" + num;
        C0772Qi c0772QiD = C0798Ri.j.d();
        c0772QiD.f |= 1;
        c0772QiD.g = str;
        c0772QiD.p();
        int iIntValue = num.intValue();
        c0772QiD.f |= 2;
        c0772QiD.h = iIntValue;
        c0772QiD.p();
        C0798Ri c0798RiI = c0772QiD.i();
        if (!c0798RiI.a()) {
            throw H0.c(c0798RiI);
        }
        this.b = c0798RiI;
        this.d = c1260ck;
        this.c = c1260ck.c + '.' + c0798RiI.k();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1046aB
    public final int a() {
        return this.b.g;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final C1941kk b() {
        return this.d.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String c() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final String d() {
        return this.b.k();
    }

    @Override // com.android.tools.r8.internal.AbstractC2027lk
    public final J0 e() {
        return this.b;
    }

    public final String toString() {
        return this.b.k();
    }

    public C1515fk(C0798Ri c0798Ri, C1941kk c1941kk, C1260ck c1260ck) throws C1091ak {
        this.b = c0798Ri;
        this.d = c1260ck;
        this.c = c1260ck.c + '.' + c0798Ri.k();
        c1941kk.h.a(this);
    }
}
