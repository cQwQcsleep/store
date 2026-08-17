package com.android.tools.r8.internal;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final enum C2548rp extends EnumC2890vp {
    public C2548rp() {
        super(3, "UPPER_CASE_WITH_UNDERSCORES");
    }

    @Override // com.android.tools.r8.internal.EnumC2890vp
    public final String a(Field field) {
        return EnumC2890vp.a(field.getName(), '_').toUpperCase(Locale.ENGLISH);
    }
}
