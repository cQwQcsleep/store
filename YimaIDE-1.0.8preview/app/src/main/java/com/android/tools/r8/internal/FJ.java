package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FJ {
    public static final /* synthetic */ boolean g = true;
    public final HashMap a = new HashMap();
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public final HashMap e = new HashMap();
    public List f;

    public final IJ a() {
        if (g || !(this.d == null || this.e == null)) {
            return new IJ(this.a, this.b, this.d, this.e, this.c, this.f);
        }
        x1f.a();
        return null;
    }

    public final FJ a(String str, byte[] bArr) {
        this.a.put(str, bArr);
        return this;
    }
}
