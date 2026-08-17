package org.jetbrains.kotlin.incremental.storage;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\n\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u001b\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010!J\u0018\u0010\"\u001a\u0004\u0018\u00018\u00012\u0006\u0010 \u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010#J\u001e\u0010$\u001a\u00020%2\u0006\u0010 \u001a\u00028\u00002\u0006\u0010&\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010'J\u0015\u0010(\u001a\u00020%2\u0006\u0010 \u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020%H\u0016J\b\u0010+\u001a\u00020%H\u0016J\b\u0010,\u001a\u00020%H\u0016J\b\u0010-\u001a\u00020%H\u0016J\b\u0010.\u001a\u00020%H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR0\u0010\f\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u000eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R0\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u000eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R0\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u000eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R$\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0016j\b\u0012\u0004\u0012\u00028\u0000`\u0017X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/InMemoryStorage;", "KEY", "VALUE", "Lorg/jetbrains/kotlin/incremental/storage/InMemoryStorageInterface;", "storage", "Lorg/jetbrains/kotlin/incremental/storage/PersistentStorage;", "<init>", "(Lorg/jetbrains/kotlin/incremental/storage/PersistentStorage;)V", "storageFile", "Ljava/io/File;", "getStorageFile", "()Ljava/io/File;", "addedEntries", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getAddedEntries", "()Ljava/util/LinkedHashMap;", "modifiedEntries", "getModifiedEntries", "appendedEntries", "getAppendedEntries", "removedKeys", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "getRemovedKeys", "()Ljava/util/LinkedHashSet;", "keys", "", "getKeys", "()Ljava/util/Set;", "contains", "", "key", "(Ljava/lang/Object;)Z", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "set", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "(Ljava/lang/Object;)V", "applyChanges", "clearChanges", "flush", "close", "clean", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class InMemoryStorage<KEY, VALUE> implements InMemoryStorageInterface<KEY, VALUE> {
    private final LinkedHashMap<KEY, VALUE> addedEntries;
    private final LinkedHashMap<KEY, VALUE> appendedEntries;
    private final LinkedHashMap<KEY, VALUE> modifiedEntries;
    private final LinkedHashSet<KEY> removedKeys;
    private final PersistentStorage<KEY, VALUE> storage;
    private final File storageFile;

    public InMemoryStorage(PersistentStorage<KEY, VALUE> persistentStorage) {
        persistentStorage.getClass();
        this.storage = persistentStorage;
        this.storageFile = persistentStorage.getStorageFile();
        this.addedEntries = new LinkedHashMap<>();
        this.modifiedEntries = new LinkedHashMap<>();
        this.appendedEntries = new LinkedHashMap<>();
        this.removedKeys = new LinkedHashSet<>();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.InMemoryStorageInterface
    public synchronized void applyChanges() {
        try {
            for (Map.Entry<KEY, VALUE> entry : this.addedEntries.entrySet()) {
                this.storage.set(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<KEY, VALUE> entry2 : this.modifiedEntries.entrySet()) {
                this.storage.set(entry2.getKey(), entry2.getValue());
            }
            Iterator<T> it = this.removedKeys.iterator();
            while (it.hasNext()) {
                this.storage.remove((KEY) it.next());
            }
            clearChanges();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void clean() {
        this.storage.clean();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.InMemoryStorageInterface
    public synchronized void clearChanges() {
        this.addedEntries.clear();
        this.modifiedEntries.clear();
        this.appendedEntries.clear();
        this.removedKeys.clear();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        applyChanges();
        this.storage.close();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized boolean contains(KEY key) {
        boolean zContains;
        zContains = true;
        if (!this.addedEntries.containsKey(key) && !this.modifiedEntries.containsKey(key) && !this.appendedEntries.containsKey(key)) {
            zContains = this.removedKeys.contains(key) ? false : this.storage.contains(key);
        }
        return zContains;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void flush() {
        applyChanges();
        this.storage.flush();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized VALUE get(KEY key) {
        VALUE value;
        value = this.addedEntries.get(key);
        if (value == null && (value = this.modifiedEntries.get(key)) == null) {
            value = this.removedKeys.contains(key) ? null : this.storage.get(key);
        }
        return value;
    }

    public final LinkedHashMap<KEY, VALUE> getAddedEntries() {
        return this.addedEntries;
    }

    public final LinkedHashMap<KEY, VALUE> getAppendedEntries() {
        return this.appendedEntries;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized Set<KEY> getKeys() {
        Set<KEY> keys;
        Set<KEY> setKeySet;
        keys = this.storage.getKeys();
        setKeySet = this.addedEntries.keySet();
        setKeySet.getClass();
        return SetsKt.minus(SetsKt.plus(keys, setKeySet), this.removedKeys);
    }

    public final LinkedHashMap<KEY, VALUE> getModifiedEntries() {
        return this.modifiedEntries;
    }

    public final LinkedHashSet<KEY> getRemovedKeys() {
        return this.removedKeys;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public File getStorageFile() {
        return this.storageFile;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void remove(KEY key) {
        try {
            if (this.addedEntries.containsKey(key)) {
                this.addedEntries.remove(key);
            } else if (this.modifiedEntries.containsKey(key)) {
                this.modifiedEntries.remove(key);
                this.removedKeys.add(key);
            } else if (this.appendedEntries.containsKey(key)) {
                this.appendedEntries.remove(key);
                this.removedKeys.add(key);
            } else if (!this.removedKeys.contains(key) && this.storage.contains(key)) {
                this.removedKeys.add(key);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void set(KEY key, VALUE value) {
        try {
            if (this.addedEntries.containsKey(key)) {
                this.addedEntries.put(key, value);
            } else if (this.modifiedEntries.containsKey(key)) {
                this.modifiedEntries.put(key, value);
            } else if (this.appendedEntries.containsKey(key)) {
                this.appendedEntries.remove(key);
                this.modifiedEntries.put(key, value);
            } else if (this.removedKeys.contains(key)) {
                this.removedKeys.remove(key);
                this.modifiedEntries.put(key, value);
            } else if (this.storage.contains(key)) {
                this.modifiedEntries.put(key, value);
            } else {
                this.addedEntries.put(key, value);
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
