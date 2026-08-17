package com.android.tools.r8.internal;

import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1154bX implements DW {
    public static final /* synthetic */ boolean c = true;
    public final Predicate a;
    public int b = -1;

    public C1154bX(Predicate predicate) {
        this.a = predicate;
    }

    @Override // com.android.tools.r8.internal.DW
    public final void a(int i) {
        if (c || this.b == -1) {
            this.b = i;
        } else {
            x1f.a();
        }
    }

    @Override // com.android.tools.r8.internal.DW
    public final Predicate b() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.DW
    public final int c() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.DW
    public final int a() {
        return 1;
    }
}
