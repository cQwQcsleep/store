package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2927wF extends C2756uF {
    public final String b;
    public final String c;
    public final String d;

    public C2927wF(String str, String str2, String str3) {
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final String a() {
        return this.b + this.c + ":" + this.d;
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2927wF)) {
            return false;
        }
        C2927wF c2927wF = (C2927wF) obj;
        return this.b.equals(c2927wF.b) && this.c.equals(c2927wF.c) && this.d.equals(c2927wF.d);
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final int hashCode() {
        return Objects.hash(this.b, this.c, this.d);
    }
}
