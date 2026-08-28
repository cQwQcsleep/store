package com.shadow.okio;

import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.okio.Path;
import com.shadow.okio.internal.FixedLengthSource;
import com.shadow.okio.internal.ZipEntry;
import com.shadow.okio.internal.ZipFilesKt;
import core.pro.android.notify.h;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ZipFileSystem extends FileSystem {
    private static final Companion Companion = new Companion(null);
    private static final Path ROOT = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null);
    private final String comment;
    private final Map<Path, ZipEntry> entries;
    private final FileSystem fileSystem;
    private final Path zipPath;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Path getROOT() {
            return ZipFileSystem.ROOT;
        }

        private Companion() {
        }
    }

    public ZipFileSystem(Path path, FileSystem fileSystem, Map<Path, ZipEntry> map, String str) {
        CloseableKt.checkNotNullParameter(path, "zipPath");
        CloseableKt.checkNotNullParameter(fileSystem, "fileSystem");
        CloseableKt.checkNotNullParameter(map, "entries");
        this.zipPath = path;
        this.fileSystem = fileSystem;
        this.entries = map;
        this.comment = str;
    }

    private final Path canonicalizeInternal(Path path) {
        return ROOT.resolve(path, true);
    }

    @Override // com.shadow.okio.FileSystem
    public Sink appendingSink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public void atomicMove(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        throw new IOException("zip file systems are read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public Path canonicalize(Path path) throws FileNotFoundException {
        CloseableKt.checkNotNullParameter(path, "path");
        Path pathCanonicalizeInternal = canonicalizeInternal(path);
        if (this.entries.containsKey(pathCanonicalizeInternal)) {
            return pathCanonicalizeInternal;
        }
        throw new FileNotFoundException(String.valueOf(path));
    }

    @Override // com.shadow.okio.FileSystem
    public void createDirectory(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        throw new IOException("zip file systems are read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public void createSymlink(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        throw new IOException("zip file systems are read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public void delete(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        throw new IOException("zip file systems are read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public List<Path> list(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        List<Path> list = list(path, true);
        CloseableKt.checkNotNull(list);
        return list;
    }

    @Override // com.shadow.okio.FileSystem
    public List<Path> listOrNull(Path path) {
        CloseableKt.checkNotNullParameter(path, "dir");
        return list(path, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005d A[Catch: all -> 0x004d, TRY_ENTER, TRY_LEAVE, TryCatch #5 {all -> 0x004d, blocks: (B:8:0x0027, B:33:0x005d, B:21:0x0049, B:9:0x0033, B:18:0x0044), top: B:59:0x0027, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006d  */
    @Override // com.shadow.okio.FileSystem
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public FileMetadata metadataOrNull(Path path) throws Throwable {
        Throwable th;
        BufferedSource bufferedSourceBuffer;
        Throwable th2;
        CloseableKt.checkNotNullParameter(path, "path");
        ZipEntry localHeader = this.entries.get(canonicalizeInternal(path));
        if (localHeader == null) {
            return null;
        }
        if (localHeader.getOffset() != -1) {
            FileHandle fileHandleOpenReadOnly = this.fileSystem.openReadOnly(this.zipPath);
            try {
                bufferedSourceBuffer = Okio.buffer(fileHandleOpenReadOnly.source(localHeader.getOffset()));
                try {
                    localHeader = ZipFilesKt.readLocalHeader(bufferedSourceBuffer, localHeader);
                } catch (Throwable th3) {
                    if (bufferedSourceBuffer != null) {
                        try {
                            bufferedSourceBuffer.close();
                        } catch (Throwable th4) {
                            LazyKt.a(th3, th4);
                        }
                    }
                    th2 = th3;
                    localHeader = null;
                }
            } catch (Throwable th5) {
                if (fileHandleOpenReadOnly != null) {
                    try {
                        fileHandleOpenReadOnly.close();
                    } catch (Throwable th6) {
                        LazyKt.a(th5, th6);
                    }
                }
                th = th5;
                localHeader = null;
            }
            if (bufferedSourceBuffer != null) {
                try {
                    bufferedSourceBuffer.close();
                    th2 = null;
                } catch (Throwable th7) {
                    th2 = th7;
                }
                if (th2 == null) {
                    throw th2;
                }
                if (fileHandleOpenReadOnly != null) {
                    try {
                        fileHandleOpenReadOnly.close();
                        th = null;
                    } catch (Throwable th8) {
                        th = th8;
                    }
                    if (th != null) {
                        throw th;
                    }
                } else {
                    th = null;
                    if (th != null) {
                    }
                }
            } else {
                th2 = null;
                if (th2 == null) {
                }
            }
        }
        return new FileMetadata(!localHeader.isDirectory(), localHeader.isDirectory(), null, localHeader.isDirectory() ? null : Long.valueOf(localHeader.getSize()), localHeader.getCreatedAtMillis$okio(), localHeader.getLastModifiedAtMillis$okio(), localHeader.getLastAccessedAtMillis$okio(), null, 128, null);
    }

    @Override // com.shadow.okio.FileSystem
    public FileHandle openReadOnly(Path path) {
        CloseableKt.checkNotNullParameter(path, "file");
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override // com.shadow.okio.FileSystem
    public FileHandle openReadWrite(Path path, boolean z, boolean z2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        throw new IOException("zip entries are not writable");
    }

    @Override // com.shadow.okio.FileSystem
    public Sink sink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        throw new IOException("zip file systems are read-only");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v7 */
    @Override // com.shadow.okio.FileSystem
    public Source source(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        ZipEntry zipEntry = this.entries.get(canonicalizeInternal(path));
        if (zipEntry == null) {
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
        FileHandle fileHandleOpenReadOnly = this.fileSystem.openReadOnly(this.zipPath);
        BufferedSource th = null;
        try {
            BufferedSource bufferedSourceBuffer = Okio.buffer(fileHandleOpenReadOnly.source(zipEntry.getOffset()));
            if (fileHandleOpenReadOnly != null) {
                try {
                    fileHandleOpenReadOnly.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            th = th;
            th = bufferedSourceBuffer;
        } catch (Throwable th3) {
            th = th3;
            if (fileHandleOpenReadOnly != null) {
                try {
                    fileHandleOpenReadOnly.close();
                } catch (Throwable th4) {
                    LazyKt.a(th, th4);
                }
            }
        }
        if (th != 0) {
            throw th;
        }
        ZipFilesKt.skipLocalHeader(th);
        return zipEntry.getCompressionMethod() == 0 ? new FixedLengthSource(th, zipEntry.getSize(), true) : new FixedLengthSource(new InflaterSource(new FixedLengthSource(th, zipEntry.getCompressedSize(), true), new Inflater(true)), zipEntry.getSize(), false);
    }

    private final List<Path> list(Path path, boolean z) throws IOException {
        ZipEntry zipEntry = this.entries.get(canonicalizeInternal(path));
        if (zipEntry != null) {
            return CollectionsKt.g(zipEntry.getChildren());
        }
        if (z) {
            throw new IOException(h.b(path, "not a directory: "));
        }
        return null;
    }
}
