package com.android.tools.r8.internal;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ls, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2038ls extends C1867js {
    public final Method f;

    public C2038ls(C1856jk c1856jk, String str, Class cls, Class cls2, String str2) {
        super(c1856jk, str, cls, cls2, str2);
        AbstractC2209ns.a(cls, C40.a("get", str, "Bytes"), new Class[0]);
        AbstractC2209ns.a(cls2, C40.a("get", str, "Bytes"), new Class[0]);
        this.f = AbstractC2209ns.a(cls2, C40.a("set", str, "Bytes"), new Class[]{U7.class});
    }

    @Override // com.android.tools.r8.internal.C1867js, com.android.tools.r8.internal.InterfaceC1188bs
    public final void b(AbstractC0911Vr abstractC0911Vr, Object obj) {
        if (obj instanceof U7) {
            AbstractC2209ns.a(this.f, abstractC0911Vr, new Object[]{obj});
        } else {
            super.b(abstractC0911Vr, obj);
        }
    }
}
