package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E5;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.MX;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class E5 implements Iterable {
    public static final /* synthetic */ boolean d = true;
    public final String b;
    public final Set c;

    public E5(String str, Supplier supplier) {
        this.b = str;
        this.c = (Set) supplier.get();
    }

    public final void a(final Consumer consumer) {
        forEach(new Consumer() { // from class: j34
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                E5.a(consumer, (D2) obj);
            }
        });
    }

    public final void b(final Consumer consumer) {
        forEach(new Consumer() { // from class: i34
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((D2) obj).n((Consumer<? super B5>) consumer);
            }
        });
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.c.iterator();
    }

    public final String toString() {
        return "ProgramPackage(" + C0929Wj.n(this.b) + ")";
    }

    public final String a() {
        return this.b;
    }

    public static void a(Consumer consumer, D2 d2) {
        d2.getClass();
        d2.f(consumer, MX.b);
    }
}
