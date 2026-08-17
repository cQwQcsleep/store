package com.android.tools.r8.internal;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3029xV {
    public final Origin a;
    public final Position b;

    public C3029xV(Origin origin, Position position) {
        this.a = origin;
        this.b = position;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C3029xV) {
            C3029xV c3029xV = (C3029xV) obj;
            if (Objects.equals(c3029xV.a, this.a) && Objects.equals(c3029xV.b, this.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 13);
    }
}
