package com.android.tools.r8.internal;

import java.lang.reflect.Field;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final enum C2378pp extends EnumC2890vp {
    public C2378pp() {
        super(1, "UPPER_CAMEL_CASE");
    }

    @Override // com.android.tools.r8.internal.EnumC2890vp
    public final String a(Field field) {
        return EnumC2890vp.a(field.getName());
    }
}
