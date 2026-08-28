package com.shadow.okio;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.Reflection;
import com.shadow.kotlin.sequences.TransformingSequence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class ForwardingFileSystem extends FileSystem {
    private final FileSystem delegate;

    public ForwardingFileSystem(FileSystem fileSystem) {
        CloseableKt.checkNotNullParameter(fileSystem, "delegate");
        this.delegate = fileSystem;
    }

    @Override // com.shadow.okio.FileSystem
    public Sink appendingSink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        return this.delegate.appendingSink(onPathParameter(path, "appendingSink", "file"), z);
    }

    @Override // com.shadow.okio.FileSystem
    public void atomicMove(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        this.delegate.atomicMove(onPathParameter(path, "atomicMove", "source"), onPathParameter(path2, "atomicMove", "target"));
    }

    @Override // com.shadow.okio.FileSystem
    public Path canonicalize(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        return onPathResult(this.delegate.canonicalize(onPathParameter(path, "canonicalize", "path")), "canonicalize");
    }

    @Override // com.shadow.okio.FileSystem
    public void createDirectory(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        this.delegate.createDirectory(onPathParameter(path, "createDirectory", "dir"), z);
    }

    @Override // com.shadow.okio.FileSystem
    public void createSymlink(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        this.delegate.createSymlink(onPathParameter(path, "createSymlink", "source"), onPathParameter(path2, "createSymlink", "target"));
    }

    public final FileSystem delegate() {
        return this.delegate;
    }

    @Override // com.shadow.okio.FileSystem
    public void delete(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        this.delegate.delete(onPathParameter(path, "delete", "path"), z);
    }

    @Override // com.shadow.okio.FileSystem
    public List<Path> list(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        List<Path> list = this.delegate.list(onPathParameter(path, "list", "dir"));
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(onPathResult((Path) it.next(), "list"));
        }
        CollectionsKt.f(arrayList);
        return arrayList;
    }

    @Override // com.shadow.okio.FileSystem
    public List<Path> listOrNull(Path path) {
        CloseableKt.checkNotNullParameter(path, "dir");
        List<Path> listListOrNull = this.delegate.listOrNull(onPathParameter(path, "listOrNull", "dir"));
        if (listListOrNull == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listListOrNull.iterator();
        while (it.hasNext()) {
            arrayList.add(onPathResult((Path) it.next(), "listOrNull"));
        }
        CollectionsKt.f(arrayList);
        return arrayList;
    }

    @Override // com.shadow.okio.FileSystem
    public Sequence<Path> listRecursively(Path path, boolean z) {
        CloseableKt.checkNotNullParameter(path, "dir");
        Sequence<Path> sequenceListRecursively = this.delegate.listRecursively(onPathParameter(path, "listRecursively", "dir"), z);
        Function1<Path, Path> function1 = new Function1<Path, Path>() { // from class: com.shadow.okio.ForwardingFileSystem.listRecursively.1
            {
                super(1);
            }

            public final Path invoke(Path path2) {
                CloseableKt.checkNotNullParameter(path2, "it");
                return ForwardingFileSystem.this.onPathResult(path2, "listRecursively");
            }
        };
        CloseableKt.checkNotNullParameter(sequenceListRecursively, "<this>");
        return new TransformingSequence(sequenceListRecursively, function1);
    }

    @Override // com.shadow.okio.FileSystem
    public FileMetadata metadataOrNull(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        FileMetadata fileMetadataMetadataOrNull = this.delegate.metadataOrNull(onPathParameter(path, "metadataOrNull", "path"));
        if (fileMetadataMetadataOrNull == null) {
            return null;
        }
        return fileMetadataMetadataOrNull.getSymlinkTarget() == null ? fileMetadataMetadataOrNull : fileMetadataMetadataOrNull.copy((251 & 1) != 0 ? fileMetadataMetadataOrNull.isRegularFile : false, (251 & 2) != 0 ? fileMetadataMetadataOrNull.isDirectory : false, (251 & 4) != 0 ? fileMetadataMetadataOrNull.symlinkTarget : onPathResult(fileMetadataMetadataOrNull.getSymlinkTarget(), "metadataOrNull"), (251 & 8) != 0 ? fileMetadataMetadataOrNull.size : null, (251 & 16) != 0 ? fileMetadataMetadataOrNull.createdAtMillis : null, (251 & 32) != 0 ? fileMetadataMetadataOrNull.lastModifiedAtMillis : null, (251 & 64) != 0 ? fileMetadataMetadataOrNull.lastAccessedAtMillis : null, (251 & 128) != 0 ? fileMetadataMetadataOrNull.extras : null);
    }

    public Path onPathParameter(Path path, String str, String str2) {
        CloseableKt.checkNotNullParameter(path, "path");
        CloseableKt.checkNotNullParameter(str, "functionName");
        CloseableKt.checkNotNullParameter(str2, "parameterName");
        return path;
    }

    public Path onPathResult(Path path, String str) {
        CloseableKt.checkNotNullParameter(path, "path");
        CloseableKt.checkNotNullParameter(str, "functionName");
        return path;
    }

    @Override // com.shadow.okio.FileSystem
    public FileHandle openReadOnly(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        return this.delegate.openReadOnly(onPathParameter(path, "openReadOnly", "file"));
    }

    @Override // com.shadow.okio.FileSystem
    public FileHandle openReadWrite(Path path, boolean z, boolean z2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        return this.delegate.openReadWrite(onPathParameter(path, "openReadWrite", "file"), z, z2);
    }

    @Override // com.shadow.okio.FileSystem
    public Sink sink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        return this.delegate.sink(onPathParameter(path, "sink", "file"), z);
    }

    @Override // com.shadow.okio.FileSystem
    public Source source(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        return this.delegate.source(onPathParameter(path, "source", "file"));
    }

    public String toString() {
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + '(' + this.delegate + ')';
    }
}
