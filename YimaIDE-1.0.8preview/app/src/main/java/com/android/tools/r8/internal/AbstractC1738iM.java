package com.android.tools.r8.internal;

import java.util.function.IntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1738iM {
    public static final /* synthetic */ boolean e = true;
    public final IntFunction a;
    public final IntFunction b;
    public AbstractC3148ys c;
    public Object d;

    public AbstractC1738iM(AbstractC3148ys abstractC3148ys, IntFunction intFunction, IntFunction intFunction2) {
        this.c = abstractC3148ys;
        this.a = intFunction;
        this.b = intFunction2;
        this.d = intFunction2.apply(2);
    }
}
