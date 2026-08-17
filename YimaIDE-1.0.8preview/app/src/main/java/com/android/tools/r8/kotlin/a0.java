package com.android.tools.r8.kotlin;

import com.android.tools.r8.internal.AbstractC3016xI;
import com.android.tools.r8.internal.C1734iI;
import com.android.tools.r8.internal.InterfaceC2930wI;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class a0 extends AbstractC3016xI {
    public final /* synthetic */ b0 b;

    public a0(b0 b0Var) {
        this.b = b0Var;
    }

    @Override // com.android.tools.r8.internal.AbstractC3016xI
    public final InterfaceC2930wI a(C1734iI c1734iI) {
        if (c1734iI != com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.m.b) {
            return null;
        }
        return new Z(this);
    }
}
