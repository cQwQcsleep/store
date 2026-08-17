package com.android.tools.r8.internal;

import com.android.tools.r8.TextInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Uj0 implements TextInputStream {
    public final InputStream a;

    public Uj0(String str) {
        this(new ByteArrayInputStream(str.getBytes()));
    }

    @Override // com.android.tools.r8.TextInputStream
    public final Charset getCharset() {
        return StandardCharsets.UTF_8;
    }

    @Override // com.android.tools.r8.TextInputStream
    public final InputStream getInputStream() {
        return this.a;
    }

    public Uj0(Path path) throws IOException {
        this(Files.newInputStream(path, new OpenOption[0]));
    }

    public Uj0(InputStream inputStream) {
        this.a = inputStream;
    }
}
