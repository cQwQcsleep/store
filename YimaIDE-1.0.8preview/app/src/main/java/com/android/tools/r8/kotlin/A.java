package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0470Er;
import com.android.tools.r8.internal.C1053aI;
import com.android.tools.r8.internal.InterfaceC1938ki0;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.kotlin.A;
import com.android.tools.r8.kotlin.E;
import com.android.tools.r8.shaking.InterfaceC3369b0;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A implements InterfaceC3369b0 {
    public static final A b;
    public final List a;

    static {
        int i = AbstractC0551Hu.c;
        b = new A(P40.e);
    }

    public A(AbstractC0551Hu abstractC0551Hu) {
        this.a = abstractC0551Hu;
    }

    @Override // com.android.tools.r8.shaking.InterfaceC3369b0
    public final void a(InterfaceC0189d1 interfaceC0189d1) {
        C0470Er.a((Iterable) this.a, new Function() { // from class: i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return A.a((E) obj);
            }
        }, (Object) interfaceC0189d1);
    }

    public final boolean b(Consumer consumer, C0333y c0333y) {
        if (this == b) {
            return false;
        }
        return d0.a(c0333y, this.a, ((C1053aI) d0.a(consumer, new C1053aI())).a, new InterfaceC1938ki0() { // from class: d
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return Boolean.valueOf(((E) obj).b((Consumer) obj2, (C0333y) obj3));
            }
        });
    }

    public static /* synthetic */ Consumer a(final E e) {
        Objects.requireNonNull(e);
        return new Consumer() { // from class: k
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                e.a((InterfaceC0189d1) obj);
            }
        };
    }
}
