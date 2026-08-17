package com.android.tools.r8.internal;

import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Kl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0620Kl {
    public final H50 a;
    public boolean b;
    public final WO c;

    public C0620Kl(Path path, H10 h10) {
        KB.c(path, "path");
        this.a = h10;
        this.c = new WO();
    }

    public final boolean a() {
        if (this.b) {
            WO wo = this.c;
            if (wo.a && KB.a((Object) wo.b, (Object) "<clinit>")) {
                return true;
            }
        }
        return this.b && !this.c.a;
    }
}
