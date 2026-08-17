package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3114yW {
    public static final /* synthetic */ boolean a = true;

    public final C3096yE a(String str) {
        throw new C3096yE(this, str);
    }

    public abstract String a();

    public final C3030xW b(String str) {
        if (a || str != null) {
            return new C3030xW(this, str);
        }
        x1f.a();
        return null;
    }

    public abstract String b();

    public AbstractC3114yW c() {
        return null;
    }

    public boolean d() {
        return this instanceof C2773uW;
    }
}
