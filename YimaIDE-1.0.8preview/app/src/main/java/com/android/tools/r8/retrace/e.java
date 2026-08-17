package com.android.tools.r8.retrace;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class e implements ProguardMapProducer {
    public final /* synthetic */ Path a;

    public e(Path path) {
        this.a = path;
    }

    @Override // com.android.tools.r8.retrace.ProguardMapProducer
    public final InputStream get() {
        return new BufferedInputStream(new FileInputStream(this.a.toFile()));
    }

    @Override // com.android.tools.r8.retrace.ProguardMapProducer
    public final Path getPath() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.ProguardMapProducer
    public final boolean isFileBacked() {
        return true;
    }
}
