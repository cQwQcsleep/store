package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.C3100yI;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3304w extends AbstractC3305x {
    public final String a;

    public C3304w(String str) {
        this.a = str;
    }

    @Override // com.android.tools.r8.kotlin.AbstractC3305x
    public final boolean a(C3100yI c3100yI, C0333y c0333y) {
        c3100yI.b(this.a);
        return false;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
    }
}
