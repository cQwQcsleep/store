package com.android.tools.r8.synthesis;

import com.android.tools.r8.internal.InterfaceC0990Ys;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class T extends S.b {
    public final boolean d;

    public T(int i, String str, boolean z) {
        super(i, str);
        this.d = z;
    }

    @Override // com.android.tools.r8.synthesis.S.b
    public void a(InterfaceC0990Ys interfaceC0990Ys) {
        com.android.tools.r8.internal.E e = (com.android.tools.r8.internal.E) interfaceC0990Ys;
        e.a("class".getBytes(StandardCharsets.UTF_8));
        e.a(this.d ? (byte) 1 : (byte) 0);
    }

    @Override // com.android.tools.r8.synthesis.S.b
    public boolean d() {
        return this instanceof U;
    }

    @Override // com.android.tools.r8.synthesis.S.b
    public boolean e() {
        return false;
    }

    @Override // com.android.tools.r8.synthesis.S.b
    public boolean f() {
        return false;
    }

    @Override // com.android.tools.r8.synthesis.S.b
    public boolean g() {
        return this.d;
    }

    @Override // com.android.tools.r8.synthesis.S.b
    public final boolean h() {
        return false;
    }
}
