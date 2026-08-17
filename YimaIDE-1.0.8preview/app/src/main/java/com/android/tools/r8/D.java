package com.android.tools.r8;

import com.android.tools.r8.graph.A0;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.naming.C3313b;
import java.io.PrintStream;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D implements C {
    @Override // com.android.tools.r8.C
    public final A0 a(C3313b c3313b) {
        return new A0() { // from class: s33
            @Override // com.android.tools.r8.graph.A0
            public final PrintStream a(E0 e0) {
                return System.out;
            }
        };
    }

    @Override // com.android.tools.r8.C
    public final boolean b() {
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // com.android.tools.r8.C
    public final Consumer a() {
        return C0822Sg.b();
    }
}
