package com.android.tools.r8.internal;

import java.util.function.ObjIntConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1273cv extends AbstractC2981wu {
    public final InterfaceC1231cQ a = new C1736iK();

    @Override // com.android.tools.r8.internal.AbstractC2981wu
    public final AbstractC2981wu a(Object obj) {
        InterfaceC1231cQ interfaceC1231cQ = this.a;
        obj.getClass();
        interfaceC1231cQ.add(obj);
        return this;
    }

    public final C1273cv b(Iterable iterable) {
        if (iterable instanceof InterfaceC1231cQ) {
            ((InterfaceC1231cQ) iterable).a(new ObjIntConsumer() { // from class: elg
                @Override // java.util.function.ObjIntConsumer
                public final void accept(Object obj, int i) {
                    this.a.a(obj, i);
                }
            });
            return this;
        }
        a(iterable);
        return this;
    }

    public final void a(Object obj, int i) {
        InterfaceC1231cQ interfaceC1231cQ = this.a;
        obj.getClass();
        interfaceC1231cQ.a(obj, i);
    }
}
