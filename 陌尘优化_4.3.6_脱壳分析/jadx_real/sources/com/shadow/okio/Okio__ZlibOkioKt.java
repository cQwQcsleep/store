package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.internal.ZipFilesKt;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
final /* synthetic */ class Okio__ZlibOkioKt {
    public static final FileSystem openZip(FileSystem fileSystem, Path path) throws IOException {
        CloseableKt.checkNotNullParameter(fileSystem, "<this>");
        CloseableKt.checkNotNullParameter(path, "zipPath");
        return ZipFilesKt.openZip$default(path, fileSystem, null, 4, null);
    }
}
