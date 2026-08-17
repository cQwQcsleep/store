package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2924wC extends AbstractC0728Oq {
    public final /* synthetic */ Iterable b;
    public final /* synthetic */ InterfaceC0392Br c;

    public C2924wC(Iterable iterable, InterfaceC0392Br interfaceC0392Br) {
        this.b = iterable;
        this.c = interfaceC0392Br;
    }

    @Override // java.lang.Iterable
    public final void forEach(final Consumer consumer) {
        consumer.getClass();
        Iterable iterable = this.b;
        final InterfaceC0392Br interfaceC0392Br = this.c;
        iterable.forEach(new Consumer() { // from class: uni
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                consumer.accept(interfaceC0392Br.apply(obj));
            }
        });
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return NC.a(this.b.iterator(), this.c);
    }

    @Override // java.lang.Iterable
    public final Spliterator spliterator() {
        return AbstractC1165be.a(this.b.spliterator(), this.c);
    }
}
