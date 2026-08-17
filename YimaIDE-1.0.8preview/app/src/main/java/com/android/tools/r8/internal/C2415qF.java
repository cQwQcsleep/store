package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2415qF extends AbstractC2243oF {
    public static final /* synthetic */ boolean e = true;
    public final C3097yF a;
    public final IE b;
    public final AbstractC1222cH c;
    public final SE d;

    public C2415qF(C3097yF c3097yF, IE ie, AbstractC1222cH abstractC1222cH, SE se) {
        boolean z = e;
        if (!z && c3097yF == null) {
            x1f.a();
            throw null;
        }
        if (!z && ie == null) {
            x1f.a();
            throw null;
        }
        if (!z && abstractC1222cH == null) {
            x1f.a();
            throw null;
        }
        if (!z && se == null) {
            x1f.a();
            throw null;
        }
        this.a = c3097yF;
        this.b = ie;
        this.c = abstractC1222cH;
        this.d = se;
    }

    @Override // com.android.tools.r8.internal.AbstractC2243oF
    public final C2415qF b() {
        return this;
    }

    public final String toString() {
        return "KeepEdge{metainfo=" + this.a + ", preconditions=" + this.c + ", consequences=" + this.d + "}";
    }
}
