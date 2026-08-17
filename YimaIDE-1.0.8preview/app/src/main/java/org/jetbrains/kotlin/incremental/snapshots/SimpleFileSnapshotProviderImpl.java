package org.jetbrains.kotlin.incremental.snapshots;

import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0096\u0002¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/snapshots/SimpleFileSnapshotProviderImpl;", "Lorg/jetbrains/kotlin/incremental/snapshots/FileSnapshotProvider;", "<init>", "()V", "get", "Lorg/jetbrains/kotlin/incremental/snapshots/FileSnapshot;", "file", "Ljava/io/File;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SimpleFileSnapshotProviderImpl implements FileSnapshotProvider {
    @Override // org.jetbrains.kotlin.incremental.snapshots.FileSnapshotProvider
    public FileSnapshot get(File file) {
        file.getClass();
        file.isDirectory();
        return new FileSnapshot(file.length(), HashUtilKt.getMd5(file));
    }
}
