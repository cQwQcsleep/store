package com.android.tools.r8.internal;

import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class RE {
    public final ArrayList a = new ArrayList();

    public final SE a() {
        if (!this.a.isEmpty()) {
            return new SE(this.a);
        }
        defpackage.l0.a("Invalid empty consequent set");
        return null;
    }

    public final RE a(C2159nH c2159nH) {
        this.a.add(c2159nH);
        return this;
    }
}
