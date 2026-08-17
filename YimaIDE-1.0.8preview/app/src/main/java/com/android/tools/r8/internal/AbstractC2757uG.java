package com.android.tools.r8.internal;

import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uG, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2757uG {
    public static final /* synthetic */ boolean a = true;

    public final Object a(Function function, Function function2) {
        if (f()) {
            return function.apply(a());
        }
        if (a || c() != null) {
            return function2.apply(c());
        }
        x1f.a();
        return null;
    }

    public ME b() {
        return null;
    }

    public AbstractC2671tG c() {
        return null;
    }

    public AG d() {
        return null;
    }

    public DG e() {
        return null;
    }

    public final boolean f() {
        return a() != null;
    }

    public final boolean g() {
        return e() != null;
    }

    public EE a() {
        return null;
    }
}
