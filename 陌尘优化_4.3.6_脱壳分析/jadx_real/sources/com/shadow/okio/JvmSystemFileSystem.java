package com.shadow.okio;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.Path;
import core.pro.android.notify.h;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public class JvmSystemFileSystem extends FileSystem {
    private final void requireCreate(Path path) throws IOException {
        if (exists(path)) {
            throw new IOException(path + " already exists.");
        }
    }

    private final void requireExist(Path path) throws IOException {
        if (exists(path)) {
            return;
        }
        throw new IOException(path + " doesn't exist.");
    }

    @Override // com.shadow.okio.FileSystem
    public Sink appendingSink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        if (z) {
            requireExist(path);
        }
        return Okio.sink(path.toFile(), true);
    }

    @Override // com.shadow.okio.FileSystem
    public void atomicMove(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        if (path.toFile().renameTo(path2.toFile())) {
            return;
        }
        throw new IOException("failed to move " + path + " to " + path2);
    }

    @Override // com.shadow.okio.FileSystem
    public Path canonicalize(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        File canonicalFile = path.toFile().getCanonicalFile();
        if (canonicalFile.exists()) {
            return Path.Companion.get$default(Path.Companion, canonicalFile, false, 1, (Object) null);
        }
        throw new FileNotFoundException("no such file");
    }

    @Override // com.shadow.okio.FileSystem
    public void createDirectory(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        if (path.toFile().mkdir()) {
            return;
        }
        FileMetadata fileMetadataMetadataOrNull = metadataOrNull(path);
        if (fileMetadataMetadataOrNull == null || !fileMetadataMetadataOrNull.isDirectory()) {
            throw new IOException(h.b(path, "failed to create directory: "));
        }
        if (z) {
            throw new IOException(path + " already exists.");
        }
    }

    @Override // com.shadow.okio.FileSystem
    public void createSymlink(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        throw new IOException("unsupported");
    }

    @Override // com.shadow.okio.FileSystem
    public void delete(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        if (Thread.interrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        File file = path.toFile();
        if (file.delete()) {
            return;
        }
        if (file.exists()) {
            throw new IOException(h.b(path, "failed to delete "));
        }
        if (z) {
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
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

    @Override // com.shadow.okio.FileSystem
    public FileMetadata metadataOrNull(Path path) {
        CloseableKt.checkNotNullParameter(path, "path");
        File file = path.toFile();
        boolean zIsFile = file.isFile();
        boolean zIsDirectory = file.isDirectory();
        long jLastModified = file.lastModified();
        long length = file.length();
        if (!zIsFile && !zIsDirectory && jLastModified == 0 && length == 0 && !file.exists()) {
            return null;
        }
        return new FileMetadata(zIsFile, zIsDirectory, null, Long.valueOf(length), null, Long.valueOf(jLastModified), null, null, 128, null);
    }

    @Override // com.shadow.okio.FileSystem
    public FileHandle openReadOnly(Path path) {
        CloseableKt.checkNotNullParameter(path, "file");
        return new JvmFileHandle(false, new RandomAccessFile(path.toFile(), "r"));
    }

    @Override // com.shadow.okio.FileSystem
    public FileHandle openReadWrite(Path path, boolean z, boolean z2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        if (z && z2) {
            throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.");
        }
        if (z) {
            requireCreate(path);
        }
        if (z2) {
            requireExist(path);
        }
        return new JvmFileHandle(true, new RandomAccessFile(path.toFile(), "rw"));
    }

    @Override // com.shadow.okio.FileSystem
    public Sink sink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        if (z) {
            requireCreate(path);
        }
        return Okio__JvmOkioKt.sink$default(path.toFile(), false, 1, null);
    }

    @Override // com.shadow.okio.FileSystem
    public Source source(Path path) {
        CloseableKt.checkNotNullParameter(path, "file");
        return Okio.source(path.toFile());
    }

    public String toString() {
        return "JvmSystemFileSystem";
    }

    private final List<Path> list(Path path, boolean z) throws IOException {
        File file = path.toFile();
        String[] list = file.list();
        if (list == null) {
            if (!z) {
                return null;
            }
            if (file.exists()) {
                throw new IOException(h.b(path, "failed to list "));
            }
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            CloseableKt.checkNotNull(str);
            arrayList.add(path.resolve(str));
        }
        CollectionsKt.f(arrayList);
        return arrayList;
    }
}
