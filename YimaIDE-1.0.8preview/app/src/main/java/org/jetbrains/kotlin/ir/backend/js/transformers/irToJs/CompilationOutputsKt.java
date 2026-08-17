package org.jetbrains.kotlin.ir.backend.js.transformers.irToJs;

import java.io.File;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.io.FilesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\u0002\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\b"}, d2 = {"copyModificationTimeFrom", "", "Ljava/io/File;", "from", "asSourceMappingUrl", "", "writeIfNotNull", "data", "org.jetbrains.kotlin:backend.js"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CompilationOutputsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String asSourceMappingUrl(File file) {
        return "\n//# sourceMappingURL=" + file.getName() + '\n';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void copyModificationTimeFrom(File file, File file2) {
        long jLastModified = file2.lastModified();
        if (jLastModified > 0) {
            file.setLastModified(jLastModified);
        }
    }

    public static final void writeIfNotNull(File file, String str) {
        file.getClass();
        if (str != null) {
            FilesKt.writeText$default(file, str, (Charset) null, 2, (Object) null);
        } else {
            file.delete();
        }
    }
}
