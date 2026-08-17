package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ClasspathSnapshotFiles;", "Ljava/io/Serializable;", "currentClasspathEntrySnapshotFiles", "", "Ljava/io/File;", "classpathSnapshotDir", "<init>", "(Ljava/util/List;Ljava/io/File;)V", "getCurrentClasspathEntrySnapshotFiles", "()Ljava/util/List;", "shrunkPreviousClasspathSnapshotFile", "getShrunkPreviousClasspathSnapshotFile", "()Ljava/io/File;", "Companion", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ClasspathSnapshotFiles implements Serializable {
    private static final long serialVersionUID = 0;
    private final List<File> currentClasspathEntrySnapshotFiles;
    private final File shrunkPreviousClasspathSnapshotFile;

    /* JADX WARN: Multi-variable type inference failed */
    public ClasspathSnapshotFiles(List<? extends File> list, File file) {
        list.getClass();
        file.getClass();
        this.currentClasspathEntrySnapshotFiles = list;
        this.shrunkPreviousClasspathSnapshotFile = new File(file, "shrunk-classpath-snapshot.bin");
    }

    public final List<File> getCurrentClasspathEntrySnapshotFiles() {
        return this.currentClasspathEntrySnapshotFiles;
    }

    public final File getShrunkPreviousClasspathSnapshotFile() {
        return this.shrunkPreviousClasspathSnapshotFile;
    }
}
