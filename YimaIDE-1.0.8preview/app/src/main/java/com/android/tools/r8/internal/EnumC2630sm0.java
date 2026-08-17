package com.android.tools.r8.internal;

import defpackage.x0g;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sm0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum EnumC2630sm0 {
    b,
    c,
    d,
    e;

    EnumC2630sm0() {
    }

    @Override // java.lang.Enum
    public final String toString() {
        int i = AbstractC2545rm0.a[ordinal()];
        if (i == 1) {
            return "public";
        }
        if (i == 2) {
            return "protected";
        }
        if (i == 3) {
            return "private";
        }
        if (i == 4) {
            return "package-private";
        }
        x0g.a("Unexpected visibility");
        return null;
    }
}
