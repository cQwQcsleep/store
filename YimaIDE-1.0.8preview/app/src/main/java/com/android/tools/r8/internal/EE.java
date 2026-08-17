package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class EE {
    public final HE a;

    public EE(HE he) {
        this.a = he;
    }

    public static C3098yG a(HE he) {
        return new C3098yG(he);
    }

    public final HE b() {
        return this.a;
    }

    public final boolean c() {
        return a() != null;
    }

    public abstract AbstractC2757uG d();

    public String toString() {
        return this.a.toString();
    }

    public KE a() {
        return null;
    }
}
