package com.android.tools.r8.androidapi;

import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.EnumC3077y2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class g implements f {
    public static final g b = new g();
    public static final /* synthetic */ boolean c = true;

    @Override // com.android.tools.r8.androidapi.f
    public final boolean G() {
        return true;
    }

    @Override // com.android.tools.r8.androidapi.f
    public final AbstractC2173nV a(EnumC3077y2 enumC3077y2) {
        if (c) {
            return AbstractC2173nV.c;
        }
        x01.a("Cannot compute relationship for not set");
        return null;
    }

    @Override // com.android.tools.r8.androidapi.f
    public final AbstractC2173nV b(EnumC3077y2 enumC3077y2) {
        if (c) {
            return AbstractC2173nV.c;
        }
        x01.a("Cannot compute relationship for not set");
        return null;
    }

    @Override // com.android.tools.r8.androidapi.f
    public final AbstractC2173nV d(f fVar) {
        if (c) {
            return AbstractC2173nV.c;
        }
        x01.a("Cannot compute relationship for not set");
        return null;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }
}
