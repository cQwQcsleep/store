package com.android.tools.r8.internal;

import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2357pd0 implements Ai0 {
    @Override // com.android.tools.r8.internal.Ai0
    public final AbstractC3220zi0 a(C0471Es c0471Es, Fj0 fj0) {
        if (fj0.a != Timestamp.class) {
            return null;
        }
        c0471Es.getClass();
        return new C2442qd0(c0471Es.a(new Fj0(Date.class)));
    }
}
