package com.android.tools.r8.internal;

import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.internal.um, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2800um extends AbstractC2886vm {
    public final /* synthetic */ Path a;

    public C2800um(Path path) {
        this.a = path;
    }

    @Override // com.android.tools.r8.internal.AbstractC2972wm
    public final Path b() {
        return this.a.resolve("dump" + System.nanoTime() + ".zip");
    }

    @Override // com.android.tools.r8.internal.AbstractC2972wm
    public final boolean c() {
        return false;
    }
}
