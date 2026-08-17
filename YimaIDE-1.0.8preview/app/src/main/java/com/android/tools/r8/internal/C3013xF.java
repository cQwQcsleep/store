package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3013xF extends C2756uF {
    public static final /* synthetic */ boolean e = true;
    public final String b;
    public final String c;
    public final String d;

    public C3013xF(String str, String str2, String str3) {
        boolean z = e;
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        if (!z && str2 == null) {
            x1f.a();
            throw null;
        }
        if (!z && str3 == null) {
            x1f.a();
            throw null;
        }
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final String a() {
        return this.b + this.c + this.d;
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3013xF)) {
            return false;
        }
        C3013xF c3013xF = (C3013xF) obj;
        return this.b.equals(c3013xF.b) && this.c.equals(c3013xF.c) && this.d.equals(c3013xF.d);
    }

    @Override // com.android.tools.r8.internal.C2756uF
    public final int hashCode() {
        return Objects.hash(this.b, this.c, this.d);
    }
}
