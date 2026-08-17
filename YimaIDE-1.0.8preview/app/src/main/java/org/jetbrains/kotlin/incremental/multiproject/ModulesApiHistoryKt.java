package org.jetbrains.kotlin.incremental.multiproject;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"isParentOf", "", "Ljava/nio/file/Path;", "path", "file", "Ljava/io/File;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ModulesApiHistoryKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isParentOf(Path path, File file) {
        Path path2 = Paths.get(file.getAbsolutePath(), new String[0]);
        path2.getClass();
        return isParentOf(path, path2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isParentOf(Path path, Path path2) {
        return path2.startsWith(path);
    }
}
