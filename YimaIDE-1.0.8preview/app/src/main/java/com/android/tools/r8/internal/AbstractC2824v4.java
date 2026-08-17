package com.android.tools.r8.internal;

import java.io.OutputStreamWriter;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2824v4 implements Comparable, InterfaceC2134n1 {
    public abstract Object a(InterfaceC2022lh0 interfaceC2022lh0, InterfaceC2022lh0 interfaceC2022lh1);

    public abstract void a(InterfaceC1936kh0 interfaceC1936kh0, InterfaceC1936kh0 interfaceC1936kh1);

    public abstract void a(OutputStreamWriter outputStreamWriter);

    public abstract com.android.tools.r8.graph.F2 c();

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return c().b(((AbstractC2824v4) obj).c());
    }
}
