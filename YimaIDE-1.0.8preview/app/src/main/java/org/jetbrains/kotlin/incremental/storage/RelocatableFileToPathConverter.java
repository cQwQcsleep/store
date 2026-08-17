package org.jetbrains.kotlin.incremental.storage;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/RelocatableFileToPathConverter;", "Lorg/jetbrains/kotlin/incremental/storage/FileToPathConverter;", "baseDir", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "toPath", "", "file", "toFile", "path", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RelocatableFileToPathConverter implements FileToPathConverter {
    private final File baseDir;

    public RelocatableFileToPathConverter(File file) {
        file.getClass();
        this.baseDir = file;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.FileToPathConverter
    public File toFile(String path) {
        path.getClass();
        return FilesKt.normalize(FilesKt.resolve(this.baseDir, path));
    }

    @Override // org.jetbrains.kotlin.incremental.storage.FileToPathConverter
    public String toPath(File file) {
        file.getClass();
        if (file.isAbsolute()) {
            return FilesKt.getInvariantSeparatorsPath(FilesKt.relativeTo(file, this.baseDir));
        }
        cpa.a("Expected absolute path but found relative path: ", file.getPath());
        return null;
    }
}
