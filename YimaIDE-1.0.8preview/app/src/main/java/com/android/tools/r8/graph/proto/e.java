package com.android.tools.r8.graph.proto;

import com.android.tools.r8.internal.InterfaceC2386px;
import com.android.tools.r8.internal.J;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class e extends d {
    public static final /* synthetic */ boolean b = true;
    public final InterfaceC2386px a;

    public e(J j) {
        if (b || j.b == -1) {
            this.a = j;
        } else {
            x1f.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.graph.proto.d
    public final int a(int i) {
        int i2 = this.a.get(i);
        return i2 >= 0 ? i2 : i;
    }
}
