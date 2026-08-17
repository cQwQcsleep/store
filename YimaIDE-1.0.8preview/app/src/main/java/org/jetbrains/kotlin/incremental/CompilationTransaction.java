package org.jetbrains.kotlin.incremental;

import java.io.Closeable;
import java.nio.file.Path;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.storage.InMemoryStorageInterface;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0018\u0010\u0013\u001a\u00020\u00032\u000e\u0010\u0014\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0015H&R\u001a\u0010\b\u001a\u0004\u0018\u00010\u0001X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u0004\u0018\u00010\u000eX¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/CompilationTransaction;", "Ljava/io/Closeable;", "registerAddedOrChangedFile", "", "outputFile", "Ljava/nio/file/Path;", "deleteFile", "markAsSuccessful", "cachesManager", "getCachesManager", "()Ljava/io/Closeable;", "setCachesManager", "(Ljava/io/Closeable;)V", "executionThrowable", "", "getExecutionThrowable", "()Ljava/lang/Throwable;", "setExecutionThrowable", "(Ljava/lang/Throwable;)V", "registerInMemoryStorageWrapper", "inMemoryStorageWrapper", "Lorg/jetbrains/kotlin/incremental/storage/InMemoryStorageInterface;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CompilationTransaction extends Closeable {
    void deleteFile(Path outputFile);

    Closeable getCachesManager();

    Throwable getExecutionThrowable();

    void markAsSuccessful();

    void registerAddedOrChangedFile(Path outputFile);

    void registerInMemoryStorageWrapper(InMemoryStorageInterface<?, ?> inMemoryStorageWrapper);

    void setCachesManager(Closeable closeable);

    void setExecutionThrowable(Throwable th);
}
