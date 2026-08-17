package com.android.tools.r8.internal;

import java.lang.reflect.Field;

/* JADX INFO: renamed from: com.android.tools.r8.internal.qp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final enum C2463qp extends EnumC2890vp {
    public C2463qp() {
        super(2, "UPPER_CAMEL_CASE_WITH_SPACES");
    }

    @Override // com.android.tools.r8.internal.EnumC2890vp
    public final String a(Field field) {
        return EnumC2890vp.a(EnumC2890vp.a(field.getName(), ' '));
    }
}
