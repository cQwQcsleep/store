package com.android.tools.r8.internal;

import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class F7 implements Y7 {
    public static final /* synthetic */ boolean b = true;
    public final ByteArrayOutputStream a = new ByteArrayOutputStream();

    @Override // com.android.tools.r8.internal.Y7
    public final void a(int i) {
        if (!b) {
            boolean z = W7.a;
            if (i < 0 || i > 255) {
                x1f.a();
                return;
            }
        }
        this.a.write(i);
    }
}
