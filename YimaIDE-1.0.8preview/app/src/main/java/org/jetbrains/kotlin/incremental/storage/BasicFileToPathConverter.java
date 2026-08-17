package org.jetbrains.kotlin.incremental.storage;

import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/BasicFileToPathConverter;", "Lorg/jetbrains/kotlin/incremental/storage/FileToPathConverter;", "<init>", "()V", "toPath", "", "file", "Ljava/io/File;", "toFile", "path", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BasicFileToPathConverter implements FileToPathConverter {
    public static final BasicFileToPathConverter INSTANCE = new BasicFileToPathConverter();

    private BasicFileToPathConverter() {
    }

    @Override // org.jetbrains.kotlin.incremental.storage.FileToPathConverter
    public File toFile(String path) {
        path.getClass();
        return new File(path);
    }

    @Override // org.jetbrains.kotlin.incremental.storage.FileToPathConverter
    public String toPath(File file) {
        file.getClass();
        String path = file.getPath();
        path.getClass();
        return path;
    }
}
