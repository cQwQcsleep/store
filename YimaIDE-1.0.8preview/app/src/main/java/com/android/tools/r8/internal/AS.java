package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AS implements InterfaceC2714tl0 {
    public int a = 0;

    @Override // com.android.tools.r8.internal.InterfaceC2714tl0
    public final C2543rl0 a(AbstractC2624sj0 abstractC2624sj0, C0230j0 c0230j0) {
        return new C2543rl0(a(), abstractC2624sj0, c0230j0);
    }

    public int b() {
        return this.a;
    }

    public int a() {
        int i = this.a;
        this.a = i + 1;
        return i;
    }
}
