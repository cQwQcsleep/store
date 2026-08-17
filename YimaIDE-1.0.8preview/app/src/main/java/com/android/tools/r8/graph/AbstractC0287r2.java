package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0217h1;
import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C2924wC;
import com.android.tools.r8.internal.De0;
import com.android.tools.r8.internal.InterfaceC0392Br;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.r2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0287r2<D extends AbstractC0217h1<D, R>, R extends AbstractC0287r2<D, R>> extends F2 implements InterfaceC0221h5 {
    public static final /* synthetic */ boolean h = true;
    public final I2 f;
    public final H2 g;

    public AbstractC0287r2(H2 h2, I2 i2) {
        boolean z = h;
        if (!z && i2 == null) {
            x1f.a();
            throw null;
        }
        this.f = i2;
        if (z || h2 != null) {
            this.g = h2;
        } else {
            x1f.a();
            throw null;
        }
    }

    public abstract G0 a(E0 e0);

    public abstract AbstractC0287r2 a(F2 f2, B1 b1);

    public abstract Object a(Function function, Function function2);

    public final void a(Predicate predicate, B1 b1) {
        if (h || De0.a(a(b1)).allMatch(predicate)) {
            return;
        }
        x1f.a();
    }

    @Override // com.android.tools.r8.internal.UK
    public void b(com.android.tools.r8.utils.structural.o oVar) {
        a(oVar);
    }

    @Override // com.android.tools.r8.graph.F2
    public final AbstractC0287r2 p0() {
        return this;
    }

    @Override // com.android.tools.r8.graph.F2
    public final boolean t0() {
        return true;
    }

    public I2 w0() {
        return this.f;
    }

    public H2 x0() {
        return this.g;
    }

    public abstract Iterable y0();

    @Override // com.android.tools.r8.graph.F2
    public final I2 z() {
        return this.f;
    }

    public final C2924wC a(final B1 b1) {
        return AbstractC3179zC.a(y0(), new InterfaceC0392Br() { // from class: m6i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((I2) obj).a(b1);
            }
        });
    }
}
