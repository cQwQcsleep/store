package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2021lh extends AbstractC2106mh {
    public static final C2021lh c = new C2021lh();

    @Override // com.android.tools.r8.internal.AbstractC2106mh
    /* JADX INFO: renamed from: a */
    public final int compareTo(AbstractC2106mh abstractC2106mh) {
        return abstractC2106mh == this ? 0 : -1;
    }

    @Override // com.android.tools.r8.internal.AbstractC2106mh
    public final void b(StringBuilder sb) {
        throw new AssertionError();
    }

    @Override // com.android.tools.r8.internal.AbstractC2106mh
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public final String toString() {
        return "-∞";
    }

    @Override // com.android.tools.r8.internal.AbstractC2106mh
    public final boolean a() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2106mh
    public final void a(StringBuilder sb) {
        sb.append("(-∞");
    }
}
