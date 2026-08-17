package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Jv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0604Jv extends IllegalArgumentException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0604Jv(String str) {
        super(str, null);
        KB.c(str, "message");
    }

    public C0604Jv(Throwable th) {
        super("Exception occurred when reading Kotlin metadata", th);
    }
}
