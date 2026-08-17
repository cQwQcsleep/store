package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.o2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2222o2 extends Exception {
    public final transient G b;

    public C2222o2(G g, String str, Object obj, InterfaceC2458ql0 interfaceC2458ql0) {
        super((str == null ? "Expected " : str.concat(": expected ")) + obj + ", but found " + interfaceC2458ql0);
        this.b = g;
    }

    public C2222o2(G g, String str, Exception exc) {
        super(str, exc);
        this.b = g;
    }

    public C2222o2(G g, String str) {
        super(str);
        this.b = g;
    }
}
