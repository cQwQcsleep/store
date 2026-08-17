package org.jetbrains.kotlin.incremental.storage;

import java.io.File;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0016H\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0016J\b\u0010\u001d\u001a\u00020\u0016H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/PersistentStorageWrapper;", "KEY", "VALUE", "Lorg/jetbrains/kotlin/incremental/storage/PersistentStorage;", "storage", "<init>", "(Lorg/jetbrains/kotlin/incremental/storage/PersistentStorage;)V", "storageFile", "Ljava/io/File;", "getStorageFile", "()Ljava/io/File;", "keys", "", "getKeys", "()Ljava/util/Set;", "contains", "", "key", "(Ljava/lang/Object;)Z", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "set", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "(Ljava/lang/Object;)V", "flush", "close", "clean", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class PersistentStorageWrapper<KEY, VALUE> implements PersistentStorage<KEY, VALUE> {
    private final PersistentStorage<KEY, VALUE> storage;
    private final File storageFile;

    public PersistentStorageWrapper(PersistentStorage<KEY, VALUE> persistentStorage) {
        persistentStorage.getClass();
        this.storage = persistentStorage;
        this.storageFile = persistentStorage.getStorageFile();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void clean() {
        this.storage.clean();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.storage.close();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized boolean contains(KEY key) {
        return this.storage.contains(key);
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void flush() {
        this.storage.flush();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized VALUE get(KEY key) {
        return this.storage.get(key);
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized Set<KEY> getKeys() {
        return this.storage.getKeys();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public File getStorageFile() {
        return this.storageFile;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void remove(KEY key) {
        this.storage.remove(key);
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void set(KEY key, VALUE value) {
        this.storage.set(key, value);
    }
}
