package com.shadow.okio;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.builders.ListBuilder;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.Reflection;
import com.shadow.okio.Path;
import core.pro.android.notify.h;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class NioFileSystemWrappingFileSystem extends NioSystemFileSystem {
    private final java.nio.file.FileSystem nioFileSystem;

    public NioFileSystemWrappingFileSystem(java.nio.file.FileSystem fileSystem) {
        CloseableKt.checkNotNullParameter(fileSystem, "nioFileSystem");
        this.nioFileSystem = fileSystem;
    }

    private final java.nio.file.Path resolve(Path path) {
        java.nio.file.Path path2 = this.nioFileSystem.getPath(path.toString(), new String[0]);
        CloseableKt.checkNotNullExpressionValue(path2, "getPath(...)");
        return path2;
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public Sink appendingSink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        ListBuilder listBuilder = new ListBuilder();
        listBuilder.add(StandardOpenOption.APPEND);
        if (!z) {
            listBuilder.add(StandardOpenOption.CREATE);
        }
        List listBuild = listBuilder.build();
        java.nio.file.Path pathResolve = resolve(path);
        StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) listBuild.toArray(new StandardOpenOption[0]);
        OpenOption[] openOptionArr = (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length);
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(pathResolve, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        CloseableKt.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        return Okio.sink(outputStreamNewOutputStream);
    }

    @Override // com.shadow.okio.NioSystemFileSystem, com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public void atomicMove(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        try {
            CloseableKt.checkNotNullExpressionValue(Files.move(resolve(path), resolve(path2), (CopyOption[]) Arrays.copyOf(new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING}, 2)), "move(...)");
        } catch (UnsupportedOperationException unused) {
            throw new IOException("atomic move not supported");
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public Path canonicalize(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        try {
            Path.Companion companion = Path.Companion;
            java.nio.file.Path realPath = resolve(path).toRealPath(new LinkOption[0]);
            CloseableKt.checkNotNullExpressionValue(realPath, "toRealPath(...)");
            return Path.Companion.get$default(companion, realPath, false, 1, (Object) null);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void createDirectory(Path path, boolean z) throws IOException {
        boolean z2;
        CloseableKt.checkNotNullParameter(path, "dir");
        FileMetadata fileMetadataMetadataOrNull = metadataOrNull(path);
        if (fileMetadataMetadataOrNull != null) {
            z2 = fileMetadataMetadataOrNull.isDirectory();
        }
        if (z2 && z) {
            throw new IOException(path + " already exists.");
        }
        try {
            CloseableKt.checkNotNullExpressionValue(Files.createDirectory(resolve(path), (FileAttribute[]) Arrays.copyOf(new FileAttribute[0], 0)), "createDirectory(...)");
        } catch (IOException e) {
            if (!z2) {
                throw new IOException(h.b(path, "failed to create directory: "), e);
            }
        }
    }

    @Override // com.shadow.okio.NioSystemFileSystem, com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public void createSymlink(Path path, Path path2) {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        CloseableKt.checkNotNullExpressionValue(Files.createSymbolicLink(resolve(path), resolve(path2), (FileAttribute[]) Arrays.copyOf(new FileAttribute[0], 0)), "createSymbolicLink(...)");
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public void delete(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        if (Thread.interrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        java.nio.file.Path pathResolve = resolve(path);
        try {
            Files.delete(pathResolve);
        } catch (NoSuchFileException unused) {
            if (z) {
                throw new FileNotFoundException(h.b(path, "no such file: "));
            }
        } catch (IOException unused2) {
            if (Files.exists(pathResolve, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                throw new IOException(h.b(path, "failed to delete "));
            }
        }
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public List<Path> list(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        List<Path> list = list(path, true);
        CloseableKt.checkNotNull(list);
        return list;
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public List<Path> listOrNull(Path path) {
        CloseableKt.checkNotNullParameter(path, "dir");
        return list(path, false);
    }

    @Override // com.shadow.okio.NioSystemFileSystem, com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public FileMetadata metadataOrNull(Path path) {
        CloseableKt.checkNotNullParameter(path, "path");
        return metadataOrNull(resolve(path));
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public FileHandle openReadOnly(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        try {
            FileChannel fileChannelOpen = FileChannel.open(resolve(path), StandardOpenOption.READ);
            CloseableKt.checkNotNull(fileChannelOpen);
            return new NioFileSystemFileHandle(false, fileChannelOpen);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public FileHandle openReadWrite(Path path, boolean z, boolean z2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        if (z && z2) {
            throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.");
        }
        ListBuilder listBuilder = new ListBuilder();
        listBuilder.add(StandardOpenOption.READ);
        listBuilder.add(StandardOpenOption.WRITE);
        if (z) {
            listBuilder.add(StandardOpenOption.CREATE_NEW);
        } else if (!z2) {
            listBuilder.add(StandardOpenOption.CREATE);
        }
        List listBuild = listBuilder.build();
        try {
            java.nio.file.Path pathResolve = resolve(path);
            StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) listBuild.toArray(new StandardOpenOption[0]);
            FileChannel fileChannelOpen = FileChannel.open(pathResolve, (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length));
            CloseableKt.checkNotNull(fileChannelOpen);
            return new NioFileSystemFileHandle(true, fileChannelOpen);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public Sink sink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        ListBuilder listBuilder = new ListBuilder();
        if (z) {
            listBuilder.add(StandardOpenOption.CREATE_NEW);
        }
        List listBuild = listBuilder.build();
        try {
            java.nio.file.Path pathResolve = resolve(path);
            StandardOpenOption[] standardOpenOptionArr = (StandardOpenOption[]) listBuild.toArray(new StandardOpenOption[0]);
            OpenOption[] openOptionArr = (OpenOption[]) Arrays.copyOf(standardOpenOptionArr, standardOpenOptionArr.length);
            OutputStream outputStreamNewOutputStream = Files.newOutputStream(pathResolve, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
            CloseableKt.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
            return Okio.sink(outputStreamNewOutputStream);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
    }

    @Override // com.shadow.okio.JvmSystemFileSystem, com.shadow.okio.FileSystem
    public Source source(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        try {
            InputStream inputStreamNewInputStream = Files.newInputStream(resolve(path), (OpenOption[]) Arrays.copyOf(new OpenOption[0], 0));
            CloseableKt.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
            return Okio.source(inputStreamNewInputStream);
        } catch (NoSuchFileException unused) {
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
    }

    @Override // com.shadow.okio.NioSystemFileSystem, com.shadow.okio.JvmSystemFileSystem
    public String toString() {
        String simpleName = Reflection.getOrCreateKotlinClass(this.nioFileSystem.getClass()).getSimpleName();
        CloseableKt.checkNotNull(simpleName);
        return simpleName;
    }

    private final List<Path> list(Path path, boolean z) throws IOException {
        java.nio.file.Path pathResolve = resolve(path);
        try {
            CloseableKt.checkNotNullParameter(pathResolve, "<this>");
            DirectoryStream<java.nio.file.Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(pathResolve, "*");
            try {
                CloseableKt.checkNotNull(directoryStreamNewDirectoryStream);
                List listG = CollectionsKt.g(directoryStreamNewDirectoryStream);
                CloseableKt.closeFinally(directoryStreamNewDirectoryStream, null);
                ArrayList arrayList = new ArrayList();
                Iterator it = listG.iterator();
                while (it.hasNext()) {
                    arrayList.add(Path.Companion.get$default(Path.Companion, (java.nio.file.Path) it.next(), false, 1, (Object) null));
                }
                CollectionsKt.f(arrayList);
                return arrayList;
            } finally {
            }
        } catch (Exception unused) {
            if (!z) {
                return null;
            }
            if (Files.exists(pathResolve, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0))) {
                throw new IOException(h.b(path, "failed to list "));
            }
            throw new FileNotFoundException(h.b(path, "no such file: "));
        }
    }
}
