package org.jetbrains.kotlin.incremental.storage;

import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/FileLocations;", "", "rootProjectDir", "Ljava/io/File;", "buildDir", "<init>", "(Ljava/io/File;Ljava/io/File;)V", "getRootProjectDir", "()Ljava/io/File;", "getBuildDir", "getRelocatablePathConverterForSourceFiles", "Lorg/jetbrains/kotlin/incremental/storage/RelocatableFileToPathConverter;", "getRelocatablePathConverterForOutputFiles", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FileLocations {
    private final File buildDir;
    private final File rootProjectDir;

    public FileLocations(File file, File file2) {
        file.getClass();
        file2.getClass();
        this.rootProjectDir = file;
        this.buildDir = file2;
    }

    public final File getBuildDir() {
        return this.buildDir;
    }

    public final RelocatableFileToPathConverter getRelocatablePathConverterForOutputFiles() {
        return new RelocatableFileToPathConverter(this.buildDir);
    }

    public final RelocatableFileToPathConverter getRelocatablePathConverterForSourceFiles() {
        return new RelocatableFileToPathConverter(this.rootProjectDir);
    }

    public final File getRootProjectDir() {
        return this.rootProjectDir;
    }
}
