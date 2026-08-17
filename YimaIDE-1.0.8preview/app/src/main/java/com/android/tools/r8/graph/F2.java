package com.android.tools.r8.graph;

import com.android.tools.r8.internal.UK;
import defpackage.hkh;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class F2 extends X3 implements UK {
    public static final /* synthetic */ boolean e = true;

    public static Object a(F2 f2, F2 f3, BiFunction biFunction, BiFunction biFunction2, BiFunction biFunction3) {
        f2.getClass();
        if (f2 instanceof I2) {
            return biFunction.apply(f2.r0(), f3.r0());
        }
        if (f2.s0()) {
            return biFunction2.apply(f2.o0(), f3.o0());
        }
        if (f2.u0()) {
            return biFunction3.apply(f2.q0(), f3.q0());
        }
        hkh.a();
        return null;
    }

    public abstract Object a(Function function, Function function2, Function function3);

    public abstract void a(C0333y c0333y, com.android.tools.r8.dex.M m);

    public abstract void a(Consumer consumer, Consumer consumer2, Consumer consumer3);

    public abstract int b(F2 f2);

    public C0245l1 o0() {
        return null;
    }

    public AbstractC0287r2 p0() {
        return null;
    }

    public C0322w2 q0() {
        return null;
    }

    public I2 r0() {
        return null;
    }

    public boolean s0() {
        return false;
    }

    public boolean t0() {
        return false;
    }

    public boolean u0() {
        return false;
    }

    public boolean v0() {
        return this instanceof I2;
    }

    public abstract I2 z();
}
