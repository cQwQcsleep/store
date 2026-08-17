package com.android.tools.r8.internal;

import defpackage.cs9;
import defpackage.ww0;
import defpackage.xw0;
import java.util.HashMap;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PY extends AbstractC3225zl {
    public static final PY c = new PY(new ww0());

    public PY(Supplier supplier) {
        super(supplier);
    }

    public static PY a(int i) {
        return new PY(new HashMap(i));
    }

    public static PY k() {
        return new PY(new xw0());
    }

    public static PY l() {
        return new PY(new cs9());
    }

    @Override // com.android.tools.r8.internal.AbstractC3225zl
    public final C2119mo b(com.android.tools.r8.graph.G0 g0) {
        return new C2119mo(OY.a, (com.android.tools.r8.graph.B5) g0);
    }

    public PY(HashMap map) {
        super(map);
    }
}
