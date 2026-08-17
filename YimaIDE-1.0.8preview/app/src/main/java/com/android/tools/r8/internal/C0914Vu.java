package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Vu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0914Vu extends AbstractC2724tu {
    public final /* synthetic */ AbstractC0551Hu d;
    public final /* synthetic */ C0940Wu e;

    public C0914Vu(C0940Wu c0940Wu, AbstractC0551Hu abstractC0551Hu) {
        this.e = c0940Wu;
        this.d = abstractC0551Hu;
    }

    @Override // java.util.List
    public final Object get(int i) {
        return ((Map.Entry) this.d.get(i)).getValue();
    }

    @Override // com.android.tools.r8.internal.AbstractC2724tu
    public final AbstractC3066xu k() {
        return this.e;
    }
}
