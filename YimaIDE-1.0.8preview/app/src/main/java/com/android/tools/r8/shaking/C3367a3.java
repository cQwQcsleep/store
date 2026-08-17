package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.K7;
import com.android.tools.r8.origin.Origin;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.a3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3367a3 implements Z2 {
    public final byte[] a;
    public final Origin b;

    public C3367a3(Origin origin, InputStream inputStream) {
        this.a = K7.a(inputStream);
        this.b = origin;
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final Path a() {
        return null;
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final String get() {
        return new String(this.a, StandardCharsets.UTF_8);
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final String getName() {
        return this.b.toString();
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final Origin getOrigin() {
        return this.b;
    }
}
