package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.KB;
import com.android.tools.r8.internal.RH;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3306y implements G {
    public final String a;

    public C3306y(String str) {
        this.a = str;
    }

    public final boolean a(RH rh, C0245l1 c0245l1, AbstractC3345r0 abstractC3345r0) {
        String string = abstractC3345r0.a(c0245l1).toString();
        KB.c(string, TypeBlock.NAME_name);
        rh.i = string;
        return !string.equals(this.a);
    }

    @Override // com.android.tools.r8.kotlin.P
    public final boolean f() {
        return true;
    }

    @Override // com.android.tools.r8.kotlin.P
    public final C3306y g() {
        return this;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
    }
}
