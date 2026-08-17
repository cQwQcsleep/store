package com.android.tools.r8.internal;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ks, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1952ks extends C1867js {
    public final Method f;

    public C1952ks(C1856jk c1856jk, String str, Class cls, Class cls2, String str2) {
        super(c1856jk, str, cls, cls2, str2);
        this.f = AbstractC2209ns.a(this.a, "newBuilder", new Class[0]);
        AbstractC2209ns.a(cls2, C40.a("get", str, "Builder"), new Class[0]);
    }

    @Override // com.android.tools.r8.internal.C1867js, com.android.tools.r8.internal.InterfaceC1188bs
    public final H0 a() {
        return (H0) AbstractC2209ns.a(this.f, (Object) null, new Object[0]);
    }

    @Override // com.android.tools.r8.internal.C1867js, com.android.tools.r8.internal.InterfaceC1188bs
    public final void b(AbstractC0911Vr abstractC0911Vr, Object obj) {
        if (!this.a.isInstance(obj)) {
            obj = ((H0) AbstractC2209ns.a(this.f, (Object) null, new Object[0])).b((J0) obj).i();
        }
        super.b(abstractC0911Vr, obj);
    }
}
