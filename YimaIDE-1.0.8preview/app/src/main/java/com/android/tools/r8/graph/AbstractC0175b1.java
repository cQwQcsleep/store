package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0175b1;
import com.android.tools.r8.graph.C0285r0;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.graph.b1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0175b1 extends AbstractC0259n1 {
    public static final /* synthetic */ boolean c = true;
    public C0306u0 b;

    public AbstractC0175b1(C0306u0 c0306u0) {
        if (c || c0306u0 != null) {
            this.b = c0306u0;
        } else {
            x01.a("Should use DexAnnotationSet.THE_EMPTY_ANNOTATIONS_SET");
            throw null;
        }
    }

    public static Stream a(Stream stream, final Function function) {
        Predicate predicate = new Predicate() { // from class: pgg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC0175b1) obj).w0();
            }
        };
        return stream.filter(predicate).map(new Function() { // from class: qgg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return function.apply(((AbstractC0175b1) obj).p0());
            }
        });
    }

    public static Stream b(Stream stream, final Function function) {
        Predicate predicate = new Predicate() { // from class: rgg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC0175b1) obj).y0();
            }
        };
        return stream.filter(predicate).map(new Function() { // from class: sgg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return function.apply(((AbstractC0175b1) obj).r0());
            }
        });
    }

    public abstract boolean A0();

    public void J() {
        this.b = C0306u0.o0();
    }

    public D2 X() {
        return null;
    }

    public boolean a0() {
        return false;
    }

    public abstract AbstractC0208g getAccessFlags();

    public abstract F2 getReference();

    public C0306u0 n0() {
        return this.b;
    }

    public E0 o0() {
        return null;
    }

    public C0210g1 p0() {
        return null;
    }

    public AbstractC0217h1 q0() {
        return null;
    }

    public C0231j1 r0() {
        return null;
    }

    public final void s0() {
        this.b = C0306u0.o0();
    }

    public final boolean t0() {
        return !n0().isEmpty();
    }

    public boolean u0() {
        return t0();
    }

    public boolean v0() {
        return false;
    }

    public boolean w0() {
        return this instanceof C0210g1;
    }

    public boolean x0() {
        return false;
    }

    public boolean y0() {
        return false;
    }

    public I2 z() {
        return v0() ? o0().e : q0().E0();
    }

    public abstract boolean z0();

    public final void a(Predicate predicate) {
        this.b = n0().a(predicate);
    }

    public void a(final BiFunction biFunction) {
        this.b = n0().a(new Function() { // from class: tgg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(biFunction, (C0285r0) obj);
            }
        });
    }

    public final C0285r0 a(BiFunction biFunction, C0285r0 c0285r0) {
        return (C0285r0) biFunction.apply(c0285r0, EnumC0272p0.a(getReference()));
    }
}
