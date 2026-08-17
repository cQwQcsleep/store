package org.jetbrains.kotlin.ir.backend.js.ic;

import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.io.FilesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/IncrementalCacheGuard;", "", "cacheDir", "", "<init>", "(Ljava/lang/String;)V", "cacheRoot", "Ljava/io/File;", "guardFile", "acquire", "Lorg/jetbrains/kotlin/ir/backend/js/ic/IncrementalCacheGuard$AcquireStatus;", "tryAcquire", "", "release", "AcquireStatus", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IncrementalCacheGuard {
    private final File cacheRoot;
    private final File guardFile;

    public IncrementalCacheGuard(String str) {
        str.getClass();
        File file = new File(str);
        this.cacheRoot = file;
        this.guardFile = FilesKt.resolve(file, "cache.guard");
    }

    public final AcquireStatus acquire() throws IOException {
        if (!this.guardFile.exists()) {
            tryAcquire();
            return AcquireStatus.OK;
        }
        FilesKt.deleteRecursively(this.cacheRoot);
        tryAcquire();
        return AcquireStatus.CACHE_CLEARED;
    }

    public final void release() {
        this.guardFile.delete();
    }

    public final void tryAcquire() throws IOException {
        this.cacheRoot.mkdirs();
        this.guardFile.createNewFile();
    }
}
