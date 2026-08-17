package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0473Eu;
import java.util.function.BooleanSupplier;

/* JADX INFO: renamed from: com.android.tools.r8.graph.f3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0205f3 extends AbstractC0208g<C0205f3> {
    public static final /* synthetic */ boolean f = true;

    public C0205f3(int i) {
        super(i, i);
    }

    public static C0205f3 e(int i) {
        return new C0205f3(i & 20703);
    }

    public static C0205f3 f(int i) {
        if (f || (i & 20703) == i) {
            return new C0205f3(i & 20703);
        }
        x1f.a();
        return null;
    }

    public int F() {
        return this.c;
    }

    public final boolean H() {
        return AbstractC0208g.d(this.c, 16384);
    }

    public final boolean I() {
        return AbstractC0208g.d(this.c, 128);
    }

    public final boolean J() {
        return AbstractC0208g.d(this.c, 64);
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g, com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    public final AbstractC0551Hu c() {
        return new C0473Eu(4).b((Iterable) AbstractC0208g.d).a("volatile").a("transient").a("enum").a();
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    public final AbstractC0551Hu d() {
        return new C0473Eu(4).b((Iterable) super.d()).a(new BooleanSupplier() { // from class: yug
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.J();
            }
        }).a(new BooleanSupplier() { // from class: avg
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.I();
            }
        }).a(new BooleanSupplier() { // from class: cvg
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.a.H();
            }
        }).a();
    }

    @Override // com.android.tools.r8.graph.AbstractC0208g
    /* JADX INFO: renamed from: t */
    public final AbstractC0208g R() {
        return this;
    }

    public C0205f3(int i, int i2) {
        super(i, i2);
    }
}
