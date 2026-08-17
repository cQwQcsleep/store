package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.ProguardMapProducer;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ProguardMapProducer {
    /* JADX INFO: Access modifiers changed from: private */
    static InputStream a(byte[][] bArr) {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length;
        }
        byte[] bArr3 = new byte[length];
        int length2 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
            length2 += bArr4.length;
        }
        return new ByteArrayInputStream(bArr3);
    }

    static ProguardMapProducer fromBytes(final byte[]... bArr) {
        return new ProguardMapProducer() { // from class: xeb
            @Override // com.android.tools.r8.retrace.ProguardMapProducer
            public final InputStream get() {
                return ProguardMapProducer.a(bArr);
            }
        };
    }

    static ProguardMapProducer fromPath(Path path) {
        return new e(path);
    }

    static ProguardMapProducer fromString(final String str) {
        return new ProguardMapProducer() { // from class: web
            @Override // com.android.tools.r8.retrace.ProguardMapProducer
            public final InputStream get() {
                return ProguardMapProducer.a(str);
            }
        };
    }

    InputStream get() throws IOException;

    default Path getPath() throws FileNotFoundException {
        return null;
    }

    default boolean isFileBacked() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static /* synthetic */ InputStream a(String str) {
        return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
    }
}
