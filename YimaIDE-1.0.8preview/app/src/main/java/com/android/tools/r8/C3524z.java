package com.android.tools.r8;

import com.android.tools.r8.graph.A0;
import com.android.tools.r8.graph.B0;
import com.android.tools.r8.naming.C3313b;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3524z implements C {
    public final Path a;
    public final String b;

    public C3524z(String str, Path path) {
        this.a = path;
        this.b = str;
    }

    @Override // com.android.tools.r8.C
    public final A0 a(C3313b c3313b) {
        return B0.a(c3313b, this.a, this.b);
    }

    @Override // com.android.tools.r8.C
    public final boolean b() {
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // com.android.tools.r8.C
    public final Consumer a() {
        return new Consumer() { // from class: lxi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((PrintStream) obj).close();
            }
        };
    }
}
