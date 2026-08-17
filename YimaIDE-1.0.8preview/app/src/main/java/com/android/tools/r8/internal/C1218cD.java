package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cD, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1218cD {
    public final C2752uB a;
    public boolean b = Kg0.a("com.android.tools.r8.jetpackcompose.enableComposableOptimizationPass", System.getProperty("com.android.tools.r8.jetpackcompose.enableComposableOptimizationPass"), false);
    public boolean c = Kg0.a("com.android.tools.r8.jetpackcompose.enableModelingOfChangedArguments", System.getProperty("com.android.tools.r8.jetpackcompose.enableModelingOfChangedArguments"), false);

    public C1218cD(C2752uB c2752uB) {
        this.a = c2752uB;
    }

    public final boolean a() {
        return this.a.e0() && this.a.g0() && this.c;
    }

    public void a(boolean z) {
        this.b = z;
        this.c = z;
    }
}
