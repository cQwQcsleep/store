package com.android.tools.r8.internal;

import java.lang.reflect.Field;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1185bp {
    public final Field a;

    public C1185bp(Field field) {
        Objects.requireNonNull(field);
        this.a = field;
    }

    public final String toString() {
        return this.a.toString();
    }
}
