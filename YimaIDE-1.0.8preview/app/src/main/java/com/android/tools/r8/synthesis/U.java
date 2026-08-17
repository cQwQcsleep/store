package com.android.tools.r8.synthesis;

import com.android.tools.r8.internal.InterfaceC0990Ys;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U extends T {
    public final boolean e;

    public U(int i, String str, boolean z) {
        super(i, str, false);
        this.e = z;
    }

    @Override // com.android.tools.r8.synthesis.T, com.android.tools.r8.synthesis.S.b
    public final void a(InterfaceC0990Ys interfaceC0990Ys) {
        com.android.tools.r8.internal.E e = (com.android.tools.r8.internal.E) interfaceC0990Ys;
        e.a((b().isEmpty() ? "global" : "fixed").getBytes(StandardCharsets.UTF_8));
        e.a(this.e ? (byte) 1 : (byte) 0);
    }

    @Override // com.android.tools.r8.synthesis.T, com.android.tools.r8.synthesis.S.b
    public final boolean e() {
        return b().isEmpty();
    }

    @Override // com.android.tools.r8.synthesis.T, com.android.tools.r8.synthesis.S.b
    public final boolean f() {
        return this.e;
    }

    @Override // com.android.tools.r8.synthesis.T, com.android.tools.r8.synthesis.S.b
    public final boolean g() {
        return false;
    }
}
