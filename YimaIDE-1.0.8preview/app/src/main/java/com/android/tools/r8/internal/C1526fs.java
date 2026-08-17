package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1526fs implements InterfaceC1188bs {
    public final Class a;
    public final C1440es b;

    public C1526fs(String str, Class cls, Class cls2) {
        C1440es c1440es = new C1440es(str, cls, cls2);
        this.a = c1440es.c.getReturnType();
        this.b = c1440es;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public void a(AbstractC0911Vr abstractC0911Vr, Object obj) {
        AbstractC2209ns.a(this.b.e, abstractC0911Vr, new Object[]{obj});
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public final void b(AbstractC0911Vr abstractC0911Vr, Object obj) {
        AbstractC2209ns.a(this.b.h, abstractC0911Vr, new Object[0]);
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            a(abstractC0911Vr, it.next());
        }
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public final boolean a(AbstractC2209ns abstractC2209ns) {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public final boolean a(AbstractC0911Vr abstractC0911Vr) {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public H0 a() {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public Object b(AbstractC0911Vr abstractC0911Vr) {
        return AbstractC2209ns.a(this.b.b, abstractC0911Vr, new Object[0]);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1188bs
    public Object b(AbstractC2209ns abstractC2209ns) {
        return AbstractC2209ns.a(this.b.a, abstractC2209ns, new Object[0]);
    }
}
