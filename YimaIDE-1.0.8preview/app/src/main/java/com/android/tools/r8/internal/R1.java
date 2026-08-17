package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class R1 {
    public boolean a = true;
    public final C2752uB b;

    public R1(C2752uB c2752uB) {
        this.b = c2752uB;
    }

    public final boolean a() {
        C2752uB c2752uB = this.b;
        if (c2752uB.h != null && c2752uB.H().f) {
            return true;
        }
        if (!this.b.f0.isEmpty()) {
            return false;
        }
        C2752uB c2752uB2 = this.b;
        return !c2752uB2.V0 && c2752uB2.e0();
    }

    public void a(boolean z) {
        this.a = z;
    }
}
