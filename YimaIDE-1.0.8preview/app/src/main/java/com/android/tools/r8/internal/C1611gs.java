package com.android.tools.r8.internal;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1611gs extends C1526fs {
    public final Method c;

    public C1611gs(String str, Class cls, Class cls2) {
        super(str, cls, cls2);
        this.c = AbstractC2209ns.a(this.a, "newBuilder", new Class[0]);
        AbstractC2209ns.a(cls2, C40.a("get", str, "Builder"), new Class[]{Integer.TYPE});
    }

    @Override // com.android.tools.r8.internal.C1526fs, com.android.tools.r8.internal.InterfaceC1188bs
    public final void a(AbstractC0911Vr abstractC0911Vr, Object obj) {
        if (!this.a.isInstance(obj)) {
            obj = ((H0) AbstractC2209ns.a(this.c, (Object) null, new Object[0])).b((J0) obj).h();
        }
        super.a(abstractC0911Vr, obj);
    }

    @Override // com.android.tools.r8.internal.C1526fs, com.android.tools.r8.internal.InterfaceC1188bs
    public final H0 a() {
        return (H0) AbstractC2209ns.a(this.c, (Object) null, new Object[0]);
    }
}
