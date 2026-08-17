package com.android.tools.r8.internal;

import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gi0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1597gi0 {
    public static final /* synthetic */ boolean a = true;

    public final AbstractC1597gi0 a(Function function) {
        return d() ? (AbstractC1597gi0) function.apply(b()) : this;
    }

    public C1512fi0 b() {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public static AbstractC1597gi0 a(boolean z) {
        return z ? C1512fi0.c : C1341di0.c;
    }

    public C1341di0 a() {
        return null;
    }

    public static C1341di0 a(Object obj) {
        return new C1341di0(obj);
    }
}
