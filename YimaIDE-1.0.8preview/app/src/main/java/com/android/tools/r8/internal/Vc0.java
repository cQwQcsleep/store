package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.InterfaceC0189d1;
import defpackage.s26;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Vc0 extends UY {
    public static final /* synthetic */ int f = 0;

    static {
        new Tc0();
    }

    @Override // com.android.tools.r8.internal.AbstractC0490Fl
    public final Set b() {
        final TreeSet treeSet = new TreeSet(Comparator.comparing(new s26()));
        forEach(new Consumer() { // from class: baf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                treeSet.add(((B5) obj).e());
            }
        });
        return treeSet;
    }

    public final /* synthetic */ void a(final AbstractC3148ys abstractC3148ys, final AbstractC3148ys abstractC3148ys2, final InterfaceC0189d1 interfaceC0189d1, final Consumer consumer) {
        forEach(new Consumer() { // from class: z9f
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                B5 b5 = (B5) obj;
                consumer.accept(b5.a(interfaceC0189d1, abstractC3148ys, abstractC3148ys2));
            }
        });
    }

    public final Uc0 b(final InterfaceC0189d1 interfaceC0189d1, final AbstractC3148ys abstractC3148ys) {
        final AbstractC3148ys abstractC3148ysG = AbstractC3148ys.g();
        return b(new InterfaceC0806Rq() { // from class: aaf
            @Override // com.android.tools.r8.internal.InterfaceC0806Rq
            public final void forEach(Consumer consumer) {
                this.a.a(abstractC3148ys, abstractC3148ysG, interfaceC0189d1, consumer);
            }
        });
    }

    public static Uc0 b(InterfaceC0806Rq interfaceC0806Rq) {
        final Uc0 uc0 = new Uc0();
        interfaceC0806Rq.forEach(new Consumer() { // from class: caf
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.add((B5) obj);
            }
        });
        return uc0;
    }
}
