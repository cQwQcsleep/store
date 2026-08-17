package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0651Lq extends AbstractC0728Oq {
    public final /* synthetic */ Iterable b;

    public C0651Lq(C2924wC c2924wC) {
        this.b = c2924wC;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new KC(NC.a(this.b.iterator(), new InterfaceC0392Br() { // from class: sj9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Iterable) obj).iterator();
            }
        }));
    }
}
