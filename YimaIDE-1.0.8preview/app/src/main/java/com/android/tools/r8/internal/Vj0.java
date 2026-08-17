package com.android.tools.r8.internal;

import com.android.tools.r8.TextOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Vj0 implements TextOutputStream {
    public final OutputStream a;

    public Vj0(Path path) {
        this.a = Files.newOutputStream(path, new OpenOption[0]);
    }

    @Override // com.android.tools.r8.TextOutputStream
    public final Charset getCharset() {
        return StandardCharsets.UTF_8;
    }

    @Override // com.android.tools.r8.TextOutputStream
    public final OutputStream getOutputStream() {
        return this.a;
    }
}
