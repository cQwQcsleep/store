package com.android.tools.r8.naming;

import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.naming.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3333l {
    public final String a;
    public final String b;

    public C3333l(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3333l)) {
            return false;
        }
        C3333l c3333l = (C3333l) obj;
        return this.a.equals(c3333l.a) && this.b.equals(c3333l.b);
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
