package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2200nl extends AbstractC2457ql {
    public final AbstractC0138z1 e;

    public C2200nl(AbstractC0890Uw abstractC0890Uw, AbstractC0138z1 abstractC0138z1) {
        super(abstractC0890Uw);
        this.e = abstractC0138z1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final boolean a(AbstractC2457ql abstractC2457ql, C2884vl c2884vl) {
        return (abstractC2457ql instanceof C2200nl) && this.e.equals(((C2200nl) abstractC2457ql).e);
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final int c() {
        return this.e.t();
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final int d() {
        return this.e.t();
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final int e() {
        return this.e.t();
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final void a(C2884vl c2884vl, ArrayList arrayList) {
        arrayList.add(this.e);
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final int a(C2884vl c2884vl) {
        this.e.f(b());
        return this.e.t();
    }
}
