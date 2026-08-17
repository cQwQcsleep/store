package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2516rW extends AbstractC3114yW {
    public final AbstractC3114yW b;
    public final String c;

    public C2516rW(AbstractC3114yW abstractC3114yW, String str) {
        if (abstractC3114yW.d()) {
            abstractC3114yW = abstractC3114yW.c();
            if (!AbstractC3114yW.a && abstractC3114yW.d()) {
                x1f.a();
                throw null;
            }
        }
        this.b = abstractC3114yW;
        this.c = str;
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final String a() {
        return "@".concat(this.c.substring(Math.max(this.c.lastIndexOf(47) + 1, 1), this.c.length() - 1));
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final String b() {
        return "annotation";
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final AbstractC3114yW c() {
        return this.b;
    }
}
