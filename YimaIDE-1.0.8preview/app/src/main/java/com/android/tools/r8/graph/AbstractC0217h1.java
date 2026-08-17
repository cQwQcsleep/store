package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0217h1;
import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.C0210g1;
import com.android.tools.r8.graph.C0231j1;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.graph.h1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0217h1<D extends AbstractC0217h1<D, R>, R extends AbstractC0287r2<D, R>> extends AbstractC0175b1 {
    public final boolean d;
    public com.android.tools.r8.androidapi.f e;
    public final AbstractC0287r2 f;

    public AbstractC0217h1(AbstractC0287r2 abstractC0287r2, C0306u0 c0306u0, boolean z, com.android.tools.r8.androidapi.f fVar) {
        super(c0306u0);
        this.f = abstractC0287r2;
        this.d = z;
        this.e = fVar;
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public boolean A0() {
        return z0();
    }

    public abstract void B();

    public abstract void B0();

    public abstract com.android.tools.r8.androidapi.f C0();

    public final com.android.tools.r8.androidapi.f D0() {
        return this.e;
    }

    public I2 E0() {
        return getReference().w0();
    }

    public H2 F0() {
        return getReference().x0();
    }

    public abstract com.android.tools.r8.ir.optimize.info.g G0();

    @Override // com.android.tools.r8.graph.AbstractC0175b1, com.android.tools.r8.graph.InterfaceC0265o0
    /* JADX INFO: renamed from: H0, reason: merged with bridge method [inline-methods] */
    public R getReference() {
        return (R) this.f;
    }

    public boolean I0() {
        return this.d;
    }

    public final boolean J0() {
        return getAccessFlags().i();
    }

    public final boolean K0() {
        return getAccessFlags().m();
    }

    public abstract com.android.tools.r8.kotlin.P W();

    public abstract Object a(Function function, Function function2);

    public final void a(final Consumer consumer, final Consumer consumer2) {
        a(new Function() { // from class: a0h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC0217h1.a(consumer, (C0210g1) obj);
            }
        }, new Function() { // from class: m0h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC0217h1.a(consumer2, (C0231j1) obj);
            }
        });
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final AbstractC0217h1 q0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0175b1
    public final boolean x0() {
        return true;
    }

    public static /* synthetic */ Object a(Consumer consumer, C0210g1 c0210g1) {
        consumer.accept(c0210g1);
        return null;
    }

    public static /* synthetic */ Object a(Consumer consumer, C0231j1 c0231j1) {
        consumer.accept(c0231j1);
        return null;
    }
}
