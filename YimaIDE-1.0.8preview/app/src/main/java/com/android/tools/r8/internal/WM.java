package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class WM extends Wh0 {
    public final /* synthetic */ InterfaceC0392Br c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WM(Iterator it, InterfaceC0392Br interfaceC0392Br) {
        super(it);
        this.c = interfaceC0392Br;
    }

    @Override // com.android.tools.r8.internal.Wh0
    public final Object a(Object obj) {
        return new C3236zu(obj, this.c.apply(obj));
    }
}
