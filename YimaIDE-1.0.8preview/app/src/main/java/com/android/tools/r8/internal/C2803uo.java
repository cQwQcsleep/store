package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uo, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2803uo extends RuntimeException {
    public static final /* synthetic */ int d = 0;
    public final Origin b;
    public final Position c;

    public C2803uo(RuntimeException runtimeException, Origin origin, Position position) {
        super(runtimeException);
        this.b = origin;
        this.c = position;
    }
}
