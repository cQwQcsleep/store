package org.jetbrains.kotlin.incremental.dirtyFiles;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.incremental.CompilationTransaction;
import org.jetbrains.kotlin.incremental.CompilationTransactionKt;
import org.jetbrains.kotlin.incremental.IncrementalCompilerRunner;
import org.jetbrains.kotlin.incremental.dirtyFiles.DirtyFilesCachedHistory;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eJ\u000e\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/incremental/dirtyFiles/DirtyFilesCachedHistory;", "", "workingDir", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "dirtySourcesSinceLastTimeFile", "store", "", "withTransaction", "Lorg/jetbrains/kotlin/incremental/CompilationTransaction;", "allDirtySources", "", "read", "", "clear", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DirtyFilesCachedHistory {
    private final File dirtySourcesSinceLastTimeFile;

    public DirtyFilesCachedHistory(File file) {
        file.getClass();
        this.dirtySourcesSinceLastTimeFile = new File(file, IncrementalCompilerRunner.DIRTY_SOURCES_FILE_NAME);
    }

    public static CharSequence a(File file) {
        file.getClass();
        String absolutePath = FilesKt.normalize(file).getAbsolutePath();
        absolutePath.getClass();
        return absolutePath;
    }

    public final void clear(CompilationTransaction withTransaction) {
        withTransaction.getClass();
        Path path = this.dirtySourcesSinceLastTimeFile.toPath();
        path.getClass();
        withTransaction.deleteFile(path);
    }

    public final List<File> read() {
        if (!this.dirtySourcesSinceLastTimeFile.exists()) {
            return CollectionsKt.emptyList();
        }
        List lines$default = FilesKt.readLines$default(this.dirtySourcesSinceLastTimeFile, (Charset) null, 1, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(lines$default, 10));
        Iterator it = lines$default.iterator();
        while (it.hasNext()) {
            arrayList.add(new File((String) it.next()));
        }
        return arrayList;
    }

    public final void store(CompilationTransaction withTransaction, Collection<? extends File> allDirtySources) {
        withTransaction.getClass();
        allDirtySources.getClass();
        String strLineSeparator = System.lineSeparator();
        strLineSeparator.getClass();
        String strJoinToString$default = CollectionsKt.joinToString$default(allDirtySources, strLineSeparator, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: eu3
            public final Object invoke(Object obj) {
                return DirtyFilesCachedHistory.a((File) obj);
            }
        }, 30, (Object) null);
        Path path = this.dirtySourcesSinceLastTimeFile.toPath();
        path.getClass();
        CompilationTransactionKt.writeText(withTransaction, path, strJoinToString$default);
    }
}
