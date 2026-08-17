package org.jetbrains.kotlin.incremental.classpathDiff.impl;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFile;", "", "classRoot", "Ljava/io/File;", "relativePath", "", "<init>", "(Ljava/io/File;Ljava/lang/String;)V", "getClassRoot", "()Ljava/io/File;", "unixStyleRelativePath", "getUnixStyleRelativePath", "()Ljava/lang/String;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClassFile {
    private final File classRoot;
    private final String unixStyleRelativePath;

    public ClassFile(File file, String str) {
        file.getClass();
        str.getClass();
        this.classRoot = file;
        this.unixStyleRelativePath = FilesKt.getInvariantSeparatorsPath(new File(str));
    }

    public final File getClassRoot() {
        return this.classRoot;
    }

    public final String getUnixStyleRelativePath() {
        return this.unixStyleRelativePath;
    }
}
