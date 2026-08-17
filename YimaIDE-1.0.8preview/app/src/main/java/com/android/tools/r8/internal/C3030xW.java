package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xW, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3030xW extends AbstractC3114yW {
    public final AbstractC3114yW b;
    public final String c;

    public C3030xW(AbstractC3114yW abstractC3114yW, String str) {
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
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final String b() {
        return "property";
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final AbstractC3114yW c() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC3114yW
    public final boolean d() {
        return "value".equals(this.c);
    }
}
