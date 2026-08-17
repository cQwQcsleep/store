package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I6 {
    public final AbstractC2083mQ a;
    public final C2464qq b;

    public I6(AbstractC2083mQ abstractC2083mQ, C2464qq c2464qq) {
        this.a = abstractC2083mQ;
        this.b = c2464qq;
        if (c2464qq.b == 1 && c2464qq.c == 1) {
            return;
        }
        b6c.a(I4.a("BooleanFlagDelegate can work only with boolean flags (bitWidth = 1 and value = 1), but ", c2464qq, " was passed"));
        throw null;
    }

    public final boolean a(Object obj, AbstractC2083mQ abstractC2083mQ) {
        KB.c(abstractC2083mQ, "property");
        C2464qq c2464qq = this.b;
        return ((((Number) this.a.a(obj)).intValue() >>> c2464qq.a) & ((1 << c2464qq.b) - 1)) == c2464qq.c;
    }
}
