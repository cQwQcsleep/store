package com.android.tools.r8.internal;

import java.lang.reflect.Field;

/* JADX INFO: renamed from: com.android.tools.r8.internal.op, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final enum C2292op extends EnumC2890vp {
    public C2292op() {
        super(0, "IDENTITY");
    }

    @Override // com.android.tools.r8.internal.EnumC2890vp
    public final String a(Field field) {
        return field.getName();
    }
}
