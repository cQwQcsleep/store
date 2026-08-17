package com.android.tools.r8.internal;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C0972Ya;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ya, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0972Ya {
    public static final /* synthetic */ boolean e = true;
    public final K9 a;
    public final K9 b;
    public final List<com.android.tools.r8.graph.I2> c;
    public final List<K9> d;

    public C0972Ya(K9 k9, K9 k10, List list, List list2) {
        this.a = k9;
        this.b = k10;
        this.c = list;
        this.d = list2;
        if (e) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.android.tools.r8.graph.I2 i2 = (com.android.tools.r8.graph.I2) it.next();
            if (!e && i2 == null) {
                x1f.a();
                throw null;
            }
        }
    }

    public static void a(com.android.tools.r8.graph.O o, com.android.tools.r8.utils.structural.A a) {
        Function function = new Function() { // from class: i2g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0972Ya) obj).a;
            }
        };
        com.android.tools.r8.utils.structural.u uVarA = o.a();
        com.android.tools.r8.utils.structural.A a2 = a.a(function, uVarA, uVarA);
        Function function2 = new Function() { // from class: j2g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0972Ya) obj).b;
            }
        };
        com.android.tools.r8.utils.structural.u uVarA2 = o.a();
        a2.a(function2, uVarA2, uVarA2).h(new Function() { // from class: k2g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0972Ya) obj).c;
            }
        }).a(new Function() { // from class: l2g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0972Ya) obj).d;
            }
        }, o.a());
    }

    public final List a() {
        return this.d;
    }

    public final int a(C0972Ya c0972Ya, AbstractC3519a abstractC3519a, final com.android.tools.r8.graph.O o) {
        return abstractC3519a.a(this, c0972Ya, (com.android.tools.r8.utils.structural.y<C0972Ya>) new com.android.tools.r8.utils.structural.y() { // from class: m2g
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0972Ya.a(o, a);
            }
        });
    }

    public final void a(Consumer consumer) {
        this.d.forEach(consumer);
    }

    public final void a(final com.android.tools.r8.graph.Z5 z5) {
        List<com.android.tools.r8.graph.I2> list = this.c;
        Objects.requireNonNull(z5);
        list.forEach(new Consumer() { // from class: n2g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                z5.a((I2) obj);
            }
        });
    }
}
