package com.intellij.util.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B9\b\u0007\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\u0010¢\u0006\u0002\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\u0013J\b\u0010\u0018\u001a\u00020\u0013H\u0016R\"\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/intellij/util/io/PersistentEnumeratorWal;", "Data", "Ljava/io/Closeable;", "dataDescriptor", "Lcom/intellij/util/io/KeyDescriptor;", "useCompression", "", "file", "Ljava/nio/file/Path;", "walIoExecutor", "Ljava/util/concurrent/ExecutorService;", "compact", "<init>", "(Lcom/intellij/util/io/KeyDescriptor;ZLjava/nio/file/Path;Ljava/util/concurrent/ExecutorService;Z)V", "underlying", "Lcom/intellij/util/io/PersistentMapWal;", "", "kotlin.jvm.PlatformType", "enumerate", "", "data", "id", "(Ljava/lang/Object;I)V", "flush", "close", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PersistentEnumeratorWal<Data> implements Closeable {
    private final PersistentMapWal<Data, Integer> underlying;

    public PersistentEnumeratorWal(KeyDescriptor<Data> keyDescriptor, boolean z, Path path, ExecutorService executorService, boolean z2) throws IOException {
        keyDescriptor.getClass();
        path.getClass();
        executorService.getClass();
        this.underlying = new PersistentMapWal<>(keyDescriptor, WriteAheadLogKt.getIntegerExternalizer(), z, path, executorService, z2);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws ExecutionException, InterruptedException, IOException {
        this.underlying.close();
    }

    public final void enumerate(Data data, int id) throws IOException {
        this.underlying.put(data, Integer.valueOf(id));
    }

    public final void flush() throws IOException {
        this.underlying.flush();
    }
}
