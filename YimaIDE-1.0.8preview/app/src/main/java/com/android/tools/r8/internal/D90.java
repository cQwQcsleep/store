package com.android.tools.r8.internal;

import com.android.tools.r8.naming.C3331k;
import java.util.OptionalInt;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D90 {
    public final C3331k.b a;
    public final OptionalInt b;

    public D90(C3331k.b bVar, OptionalInt optionalInt) {
        this.a = bVar;
        this.b = optionalInt;
    }

    public static D90 a(C3331k.b bVar) {
        com.android.tools.r8.naming.N0 n0 = bVar.d;
        return new D90(bVar, (n0 == null || n0.a() != 1) ? OptionalInt.empty() : OptionalInt.of(bVar.d.a));
    }
}
