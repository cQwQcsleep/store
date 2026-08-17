package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.de0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1333de0 implements InterfaceC2134n1, Comparable<AbstractC1333de0> {
    public abstract <E1 extends Exception, E2 extends Exception> void a(InterfaceC1936kh0<? super Yd0, E1> interfaceC1936kh0, InterfaceC1936kh0<? super C1080ae0, E2> interfaceC1936kh1) throws Exception;

    public abstract void a(Appendable appendable);

    public abstract com.android.tools.r8.graph.F2 c();

    @Override // java.lang.Comparable
    public final int compareTo(AbstractC1333de0 abstractC1333de0) {
        return c().b(abstractC1333de0.c());
    }
}
