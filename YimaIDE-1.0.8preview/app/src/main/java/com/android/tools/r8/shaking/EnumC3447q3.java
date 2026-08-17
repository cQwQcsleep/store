package com.android.tools.r8.shaking;

import defpackage.x0g;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.q3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum EnumC3447q3 {
    b,
    c,
    d,
    e;

    EnumC3447q3() {
    }

    @Override // java.lang.Enum
    public String toString() {
        int i = AbstractC3442p3.a[ordinal()];
        if (i == 1) {
            return "keep";
        }
        if (i == 2) {
            return "keepclassmembers";
        }
        if (i == 3) {
            return "keepclasseswithmembers";
        }
        if (i == 4) {
            return "if";
        }
        x0g.a("Unknown ProguardKeepRuleType.");
        return null;
    }
}
