package com.android.tools.r8.internal;

import com.android.tools.r8.ir.optimize.C3242a;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ie0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1761ie0 implements YJ {
    public abstract InterfaceC0968Xw a(C0705Nt c0705Nt, L5 l5, InterfaceC0968Xw interfaceC0968Xw, AbstractC1047aC abstractC1047aC, com.android.tools.r8.graph.H0 h0, C3242a c3242a, Set set);

    @Override // com.android.tools.r8.internal.YJ
    public final InterfaceC0968Xw a(C0705Nt c0705Nt, L5 l5, InterfaceC0968Xw interfaceC0968Xw, AbstractC1047aC abstractC1047aC, com.android.tools.r8.graph.H0 h0, C3242a c3242a, Set set, XJ xj, IO io2, C0483Fe c0483Fe) {
        if (xj == null) {
            return a(c0705Nt, l5, interfaceC0968Xw, abstractC1047aC, h0, c3242a, set);
        }
        throw new ClassCastException();
    }

    @Override // com.android.tools.r8.internal.YJ
    public final /* bridge */ /* synthetic */ XJ a() {
        return null;
    }
}
