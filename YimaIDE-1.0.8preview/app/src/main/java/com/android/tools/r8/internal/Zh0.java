package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Zh0 extends AbstractC1088ai0 {
    public static final /* synthetic */ boolean c = true;
    public final int b;

    public Zh0(C0322w2 c0322w2, int i) {
        super(c0322w2);
        boolean z = c;
        if (!z && i < 0) {
            x1f.a();
            throw null;
        }
        if (z || i < c0322w2.A0()) {
            this.b = i;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1088ai0
    public final Zh0 a() {
        return this;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Zh0)) {
            return false;
        }
        Zh0 zh0 = (Zh0) obj;
        return this.a.a(zh0.a) && this.b == zh0.b;
    }

    public final int hashCode() {
        return (this.a.hashCode() * 7) + Integer.hashCode(this.b);
    }

    public final String toString() {
        return "MethodArg(" + b().w0().G0() + "#" + b().g + "#" + this.b + ")";
    }
}
