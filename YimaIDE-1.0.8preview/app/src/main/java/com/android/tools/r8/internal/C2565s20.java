package com.android.tools.r8.internal;

import com.android.tools.r8.utils.structural.AbstractC3519a;
import defpackage.o0e;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s20, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2565s20 implements com.android.tools.r8.utils.structural.u {
    @Override // com.android.tools.r8.utils.structural.w
    public final void a(Object obj, com.android.tools.r8.utils.structural.o oVar) {
        for (String str : (String[]) obj) {
            oVar.a(str);
        }
    }

    @Override // com.android.tools.r8.utils.structural.v
    public final /* bridge */ /* synthetic */ int a(Object obj, Object obj2, AbstractC3519a abstractC3519a) {
        return a((String[]) obj, (String[]) obj2);
    }

    public static int a(String[] strArr, String[] strArr2) {
        return AbstractC2956we.a(new o0e()).compare(strArr, strArr2);
    }
}
