package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.OverlayablePolicy;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class C2121mq extends AbstractC2083mQ {
    public static final /* synthetic */ int i = 0;

    @Override // com.android.tools.r8.internal.AbstractC2083mQ
    public final Object a(Object obj) {
        return Integer.valueOf(((II) obj).a());
    }

    @Override // com.android.tools.r8.internal.AbstractC2063m8
    public final String d() {
        return OverlayablePolicy.NAME_flags;
    }

    @Override // com.android.tools.r8.internal.AbstractC2063m8
    public final InterfaceC2328pE e() {
        return AbstractC2654t40.a(II.class);
    }

    @Override // com.android.tools.r8.internal.AbstractC2063m8
    public final String f() {
        return "getFlags()I";
    }

    @Override // com.android.tools.r8.internal.AbstractC2083mQ
    public final void a(Integer num, Object obj) {
        ((II) obj).b = num.intValue();
    }
}
