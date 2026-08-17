package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.C1749iX;
import com.android.tools.r8.utils.structural.A;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1749iX extends AbstractC2004lX {
    public static final /* synthetic */ int k = 0;
    public final C0867Tz h;
    public final C0322w2 i;
    public final boolean j;

    public C1749iX(int i, C0322w2 c0322w2, AbstractC2004lX abstractC2004lX, boolean z, boolean z2, C0867Tz c0867Tz, C0322w2 c0322w3, boolean z3) {
        super(i, c0322w2, abstractC2004lX, z, z2);
        this.h = c0867Tz;
        this.i = c0322w3;
        this.j = z3;
    }

    public static void c(com.android.tools.r8.utils.structural.A a) {
        com.android.tools.r8.utils.structural.y yVar = new com.android.tools.r8.utils.structural.y() { // from class: l8h
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a2) {
                AbstractC2004lX.a(a2);
            }
        };
        a.getClass();
        yVar.a(a);
        a.a().b(new Predicate() { // from class: m8h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC2004lX) obj).p();
            }
        }).e(new Function() { // from class: n8h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AbstractC2004lX) obj).i();
            }
        }).e(new Function() { // from class: o8h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AbstractC2004lX) obj).j();
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final C1749iX a() {
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final AbstractC2004lX.a b() {
        C1663hX c1663hX = (C1663hX) ((C1663hX) new C1663hX().a(this.b)).a(this.c);
        c1663hX.c = this.d;
        C1663hX c1663hX2 = (C1663hX) c1663hX.c();
        c1663hX2.i = this.i;
        c1663hX2.j = this.j;
        c1663hX2.d = this.e;
        C1663hX c1663hX3 = (C1663hX) c1663hX2.c();
        c1663hX3.e = this.f;
        final C1663hX c1663hX4 = (C1663hX) c1663hX3.c();
        this.h.a(new BiConsumer() { // from class: p8h
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                c1663hX4.a(((Integer) obj).intValue(), (AbstractC2004lX) obj2);
            }
        });
        return c1663hX4;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final int d() {
        return 4;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final C0322w2 i() {
        return this.i;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final C0867Tz j() {
        return this.h;
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final boolean n() {
        return false;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: q8h
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C1749iX.c(a);
            }
        };
    }

    @Override // com.android.tools.r8.internal.AbstractC2004lX
    public final boolean p() {
        return this.j;
    }
}
