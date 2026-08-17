package com.android.tools.r8.internal;

import com.android.tools.r8.dex.code.AbstractC0138z1;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2627sl extends AbstractC2457ql {
    public final AbstractC0138z1[] e;
    public final int f;

    public C2627sl(AbstractC0890Uw abstractC0890Uw, AbstractC0138z1[] abstractC0138z1Arr) {
        super(abstractC0890Uw);
        this.e = abstractC0138z1Arr;
        int iT = 0;
        for (AbstractC0138z1 abstractC0138z1 : abstractC0138z1Arr) {
            iT += abstractC0138z1.t();
        }
        this.f = iT;
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final void a(C2884vl c2884vl, ArrayList arrayList) {
        int iB = b();
        for (AbstractC0138z1 abstractC0138z1 : this.e) {
            arrayList.add(abstractC0138z1);
            abstractC0138z1.f(iB);
            iB += abstractC0138z1.t();
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final int c() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final int d() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final int e() {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final int a(C2884vl c2884vl) {
        return this.f;
    }

    @Override // com.android.tools.r8.internal.AbstractC2457ql
    public final boolean a(AbstractC2457ql abstractC2457ql, C2884vl c2884vl) {
        return (abstractC2457ql instanceof C2627sl) && Arrays.equals(this.e, ((C2627sl) abstractC2457ql).e);
    }
}
