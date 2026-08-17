package com.android.tools.r8.internal;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final enum C2633sp extends EnumC2890vp {
    public C2633sp() {
        super(4, "LOWER_CASE_WITH_UNDERSCORES");
    }

    @Override // com.android.tools.r8.internal.EnumC2890vp
    public final String a(Field field) {
        return EnumC2890vp.a(field.getName(), '_').toLowerCase(Locale.ENGLISH);
    }
}
