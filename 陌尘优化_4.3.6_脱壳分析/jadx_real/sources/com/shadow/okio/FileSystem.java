package com.shadow.okio;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.functions.Function1;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.sequences.Sequence;
import com.shadow.okio.Path;
import com.shadow.okio.internal.ResourceFileSystem;
import java.io.IOException;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class FileSystem {
    public static final Companion Companion = new Companion(null);
    public static final FileSystem RESOURCES;
    public static final FileSystem SYSTEM;
    public static final Path SYSTEM_TEMPORARY_DIRECTORY;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FileSystem get(java.nio.file.FileSystem fileSystem) {
            CloseableKt.checkNotNullParameter(fileSystem, "<this>");
            return new NioFileSystemWrappingFileSystem(fileSystem);
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* renamed from: -write$default, reason: not valid java name */
    public static /* synthetic */ Object m168write$default(FileSystem fileSystem, Path path, boolean z, Function1 function1, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: write");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        CloseableKt.checkNotNullParameter(path, "file");
        CloseableKt.checkNotNullParameter(function1, "writerAction");
        BufferedSink bufferedSinkBuffer = Okio.buffer(fileSystem.sink(path, z));
        Object th = null;
        try {
            Object objInvoke = function1.invoke(bufferedSinkBuffer);
            if (bufferedSinkBuffer != null) {
                try {
                    bufferedSinkBuffer.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            th = th;
            th = objInvoke;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedSinkBuffer != null) {
                try {
                    bufferedSinkBuffer.close();
                } catch (Throwable th4) {
                    LazyKt.a(th, th4);
                }
            }
        }
        if (th == 0) {
            return th;
        }
        throw th;
    }

    static {
        FileSystem jvmSystemFileSystem;
        try {
            Class.forName("java.nio.file.Files");
            jvmSystemFileSystem = new NioSystemFileSystem();
        } catch (ClassNotFoundException unused) {
            jvmSystemFileSystem = new JvmSystemFileSystem();
        }
        SYSTEM = jvmSystemFileSystem;
        Path.Companion companion = Path.Companion;
        String property = System.getProperty("java.io.tmpdir");
        CloseableKt.checkNotNullExpressionValue(property, "getProperty(...)");
        SYSTEM_TEMPORARY_DIRECTORY = Path.Companion.get$default(companion, property, false, 1, (Object) null);
        ClassLoader classLoader = ResourceFileSystem.class.getClassLoader();
        CloseableKt.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
        RESOURCES = new ResourceFileSystem(classLoader, false, null, 4, null);
    }

    public static /* synthetic */ Sink appendingSink$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: appendingSink");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return fileSystem.appendingSink(path, z);
    }

    public static /* synthetic */ void createDirectories$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectories");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        fileSystem.createDirectories(path, z);
    }

    public static /* synthetic */ void createDirectory$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectory");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        fileSystem.createDirectory(path, z);
    }

    public static /* synthetic */ void delete$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        fileSystem.delete(path, z);
    }

    public static /* synthetic */ void deleteRecursively$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteRecursively");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        fileSystem.deleteRecursively(path, z);
    }

    public static final FileSystem get(java.nio.file.FileSystem fileSystem) {
        return Companion.get(fileSystem);
    }

    public static /* synthetic */ Sequence listRecursively$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listRecursively");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return fileSystem.listRecursively(path, z);
    }

    public static /* synthetic */ FileHandle openReadWrite$default(FileSystem fileSystem, Path path, boolean z, boolean z2, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openReadWrite");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return fileSystem.openReadWrite(path, z, z2);
    }

    public static /* synthetic */ Sink sink$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return fileSystem.sink(path, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* renamed from: -read, reason: not valid java name */
    public final <T> T m169read(Path path, kotlin.jvm.functions.Function1<? super BufferedSource, ? extends T> function1) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        CloseableKt.checkNotNullParameter(function1, "readerAction");
        BufferedSource bufferedSourceBuffer = Okio.buffer(source(path));
        T th = null;
        try {
            ?? Invoke = function1.invoke(bufferedSourceBuffer);
            if (bufferedSourceBuffer != null) {
                try {
                    bufferedSourceBuffer.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            T t = th;
            th = Invoke;
            th = t;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedSourceBuffer != null) {
                try {
                    bufferedSourceBuffer.close();
                } catch (Throwable th4) {
                    LazyKt.a(th, th4);
                }
            }
        }
        if (th == 0) {
            return th;
        }
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* renamed from: -write, reason: not valid java name */
    public final <T> T m170write(Path path, boolean z, kotlin.jvm.functions.Function1<? super BufferedSink, ? extends T> function1) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        CloseableKt.checkNotNullParameter(function1, "writerAction");
        BufferedSink bufferedSinkBuffer = Okio.buffer(sink(path, z));
        T th = null;
        try {
            ?? Invoke = function1.invoke(bufferedSinkBuffer);
            if (bufferedSinkBuffer != null) {
                try {
                    bufferedSinkBuffer.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            th = th;
            th = Invoke;
        } catch (Throwable th3) {
            th = th3;
            if (bufferedSinkBuffer != null) {
                try {
                    bufferedSinkBuffer.close();
                } catch (Throwable th4) {
                    LazyKt.a(th, th4);
                }
            }
        }
        if (th == 0) {
            return th;
        }
        throw th;
    }

    public final Sink appendingSink(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        return appendingSink(path, false);
    }

    public abstract Sink appendingSink(Path path, boolean z) throws IOException;

    public abstract void atomicMove(Path path, Path path2) throws IOException;

    public abstract Path canonicalize(Path path) throws IOException;

    public void copy(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        com.shadow.okio.internal.FileSystem.commonCopy(this, path, path2);
    }

    public final void createDirectories(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        com.shadow.okio.internal.FileSystem.commonCreateDirectories(this, path, z);
    }

    public final void createDirectory(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        createDirectory(path, false);
    }

    public abstract void createDirectory(Path path, boolean z) throws IOException;

    public abstract void createSymlink(Path path, Path path2) throws IOException;

    public final void delete(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        delete(path, false);
    }

    public abstract void delete(Path path, boolean z) throws IOException;

    public void deleteRecursively(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "fileOrDirectory");
        com.shadow.okio.internal.FileSystem.commonDeleteRecursively(this, path, z);
    }

    public final boolean exists(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        return com.shadow.okio.internal.FileSystem.commonExists(this, path);
    }

    public abstract List<Path> list(Path path) throws IOException;

    public abstract List<Path> listOrNull(Path path);

    public kotlin.sequences.Sequence<Path> listRecursively(Path path, boolean z) {
        CloseableKt.checkNotNullParameter(path, "dir");
        return com.shadow.okio.internal.FileSystem.commonListRecursively(this, path, z);
    }

    public final FileMetadata metadata(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        return com.shadow.okio.internal.FileSystem.commonMetadata(this, path);
    }

    public abstract FileMetadata metadataOrNull(Path path) throws IOException;

    public abstract FileHandle openReadOnly(Path path) throws IOException;

    public final FileHandle openReadWrite(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        return openReadWrite(path, false, false);
    }

    public abstract FileHandle openReadWrite(Path path, boolean z, boolean z2) throws IOException;

    public final Sink sink(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        return sink(path, false);
    }

    public abstract Sink sink(Path path, boolean z) throws IOException;

    public abstract Source source(Path path) throws IOException;

    public final void createDirectories(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        createDirectories(path, false);
    }

    public final void deleteRecursively(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "fileOrDirectory");
        deleteRecursively(path, false);
    }

    public final kotlin.sequences.Sequence<Path> listRecursively(Path path) {
        CloseableKt.checkNotNullParameter(path, "dir");
        return listRecursively(path, false);
    }
}
