package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2188nf extends AbstractC2530rf {
    public static final /* synthetic */ boolean e = true;
    public B1 d;

    public C2188nf(B1 b1, Set set) {
        super(set);
        this.d = b1;
        boolean z = e;
        if (!z && r()) {
            x01.a("Must use BottomPrimitiveTypeParameterState instead");
            throw null;
        }
        if (z || !this.d.isUnknown()) {
            return;
        }
        x01.a("Must use UnknownParameterState instead");
        throw null;
    }

    public static WR a(B1 b1, Set set) {
        return b1.isUnknown() ? Bk0.b : new C2188nf(b1, set);
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final C2188nf e() {
        return this;
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final Cl0 l() {
        return new C2188nf(this.d, n());
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final AbstractC1889k7 o() {
        return C1549g7.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final boolean r() {
        B1 b1 = this.d;
        b1.getClass();
        return (b1 instanceof C1804j7) && !q();
    }

    @Override // com.android.tools.r8.internal.AbstractC2530rf
    public final boolean s() {
        return this.d.isUnknown();
    }

    @Override // com.android.tools.r8.internal.Cl0
    public final B1 a(C0333y c0333y) {
        return this.d;
    }

    public C2188nf(InterfaceC0370Av interfaceC0370Av) {
        C1804j7 c1804j7 = C1804j7.a;
        HashSet hashSet = new HashSet(1);
        hashSet.add(interfaceC0370Av);
        this(c1804j7, hashSet);
    }
}
