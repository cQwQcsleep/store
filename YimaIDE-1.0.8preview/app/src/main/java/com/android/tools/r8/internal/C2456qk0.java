package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qk0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2456qk0 {
    public static final /* synthetic */ int f = 0;
    public List a;
    public List b;
    public List c;
    public List d;
    public List e;

    static {
        new C2371pk0().a();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2456qk0)) {
            return false;
        }
        C2456qk0 c2456qk0 = (C2456qk0) obj;
        return Arrays.equals(new Object[]{this.a, this.b, this.c, this.d, this.e}, new Object[]{c2456qk0.a, c2456qk0.b, c2456qk0.c, c2456qk0.d, c2456qk0.e});
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, this.c, this.d, this.e});
    }
}
