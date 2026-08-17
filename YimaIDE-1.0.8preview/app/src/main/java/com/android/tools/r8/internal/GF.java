package com.android.tools.r8.internal;

import defpackage.lx5;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GF extends K2 {
    public static final /* synthetic */ boolean e = true;
    public final /* synthetic */ C3030xW c;
    public final /* synthetic */ HF d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GF(HF hf, C3030xW c3030xW, C3030xW c3030xW2) {
        super(c3030xW);
        this.d = hf;
        this.c = c3030xW2;
    }

    @Override // com.android.tools.r8.internal.K2, com.android.tools.r8.internal.J2
    public final J2 a(String str, String str2) {
        if (!e && str != null) {
            x1f.a();
            return null;
        }
        if (!str2.equals("Lcom/android/tools/r8/keepanno/annotations/ExtractedKeepAnnotation;")) {
            super.a(str, str2);
            throw null;
        }
        C3030xW c3030xW = this.c;
        c3030xW.getClass();
        C2516rW c2516rW = new C2516rW(c3030xW, str2);
        ArrayList arrayList = this.d.d;
        Objects.requireNonNull(arrayList);
        return new FF(c2516rW, new lx5(arrayList));
    }
}
