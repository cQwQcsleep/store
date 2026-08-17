package com.android.tools.r8.internal;

import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GJ {
    public static final /* synthetic */ boolean c = true;
    public final byte[] a;
    public final Path b;

    public GJ(byte[] bArr, Path path) {
        this.a = bArr;
        this.b = path;
    }

    public final byte[] a() {
        return this.a;
    }

    public final String b() {
        if (c || this.b.toString().startsWith("res/")) {
            return this.b.toString().substring(4);
        }
        x1f.a();
        return null;
    }
}
